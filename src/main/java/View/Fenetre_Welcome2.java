package View;

import javax.swing.*;
import java.awt.*;

public class Fenetre_Welcome2{

    private JLabel Titre;
    private JButton Connexion;
    private JButton CreerCompte;
    private JPanel P;
    private JLabel Crr;
    private JButton retourButton;
    static JFrame Welcome2 = new JFrame("Welcome");


    public Fenetre_Welcome2() {

    }

    public void Suu() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Welcome2.setIconImage(Resize("Images\\Medecin.jpg",20,20).getImage());

        retourButton.setIcon(Resize("Images\\return.png",20,20));
        Welcome2.setContentPane(P);
        Welcome2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Welcome2.setPreferredSize(new Dimension(900,650));
        Welcome2.setResizable(false);
        Welcome2.pack();
        Welcome2.setVisible(true);
    }


    public JButton getRetourButton() {
        return retourButton;
    }

    public JButton getCreerCompte() {
        return CreerCompte;
    }

    public JButton getConnexion() {
        return Connexion;
    }

    public ImageIcon Resize(String path, int W , int H)
    {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(W, H,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    public void SetView(boolean etat)
    {
        Welcome2.setVisible(etat);
    }


    private void createUIComponents() {

        Connexion= new JButton(Resize("Images\\Pillule.png",64,64));
        CreerCompte= new JButton(Resize("Images\\Valise.png",64,64));
        Crr = new JLabel(Resize("Images\\Creation.jpg",544,385));
    }
}
