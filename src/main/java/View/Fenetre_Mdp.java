package View;

import javax.swing.*;
import java.awt.*;

public class Fenetre_Mdp {

    private JPanel Wow;
    private JButton button1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    static private JFrame MDP = new JFrame("Mot de passe ");

    public Fenetre_Mdp(){}

    public void Suu()
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MDP.setIconImage(new ImageIcon("Images\\Medecin.jpg").getImage());

        MDP.setContentPane(Wow);
        MDP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MDP.setPreferredSize(new Dimension(300,300));
        MDP.setResizable(false);
        MDP.pack();
        MDP.setVisible(true);
    }

    public JButton getButton1() {
        return button1;
    }
    public String getTextField1() {
        return textField1.getText();
    }
    public String getPasswordField1() {
        return String.valueOf(passwordField1.getPassword());
    }
    public String getPasswordField2() {
        return String.valueOf(passwordField2.getPassword());
    }
    public void setMDP(boolean etat)
    {
        MDP.setVisible(etat);
    }
    public void Fentre_Erreur(String Err) {
        JOptionPane.showMessageDialog(null,Err,"Erreur",JOptionPane.INFORMATION_MESSAGE);    }

    public int Fenetre_confirm() {
        return JOptionPane.showConfirmDialog(null," Confirmez vous le changement ?","Confirmation",JOptionPane.YES_NO_OPTION);
    }
}
