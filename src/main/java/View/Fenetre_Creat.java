package View;

import javax.swing.*;
import java.awt.*;

public class Fenetre_Creat {

    private JPanel j;
    private JTextField LoginTextField;
    private JTextField NomTextField;
    private JButton creerButton;
    private JPasswordField MDPField;
    private JTextField SPEField;
    private JPanel IFMEDECIN;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel loginME;
    private JLabel MDPME;
    private JLabel emailME;
    private JLabel specialiteME;
    private JLabel hopitalME;
    private JLabel NomME;
    private JPanel NOMME;
    private JPanel LOGINME;
    private JPanel mdpME;
    private JPanel EMAILME;
    private JPanel SPEME;
    private JPanel HOPME;
    private JLabel Lbl;
    private JPanel panel1;
    private JButton retourButton;

    static JFrame Suite = new JFrame("Creation compte");

    public Fenetre_Creat() {
    }

    public void Suu(int indice) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Suite.setIconImage(Resize("Images\\Medecin.jpg",20,20).getImage());

        creerButton.setIcon(Resize("Images\\equipe.png",20,20));
        retourButton.setIcon(Resize("Images\\return.png",20,20));
        Suite.add(j, BorderLayout.CENTER);
        Suite.add(panel1, BorderLayout.WEST);
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
        return LoginTextField.getText();
    }
    public String SPEField() {
        return SPEField.getText();
    }
    public String NomTextField() {
        return NomTextField.getText();
    }
    public String MDPField() {
        return String.valueOf(MDPField.getPassword());
    }
    public void SetView(boolean etat) {
        Suite.setVisible(etat);
    }
    public String getTextField1() {
        return textField1.getText();
    }
    public String getTextField2() {
        return textField2.getText();
    }

    public JTextField LoginTextField1() {
         return LoginTextField;
    }
    public JTextField SPEField1() {
        return SPEField;
    }
    public JTextField NomTextField1() {
        return NomTextField;
    }
    public JPasswordField MDPField1() {
        return MDPField;
    }
    public JTextField getTextField11() {
        return textField1;
    }
    public JTextField getTextField22() {
        return textField2;
    }

    public void setLoginTextField(String log) {LoginTextField.setText(log);}
    public void setSPEField(String spe) {SPEField.setText(spe);}
    public void sethopField(String log) {textField2.setText(log);}
    public void setMdpField(String spe) {MDPField.setText(spe);}
    public void setEmailField(String log) {textField1.setText(log);}
    public void setNomField(String spe) {NomTextField.setText(spe);}

    public void MENOM(boolean indice,JTextField F){NOMME.setVisible(indice);Color(F,indice);}
    public void MELOGIN(boolean indice,JTextField F){LOGINME.setVisible(indice);Color(F,indice);}
    public void MEEMAIL(boolean indice,JTextField F){EMAILME.setVisible(indice);Color(F,indice);}
    public void MESPE(boolean indice,JTextField F){SPEME.setVisible(indice);Color(F,indice);}
    public void MEmdp(boolean indice,JTextField F){mdpME.setVisible(indice);Color(F,indice);}
    public void MEHOP(boolean indice,JTextField F){HOPME.setVisible(indice);Color(F,indice);}


    public void Color(JTextField F,boolean C){
        if(C)F.setBackground(Color.RED);
        else F.setBackground(Color.GREEN);
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

    public void renit()
    {
        j.setVisible(false);
        LoginTextField.setVisible(false);
        NomTextField.setVisible(false);
        creerButton.setVisible(false);
        MDPField.setVisible(false);
        SPEField.setVisible(false);
        IFMEDECIN.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
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

    public JButton getRetourButton() {
        return retourButton;
    }
}


