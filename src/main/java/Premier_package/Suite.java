package Premier_package;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class Suite {
    private JPanel Test;
    private JComboBox<String> Med_cin;
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
    static JFrame Suite = new JFrame("Suite");
    DefaultListModel<Rdv> dlm = new DefaultListModel<>();


    Suite(Application App, int i) throws SQLException, ClassNotFoundException
    {
        this.Suu(App);
    }

    private ListSelectionListener createListSelectionListener(JList<Rdv> list1,Application App) {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                String Patient="";
                String Medecin="";
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

        for(Rdv N : App.Rendezvous )
        {
            dlm.addElement(N);
        }
        Liste.setModel(dlm);
        Liste.setCellRenderer(createListRenderer());
        Liste.addListSelectionListener(createListSelectionListener(Liste,App));
        SpinnerDateModel model = new SpinnerDateModel();
        spinner1.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "dd-MM-yyyy");
        spinner1.setEditor(editor);
        model.setStart( editor.getFormat().parse("26-02-2009") );

        SpinnerDateModel model2 = new SpinnerDateModel();
        spinner2.setModel(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "dd-MM-yyyy");
        spinner2.setEditor(editor2);
        model2.setStart( editor2.getFormat().parse("26-02-2009") );

        Med_cin.addItem("Choissiez un medecin");

        ButtonGroup group = new ButtonGroup();
        group.add(reserveRadioButton);
        group.add(libreRadioButton);
        group.add(passeRadioButton);

        ButtonGroup group2 = new ButtonGroup();
        group2.add(duRadioButton);
        group2.add(toutLesRendezVousRadioButton);

        for(Medecin N : App.Med)
        {
            Med_cin.addItem("Dr. "+N.toString());
        }
    }
}
