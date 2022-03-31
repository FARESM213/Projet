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

    static JFrame Suite = new JFrame("Suite");

    public Fenetre_Creat() {
    }

    public void Suu(int indice) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Suite.setContentPane(j);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500, 500));
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

    public void setLoginTextField(String log) {LoginTextField.setText(log);}
    public void setSPEField(String spe) {SPEField.setText(spe);}
    public void sethopField(String log) {textField2.setText(log);}
    public void setMdpField(String spe) {MDPField.setText(spe);}
    public void setEmailField(String log) {textField1.setText(log);}
    public void setNomField(String spe) {NomTextField.setText(spe);}

    public void MENOM(boolean indice){NOMME.setVisible(indice);}
    public void MELOGIN(boolean indice){LOGINME.setVisible(indice);}
    public void MEEMAIL(boolean indice){EMAILME.setVisible(indice);}
    public void MESPE(boolean indice){SPEME.setVisible(indice);}
    public void MEmdp(boolean indice){mdpME.setVisible(indice);}
    public void MEHOP(boolean indice){HOPME.setVisible(indice);}


}
