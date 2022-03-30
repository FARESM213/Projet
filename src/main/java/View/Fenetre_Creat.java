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

    static JFrame Suite = new JFrame("Suite");

    public Fenetre_Creat(){}


    public void Suu(int indice) {

        Suite.setContentPane(j);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
        IFMEDECIN.setVisible(indice != 1);

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

    public void SetView(boolean etat)
    {
        Suite.setVisible(etat);
    }

    public String getTextField1() {
        return textField1.getText();
    }
}
