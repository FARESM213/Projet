package View;

import Controller.Application;
import Model.Medecin;
import Model.Rdv;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Fenetre_Suite {

    private JPanel Test;
    private JComboBox <Medecin>Med_cin;
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
    private JButton consulterProfilButton;
    private JComboBox comboBox1;
    private JButton statistiquesButton;
    private JButton SUPP;
    ButtonGroup group2 = new ButtonGroup();

    public static JFrame Suite = new JFrame("Suite");
    ButtonGroup group = new ButtonGroup();
    DefaultListModel<Rdv> dlm = new DefaultListModel<>();


    public Fenetre_Suite() {}

    public void setDlm(String whouere, Application App) throws SQLException {
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

    private ListSelectionListener createListSelectionListener(JList<Rdv> list1, Application App) {
        return e -> {
            if (!e.getValueIsAdjusting()) {

                String Patient="";
                String Medecin="";
                if (list1.getSelectedValue()!=null)
                {
                    for (Model.Patient N :App.Pat)
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
                    String etat;
                    if (emp.Get_etat())
                    {
                        etat= "Libre";
                    }
                    else
                    {
                        etat= "Reservé";
                    }
                    label.setText(String.format("  Numero :  %d \t       Etat : %s ", emp.Get_id(), etat));
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
        Suite.setPreferredSize(new Dimension(820,680));
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
        comboBox1.addItem(null);

        group.add(reserveRadioButton);
        group.add(libreRadioButton);
        group.add(passeRadioButton);

        group2.add(duRadioButton);
        group2.add(toutLesRendezVousRadioButton);

        for(Medecin N : App.Med)
        {
            Med_cin.addItem(N);
            comboBox1.addItem(N.getHopital());
        }
    }

    public void setSuite(boolean suite) {
        Suite.setVisible(suite);
    }

    public JButton getAppliquerButton() {
        return appliquerButton;
    }

    public Object getSpinner1() {
        return spinner1.getValue();
    }

    public Object getSpinner2() {
        return spinner2.getValue();
    }

    public Object getMed_cin() {
        return Med_cin.getSelectedItem();
    }

    public Object getCombo() {
        return comboBox1.getSelectedItem();
    }


    public JRadioButton getLibreRadioButton() {
        return libreRadioButton;
    }

    public JRadioButton getDuRadioButton() {
        return duRadioButton;
    }

    public JRadioButton getPasseRadioButton() {
        return passeRadioButton;
    }

    public JRadioButton getReserveRadioButton() {
        return reserveRadioButton;
    }
    public JButton getAnnulerButton() {
        return annulerButton;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JRadioButton getToutLesRendezVousRadioButton() {
        return toutLesRendezVousRadioButton;
    }

    public ButtonGroup getGroup() {
        return group;
    }

    public ButtonGroup getGroup2() {
        return group2;
    }

    public DefaultListModel<Rdv> getDlm() {
        return dlm;
    }

    public JList<Rdv> getListe() {
        return Liste;
    }

    public JButton getConsulterProfilButton() {
        return consulterProfilButton;
    }

    public JButton getStatistiquesButton() {
        return statistiquesButton;
    }

    public JButton getSupprimer() {
        return SUPP;
    }
}
