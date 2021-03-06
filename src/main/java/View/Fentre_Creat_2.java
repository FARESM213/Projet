package View;

import javax.swing.*;
import java.awt.*;

public class Fentre_Creat_2 {


    private JPanel j;
    private JTextField LoginTF;
    private JTextField NomTF;
    private JButton creerButton;
    private JPasswordField MDPTF;
    private JTextField SPETF;
    private JPanel IFMEDECIN;
    private JTextField EmailTF;
    private JTextField HopiTF;
    private JPanel NOMME;
    private JPanel LOGINME;
    private JPanel mdpME;
    private JPanel EMAILME;
    private JPanel SPEME;
    private JPanel HOPME;
    private JPanel panel1;
    private JLabel Lbl;
    private JButton retourButton;
    private JLabel NomME;
    private JLabel loginME;
    private JLabel MDPME;
    private JLabel emailME;
    private JLabel specialiteME;
    private JLabel hopitalME;

    static JFrame Suite = new JFrame("Creation Compte");

    public Fentre_Creat_2() {
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public void Suu(int indice) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Suite.add(j, BorderLayout.CENTER);
        Suite.add(panel1, BorderLayout.WEST);

        Suite.setIconImage((Resize("Images\\Medecin.jpg",20,20).getImage()));

        retourButton.setIcon(Resize("Images\\return.png",20,20));
        creerButton.setIcon(Resize("Images\\equipe.png",20,20));
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(960, 600));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
        IFMEDECIN.setVisible(indice != 1);
        NOMME.setVisible(false);
        LOGINME.setVisible(false);
        SPEME.setVisible(false);
        HOPME.setVisible(false);
        mdpME.setVisible(false);
        EMAILME.setVisible(false);
    }

    public JButton creerButton() {
        return creerButton;
    }
    public String LoginTextField() {
        return LoginTF.getText();
    }
    public String SPEField() {
        return SPETF.getText();
    }
    public String NomTextField() {
        return NomTF.getText();
    }
    public String MDPField() {
        return String.valueOf(MDPTF.getPassword());
    }
    public void SetView(boolean etat) {
        Suite.setVisible(etat);
    }
    public String getTextField1() {
        return EmailTF.getText();
    }
    public String getTextField2() {
        return HopiTF.getText();
    }

    public void renit()
    {
        j.setVisible(false);
        LoginTF.setVisible(false);
        NomTF.setVisible(false);
        creerButton.setVisible(false);
        MDPTF.setVisible(false);
        SPETF.setVisible(false);
        IFMEDECIN.setVisible(false);
        EmailTF.setVisible(false);
        HopiTF.setVisible(false);
        loginME.setVisible(false);
        MDPME.setVisible(false);
        emailME.setVisible(false);
        specialiteME.setVisible(false);
        hopitalME.setVisible(false);
        NomME.setVisible(false);
        NOMME.setVisible(false);
        LOGINME.setVisible(false);
        mdpME.setVisible(false);
        EMAILME.setVisible(false);
        SPEME.setVisible(false);
        HOPME.setVisible(false);
        Lbl.setVisible(false);
        panel1.setVisible(false);
        retourButton.setVisible(false);
    }


    public void setLoginTextField(String log) {LoginTF.setText(log);}
    public void setSPEField(String spe) {SPETF.setText(spe);}
    public void sethopField(String log) {HopiTF.setText(log);}
    public void setMdpField(String spe) {MDPTF.setText(spe);}
    public void setEmailField(String log) {EmailTF.setText(log);}
    public void setNomField(String spe) {NomTF.setText(spe);}

    public void MENOM(boolean indice,JTextField F){NOMME.setVisible(indice);Color(F,indice);}
    public void MELOGIN(boolean indice,JTextField F){LOGINME.setVisible(indice);Color(F,indice);}
    public void MEEMAIL(boolean indice,JTextField F){EMAILME.setVisible(indice);Color(F,indice);}
    public void MESPE(boolean indice,JTextField F){SPEME.setVisible(indice);Color(F,indice);}
    public void MEmdp(boolean indice,JTextField F){mdpME.setVisible(indice);Color(F,indice);}
    public void MEHOP(boolean indice,JTextField F){HOPME.setVisible(indice);Color(F,indice);}


    public void Color(JTextField F,boolean C){
        if(C==true)F.setBackground(Color.RED);
        else if (C==false)F.setBackground(Color.GREEN);
    }

    private void createUIComponents() {
        panel1 = new JPanel();
        Lbl= new JLabel(Resize("Images\\app.png",180,180));
    }

    public ImageIcon Resize(String path, int W , int H)
    {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(W, H,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    public JPanel getJ() {
        return j;
    }

    public JTextField getLoginTF() {
        return LoginTF;
    }

    public JTextField getNomTF() {
        return NomTF;
    }

    public JButton getCreerButton() {
        return creerButton;
    }

    public JPasswordField getMDPTF() {
        return MDPTF;
    }

    public JTextField getSPETF() {
        return SPETF;
    }

    public JPanel getIFMEDECIN() {
        return IFMEDECIN;
    }

    public JTextField getEmailTF() {
        return EmailTF;
    }

    public JTextField getHopiTF() {
        return HopiTF;
    }

    public JPanel getNOMME() {
        return NOMME;
    }

    public JPanel getLOGINME() {
        return LOGINME;
    }

    public JPanel getMdpME() {
        return mdpME;
    }

    public JPanel getEMAILME() {
        return EMAILME;
    }

    public JPanel getSPEME() {
        return SPEME;
    }

    public JPanel getHOPME() {
        return HOPME;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JLabel getLbl() {
        return Lbl;
    }

    public JLabel getNomME() {
        return NomME;
    }

    public JLabel getLoginME() {
        return loginME;
    }

    public JLabel getMDPME() {
        return MDPME;
    }

    public JLabel getEmailME() {
        return emailME;
    }

    public JLabel getSpecialiteME() {
        return specialiteME;
    }

    public JLabel getHopitalME() {
        return hopitalME;
    }

    public static JFrame getSuite() {
        return Suite;
    }
}



