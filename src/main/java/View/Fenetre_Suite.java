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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
    private JComboBox<String> comboBox1;
    private JButton statistiquesButton;
    private JButton reserverButton;
    private JButton retourButton;
    private JLabel medecinLabel;
    private JLabel hopitalLabel;
    private JLabel dateLabel;
    private JLabel filtreLabel;
    private ButtonGroup group2 = new ButtonGroup();

    public static JFrame Suite = new JFrame("Principale");
    private ButtonGroup group = new ButtonGroup();


    DefaultListModel<Rdv> dlm = new DefaultListModel<>();

    public Fenetre_Suite() {

        Suite.setIconImage(Resize("Images\\Medecin.jpg",20,20).getImage());

        toutLesRendezVousRadioButton.addActionListener(e -> {
        SetIcons(toutLesRendezVousRadioButton,toutLesRendezVousRadioButton.isSelected());
        SetIcons(duRadioButton,duRadioButton.isSelected());
        });
        duRadioButton.addActionListener(e -> {
            SetIcons(duRadioButton,duRadioButton.isSelected());
            SetIcons(toutLesRendezVousRadioButton,toutLesRendezVousRadioButton.isSelected());
        });
        libreRadioButton.addActionListener(e -> {
            SetIcons(libreRadioButton,libreRadioButton.isSelected());
            SetIcons(reserveRadioButton,reserveRadioButton.isSelected());
            SetIcons(passeRadioButton,passeRadioButton.isSelected());

        });
        reserveRadioButton.addActionListener(e -> {
            SetIcons(libreRadioButton,libreRadioButton.isSelected());
            SetIcons(reserveRadioButton,reserveRadioButton.isSelected());
            SetIcons(passeRadioButton,passeRadioButton.isSelected());
        });
        passeRadioButton.addActionListener(e -> {
            SetIcons(libreRadioButton,libreRadioButton.isSelected());
            SetIcons(reserveRadioButton,reserveRadioButton.isSelected());
            SetIcons(passeRadioButton,passeRadioButton.isSelected());
        });

    }

    public void setDlm(String whouere, Application App) throws SQLException {
        ResultSet rs;
        dlm.clear();
        rs=App.getMaconnexion().Search("rendez_vous","rdvno",whouere);
        while (rs.next())
        {
            for(Rdv N : App.getRendezvous() )
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
                    for (Model.Patient N :App.getPat())
                    {
                        if(list1.getSelectedValue().Get_pat()==N.Get_id())
                            Patient=N.Get_nom();
                    }
                    for (Medecin N :App.getMed())
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
                        etat= "Reserv??";
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

    public void Suu(Application App,int a ) {
        try {
            createUIComponents(App, a);
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


    private void createUIComponents(Application App,int i ) throws ParseException {


        if (i==1)
        {
            reserverButton.setIcon(Resize("Images\\Reserver.png",20,20));
            reserverButton.setIconTextGap(10);
        }
        else
        {
            reserverButton.setIcon(Resize("Images\\Creer.png",20,20));
            reserverButton.setIconTextGap(10);
        }

        consulterProfilButton.setIcon(Resize("Images\\docteur.png",20,20));
        consulterProfilButton.setIconTextGap(10);

        annulerButton.setIcon(Resize("Images\\plaster.png",20,20));
        appliquerButton.setIcon(Resize("Images\\submit.png",20,20));

        medecinLabel.setIcon(Resize("Images\\doc.png",20,20));
        hopitalLabel.setIcon(Resize("Images\\hopital.png",20,20));
        dateLabel.setIcon(Resize("Images\\date2.png",20,20));
        filtreLabel.setIcon(Resize("Images\\coeur.png",20,20));

        retourButton.setIcon(Resize("Images\\return.png",20,20));
        libreRadioButton.setIcon(Resize("Images\\Off.png",28,13));
        reserveRadioButton.setIcon(Resize("Images\\Off.png",28,13));
        passeRadioButton.setIcon(Resize("Images\\Off.png",28,13));
        toutLesRendezVousRadioButton.setIcon(Resize("Images\\Off.png",28,13));
        duRadioButton.setIcon(Resize("Images\\Off.png",28,13));


        SpinnerDateModel model = new SpinnerDateModel();
        spinner1.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "yyyy-MM-dd");
        spinner1.setEditor(editor);
        model.setStart( editor.getFormat().parse("1995-02-26") );
        SpinnerDateModel model2 = new SpinnerDateModel();
        spinner2.setModel(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "yyyy-MM-dd");
        spinner2.setEditor(editor2);
        model2.setStart( editor2.getFormat().parse("1995-02-26") );

        Med_cin.addItem(null);
        comboBox1.addItem(null);
        group.add(reserveRadioButton);
        group.add(libreRadioButton);
        group.add(passeRadioButton);

        group2.add(duRadioButton);
        group2.add(toutLesRendezVousRadioButton);

         Set set = new HashSet();

        for(Medecin N : App.getMed())
        {
            Med_cin.addItem(N);
            if ( !set.contains(N.getHopital()))
            {
                set.add(N.getHopital());
            }

        }

        for (Object o : set) {
            comboBox1.addItem((String) o);
        }
    }

    public void setSuite(boolean suite) {
        Suite.setVisible(suite);
    }

    public JButton getRetourButton() {
        return retourButton;
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

    public boolean getElementListe() {
        return Liste.getSelectedValue().Get_etat();
    }

    public JButton getConsulterProfilButton() {
        return consulterProfilButton;
    }

    public JButton getStatistiquesButton() {
        return statistiquesButton;
    }

    public JButton getReserverButton() {
        return reserverButton;
    }

    public void Fentre_Erreur() {
        JOptionPane.showMessageDialog(null,"Rendez-vous deja reserv??...","Erreur",JOptionPane.INFORMATION_MESSAGE);
    }

    public int Fenetre_confirm() {
        return JOptionPane.showConfirmDialog(null,"  Mot de passe incorrect... Voulez vous le modifier ?","Clear TextField",JOptionPane.YES_NO_OPTION);
    }

    public void SetView(boolean etat,Application App)
    {
        Suite.setVisible(etat);
        try {
            setDlm("WHERE 1",App);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void SetIcons(JRadioButton B, boolean etat)
    {
        if (etat)
        {
            B.setIcon(Resize("Images\\On.png",28,13));
        }
        else
        {
            B.setIcon(Resize("Images\\Off.png",28,13));
        }
    }


    public ImageIcon Resize(String path, int W , int H)
    {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(W, H,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    public void ResetButton() {

        SetIcons(toutLesRendezVousRadioButton,false);
        SetIcons(duRadioButton,false);
        SetIcons(libreRadioButton,false);
        SetIcons(reserveRadioButton,false);
        SetIcons(passeRadioButton,false);

    }
}
