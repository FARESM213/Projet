package View;

import Controller.*;
import Model.Medecin;
import Model.Patient;
import Model.Rdv;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Fenetre_Profil
{
    private JPanel T;
    private JButton supprimerCompteButton;
    public JTextField Nom;
    public JTextField Login;
    public JPasswordField mdp;
    public JTextField hospi;
    public JTextField Email;
    public JTextField Spec;
    private JRadioButton afficherRdvRadioButton;
    private JList<Rdv> list1;
    private JTextArea textArea1;
    private JButton modifierInformationsButton;
    private JButton retourButton;
    private JLabel nmLabel;
    private JLabel lgLabel;
    private JLabel mpLabel;
    private JLabel emlLabel;
    private JLabel speLabel;
    private JLabel specialiteLabel;
    private JButton modifierButton;
    private JLabel tssLabel;
    private JButton choisirUneImageButton;

    private JLabel hptlLabel;
    private JLabel hopitalLabel;
    private JButton supprimerRdvButton;
    private JPanel MENOM;
    private JPanel MELOGIN;
    private JPanel MEMDP;
    private JPanel MEEMAIL;
    private JPanel MESPE;
    private JPanel MEHOP;

    public static JFrame Suite = new JFrame("Suite");

    DefaultListModel<Rdv> dlm = new DefaultListModel<>();
    public Fenetre_Profil() {

    }

    public void Fentre_Erreur(String Err) {
        JOptionPane.showMessageDialog(null,Err,"Erreur",JOptionPane.INFORMATION_MESSAGE);    }

    public int Fenetre_confirm(String msg) {
        return JOptionPane.showConfirmDialog(null,msg,"Confirmation",JOptionPane.YES_NO_OPTION);
    }

    public  void Visible(Object P)
    {

        Nom.setVisible(true);
        Login.setVisible(true);
        mdp.setVisible(true);
        Email.setVisible(true);
        choisirUneImageButton.setVisible(true);
        supprimerRdvButton.setVisible(false);

        if(P.getClass()==Medecin.class)
        {
            Spec.setVisible(true);
            hospi.setVisible(true);
        }

        nmLabel.setVisible(false);
        lgLabel.setVisible(false);
        mpLabel.setVisible(false);
        emlLabel.setVisible(false);
        speLabel.setVisible(false);
        hptlLabel.setVisible(false);
        list1.setVisible(false);
        textArea1.setVisible(false);
        modifierButton.setVisible(true);
        afficherRdvRadioButton.setVisible(false);
        MENOM.setVisible(false);
        MELOGIN.setVisible(false);
        MESPE.setVisible(false);
        MEHOP.setVisible(false);
        MEMDP.setVisible(false);
        MEEMAIL.setVisible(false);
    }

    public  void SetView(boolean etat)
    {
        Suite.setVisible(etat);
    }



    public void MessNOM(boolean indice,JTextField F){MENOM.setVisible(indice);Color(F,indice);}
    public void MessLOGIN(boolean indice,JTextField F){MELOGIN.setVisible(indice);Color(F,indice);}
    public void MessEMAIL(boolean indice,JTextField F){MEEMAIL.setVisible(indice);Color(F,indice);}
    public void MessSPE(boolean indice,JTextField F){MESPE.setVisible(indice);Color(F,indice);}
    public void Messmdp(boolean indice,JTextField F){MEMDP.setVisible(indice);Color(F,indice);}
    public void MessHOP(boolean indice,JTextField F){MEHOP.setVisible(indice);Color(F,indice);}


    public void Color(JTextField F,boolean C){
        if(C==true)F.setBackground(Color.RED);
        else if (C==false)F.setBackground(Color.GREEN);
    }

    public  void renit( Object P)
    {
        Image img;
        if(P.getClass()==Patient.class)
        {
            img =Toolkit.getDefaultToolkit().createImage(((Patient)P).getImage());
        }
        else
        {
            img =Toolkit.getDefaultToolkit().createImage(((Medecin)P).getImage());
        }
        ImageIcon icone;
        Image newimg = img.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icone = new ImageIcon(newimg);  // transform it back
        tssLabel.setText("");
        tssLabel.setIcon(icone);

    }

    public void Suu(Application App, Object P) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        createUIComponents(P);
        renit(P);


        afficherRdvRadioButton.setIcon(Resize("Images\\Off.png",28,13));

        supprimerRdvButton.setIcon(Resize("Images\\rapport.png",20,20));
        supprimerRdvButton.setIconTextGap(10);

        modifierInformationsButton.setIcon(Resize("Images\\change.png",20,20));
        modifierInformationsButton.setIconTextGap(10);

        modifierButton.setIcon(Resize("Images\\check.png",20,20));
        modifierButton.setIconTextGap(10);

        supprimerCompteButton.setIcon(Resize("Images\\delete.png",20,20));
        supprimerCompteButton.setIconTextGap(10);

        retourButton.setIcon(Resize("Images\\return.png",20,20));
        retourButton.setIconTextGap(10);
        choisirUneImageButton.setIcon(Resize("Images\\folder.png",20,20));
        choisirUneImageButton.setIconTextGap(10);

        Suite.setContentPane(T);
        list1.setVisible(false);
        textArea1.setVisible(false);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(880,680));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    private void createUIComponents(Object O) {


        Nom.setVisible(false);
        Login.setVisible(false);
        mdp.setVisible(false);
        Email.setVisible(false);
        Spec.setVisible(false);
        modifierButton.setVisible(false);
        choisirUneImageButton.setVisible(false);
        hospi.setVisible(false);
        supprimerRdvButton.setVisible(false);
        MENOM.setVisible(false);
        MELOGIN.setVisible(false);
        MESPE.setVisible(false);
        MEHOP.setVisible(false);
        MEMDP.setVisible(false);
        MEEMAIL.setVisible(false);

        if(O.getClass()==Medecin.class)
        {
            nmLabel.setText(((Medecin) O).Get_nom());
            lgLabel.setText(((Medecin) O).Get_log());
            mpLabel.setText(((Medecin) O).Get_mdp());
            emlLabel.setText(((Medecin) O).Get_mail());
            speLabel.setText(((Medecin) O).Get_job());
            hptlLabel.setText(((Medecin) O).getHopital());
            specialiteLabel.setVisible(true);
            hopitalLabel.setVisible(true);
        }
        else
        {
            nmLabel.setText(((Patient) O).Get_nom());
            lgLabel.setText(((Patient) O).Get_log());
            mpLabel.setText(((Patient) O).Get_mdp());
            emlLabel.setText(((Patient) O).Get_Mail());
            speLabel.setVisible(false);
            specialiteLabel.setVisible(false);
            hopitalLabel.setVisible(false);
            hptlLabel.setVisible(false);
        }

        if(O.getClass()==Medecin.class)
        {
            Nom.setText(((Medecin) O).Get_nom());
            Login.setText(((Medecin) O).Get_log());
            mdp.setText(((Medecin) O).Get_mdp());
            Email.setText(((Medecin) O).Get_mail());
            Spec.setText(((Medecin) O).Get_job());
            hospi.setText(((Medecin) O).getHopital());
        }
        else
        {
            Nom.setText(((Patient) O).Get_nom());
            Login.setText(((Patient) O).Get_log());
            mdp.setText(((Patient) O).Get_mdp());
            Email.setText(((Patient) O).Get_Mail());
        }
    }

    public JPanel getT() {
        return T;
    }

    public JButton getSupprimerCompteButton() {
        return supprimerCompteButton;
    }

    public String getNom() {
        return Nom.getText();
    }

    public String getLogin() {
        return Login.getText();
    }

    public String getMdp() {
        return String.valueOf(mdp.getPassword());
    }

    public String getEmail() {
        return Email.getText();
    }

    public String getSpec() {
        return Spec.getText();
    }

    public JRadioButton getAfficherRdvRadioButton() {
        return afficherRdvRadioButton;
    }

    public JList getList1() {
        return list1;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JButton getModifierInformationsButton() {
        return modifierInformationsButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public JLabel getNmLabel() {
        return nmLabel;
    }

    public JLabel getLgLabel() {
        return lgLabel;
    }

    public JLabel getMpLabel() {
        return mpLabel;
    }

    public JLabel getEmlLabel() {
        return emlLabel;
    }

    public JLabel getSpeLabel() {
        return speLabel;
    }

    public JLabel getSpecialiteLabel() {
        return specialiteLabel;
    }

    public JButton getModifierButton() {
        return modifierButton;
    }

    public static JFrame getSuite() {
        return Suite;
    }

    public String getHospi() {
        return hospi.getText();
    }

    public void radio(Application App, Object O)  {

        if (afficherRdvRadioButton.isSelected())
        {
            afficherRdvRadioButton.setIcon(Resize("Images\\On.png",28,13));
            list1.setVisible(true);
            textArea1.setVisible(true);
            supprimerRdvButton.setVisible(true);
            if(O.getClass()==Medecin.class) {
                try {
                    setDlm("WHERE medno= "+((Medecin) O).Get_id(),App);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    setDlm("WHERE patno= "+((Patient) O).Get_id(),App);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            supprimerRdvButton.setVisible(false);
            list1.setVisible(false);
            textArea1.setVisible(false);
            afficherRdvRadioButton.setIcon(Resize("Images\\Off.png",28,13));
        }


    }


    public JButton getchoisirUneImageButton()
    {
        return choisirUneImageButton;
    }

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
        list1.setModel(dlm);
        list1.setCellRenderer(createListRenderer());
        list1.addListSelectionListener(createListSelectionListener(list1,App));
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

    public JButton getSupprimerRdvButton() {
        return supprimerRdvButton;
    }


    public ImageIcon Resize(String path, int W , int H)
    {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(W, H,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

}
