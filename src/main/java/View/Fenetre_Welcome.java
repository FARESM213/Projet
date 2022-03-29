package View;

import javax.swing.*;
import java.awt.*;

public class Fenetre_Welcome {

    private JPanel panel1;
    private JCheckBox Medecin;
    private JCheckBox Patient;

    static JFrame Suite = new JFrame("Suite");

    public Fenetre_Welcome(){}

    public void Suu() {
        Suite.setContentPane(panel1);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,250));
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
}
