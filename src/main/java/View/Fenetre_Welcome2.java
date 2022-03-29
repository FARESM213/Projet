package View;

import javax.swing.*;
import java.awt.*;

public class Fenetre_Welcome2 {

    private JLabel Titre;
    private JButton Connexion;
    private JButton CreerCompte;
    private JPanel P;
    static JFrame Welcome2 = new JFrame("Welcome2");

    public void Suu() {

        Welcome2.setContentPane(P);
        Welcome2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Welcome2.setPreferredSize(new Dimension(500,500));
        Welcome2.setResizable(false);
        Welcome2.pack();
        Welcome2.setVisible(true);
    }

    public JButton getConnexion() {
        return Connexion;
    }

    public JButton getCreerCompte() {
        return CreerCompte;
    }


    public void SetView(boolean etat)
    {
        Welcome2.setVisible(etat);
    }
}
