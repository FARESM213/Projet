package View;

import javax.swing.*;
import java.awt.*;
public class Login {

    public  JPanel PannelMain;
    public JFrame Log = new JFrame("Login");
    public JButton button1;
    public JTextField textField1;
    public JPasswordField passwordField1;

    public  void Logt()
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Log.setContentPane(PannelMain);
        Log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Log.setPreferredSize(new Dimension(250,200));
        Log.setResizable(false);
        Log.pack();
        Log.setVisible(true);
    }
    public Login()
    {
    }

    public void SetView(boolean state)
    {
        Log.setVisible(state);
    }

    public JButton getButton1() {
        return button1;
    }

    public int Fentre_Erreur() {
        return JOptionPane.showConfirmDialog(null,"PAS DE COMPTE, CREER UN NOUVEAU ? ","Erreur",JOptionPane.YES_NO_OPTION);
    }

    public int Fenetre_confirm() {
        return JOptionPane.showConfirmDialog(null,"  Mot de passe incorrect... Voulez vous le modifier ?","Error mdp",JOptionPane.YES_NO_OPTION);
    }

    public String getPasswordField1() {
        return String.valueOf(passwordField1.getPassword());
    }

    public String getTextField1() {
        return textField1.getText();
    }
}
