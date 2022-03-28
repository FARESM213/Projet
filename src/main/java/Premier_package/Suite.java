package Premier_package;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Suite {
    private JPanel Test;
    private JComboBox<Medecin> Med_cin;
    private JComboBox Jour;
    private JButton appliquerButton;
    private JRadioButton libreRadioButton;
    private JRadioButton reserveRadioButton;
    private JRadioButton passeRadioButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JRadioButton toutLesRendezVousRadioButton;
    private JRadioButton duRadioButton;
    private JList<Rdv> Liste;
    private JTextArea textArea1;
    private JButton annulerButton;
    private int clickCount = 0;
    ButtonGroup group2 = new ButtonGroup();

    static JFrame Suite = new JFrame("Suite");
    ButtonGroup group = new ButtonGroup();
    DefaultListModel<Rdv> dlm = new DefaultListModel<>();


    Suite(Application App, int i) throws SQLException, ClassNotFoundException
    {
        this.Suu(App);
        appliquerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String whouere="WHERE rdvno IS NOT NULL ";
                Date dateToStr1;
                Date dateToStr2;

                LocalDate date = LocalDate.now();

                dateToStr1= (Date) spinner1.getValue();
                dateToStr2= (Date) spinner2.getValue();

                LocalDate date1=dateToStr1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2=dateToStr2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int idmedcin=-1;

                if (Med_cin.getSelectedItem()!=null)
                {
                    Object O = Med_cin.getSelectedItem();
                    idmedcin= ((Medecin) O).Get_id();
                }

                System.out.println(Med_cin.getSelectedItem());

                if (libreRadioButton.isSelected())
                {
                    whouere+= "AND etat=true ";
                    if (++clickCount % 2 == 0) {
                        group.clearSelection();
                    }
                }
                else if (reserveRadioButton.isSelected())
                {
                    whouere+= "AND etat=false ";
                }
                else
                {
                    whouere+= "AND rdv_date < '"+date+"' ";
                }

                if (toutLesRendezVousRadioButton.isSelected())
                {
                    whouere+= "AND rdv_date > '"+date+"' ";
                }
                else if (duRadioButton.isSelected())
                {
                    whouere+= "AND rdv_date BETWEEN '"+date1+ "' AND '"+date2+"' ";
                }
                if (idmedcin!=-1)
                {
                    whouere+= "AND medno="+idmedcin;
                }
                System.out.println( whouere );
                try
                {
                    setDlm(whouere,App);
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group.clearSelection();
                group2.clearSelection();
                dlm.clear();
                Liste.setModel(dlm);
                textArea1.setText("");
            }
        });
    }

    void setDlm(String whouere ,Application App) throws SQLException {
        ResultSet rs;
        dlm.clear();
        rs=App.maconnexion.Search("rendez_vous","rdvno",whouere);
        while (rs.next())
        {
            for(Rdv N : App.Rendezvous )
            {
                if (rs.getInt(1)==N.Get_id())
                {
                    dlm.addElement(N);
                }
            }
        }
        Liste.setModel(dlm);
        Liste.setCellRenderer(createListRenderer());
        Liste.addListSelectionListener(createListSelectionListener(Liste,App));

    }

    private ListSelectionListener createListSelectionListener(JList<Rdv> list1,Application App) {
        return e -> {
            if (!e.getValueIsAdjusting()) {

                String Patient="";
                String Medecin="";
                if (list1.getSelectedValue()!=null)
                {

                    for (Patient N :App.Pat)
                    {
                        if(list1.getSelectedValue().Get_pat()==N.Get_id())
                            Patient=N.Get_nom();
                    }
                    for (Medecin N :App.Med)
                    {
                        if(list1.getSelectedValue().Get_med()==N.Get_id())
                            Medecin=N.Get_nom();
                    }
                }
                textArea1.setText(" Patient : "+Patient+"\n\n Medecin : "+Medecin+"\n\n "+ list1.getSelectedValue());
            }
        };
    }

    private ListCellRenderer<? super Rdv> createListRenderer() {
        return new DefaultListCellRenderer() {
            private final Color background = new Color(0, 100, 255, 15);
            private final Color defaultBackground = (Color) UIManager.get("List.background");
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    Rdv emp = (Rdv) value;
                    label.setText(String.format("%d [%d]", emp.Get_med(), emp.Get_pat()));
                    if (!isSelected) {
                        label.setBackground(index % 2 == 0 ? background : defaultBackground);
                    }
                }
                return c;
            }
        };
    }

    public void Suu(Application App) {
        try {
            createUIComponents(App);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Suite.setContentPane(Test);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(800,600));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }


    private void createUIComponents(Application App) throws ParseException {

        SpinnerDateModel model = new SpinnerDateModel();
        spinner1.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "yyyy-MM-dd");
        spinner1.setEditor(editor);
        model.setStart( editor.getFormat().parse("2009-02-26") );

        SpinnerDateModel model2 = new SpinnerDateModel();
        spinner2.setModel(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "yyyy-MM-dd");
        spinner2.setEditor(editor2);
        model2.setStart( editor2.getFormat().parse("2009-02-26") );

        Med_cin.addItem(null);

        group.add(reserveRadioButton);
        group.add(libreRadioButton);
        group.add(passeRadioButton);

        group2.add(duRadioButton);
        group2.add(toutLesRendezVousRadioButton);

        for(Medecin N : App.Med)
        {
            Med_cin.addItem(N);
        }
    }
}
