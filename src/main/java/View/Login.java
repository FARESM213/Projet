package View;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel{

    private  JPanel PannelMain;
    public JFrame Log = new JFrame("Login");

    private JButton button1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel Img;
    private JButton retourButton;

    public  void Logt()
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        createUIComponents();
        Log.setIconImage((Resize("Images\\Medecin.jpg",20,20).getImage()));
        retourButton.setIcon(Resize("Images\\return.png",20,20));
        Log.setContentPane(PannelMain);
        Log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Log.setPreferredSize(new Dimension(700,600));
        Log.setResizable(false);
        Log.pack();
        Log.setVisible(true);
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public Login()
    {
    }

    public ImageIcon Resize(String path, int W , int H)
    {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(W, H,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
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

    private void createUIComponents() {
        Img= new JLabel(Resize("Images\\Medecin.jpg",300,300));
    }

    public JPasswordField getPasswordField() {
        return  passwordField1;
    }
}
