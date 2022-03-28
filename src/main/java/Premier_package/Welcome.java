package Premier_package;

import javax.swing.*;
import java.awt.*;

import java.sql.SQLException;

public class Welcome {
    private JPanel panel1;
    private JCheckBox Medecin;
    private JCheckBox Patient;

    int i=-1;

    static JFrame Suite = new JFrame("Suite");

    public void Suu() {
        Suite.setContentPane(panel1);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,250));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    public  static void main(String[] s) {
        new Welcome();
    }

    public Welcome() {

        Suu();
        Medecin.addActionListener(e -> {
                Suite.setVisible(false);try {

                new Welcome2(1);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Patient.addActionListener(e -> {
            Suite.setVisible(false);
            try {
                new Welcome2(0);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });


    }

}
