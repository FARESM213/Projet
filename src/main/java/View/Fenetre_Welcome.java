package View;

import javax.swing.*;
import java.awt.*;

public class Fenetre_Welcome {

    private JPanel panel1;
    private JCheckBox Medecin;
    private JCheckBox Patient;

    static JFrame Suite = new JFrame("Welcome");

    public Fenetre_Welcome(){}

    public void Suu() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Suite.setIconImage(Resize("Images\\Medecin.jpg",20,20).getImage());

        Suite.setContentPane(panel1);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(700,500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    public JCheckBox getMedecin() {
        return Medecin;
    }

    public JCheckBox getPatient() {
        return Patient;
    }

    public void SetView(boolean etat)
    {
        Suite.setVisible(etat);
    }

    private void createUIComponents() {
         Icon A = Resize("Images\\chirurgien.png",178,178);
         Medecin = new JCheckBox(A);
         A = Resize("Images\\Patient.png",182,186);

        Patient = new JCheckBox(A);
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
