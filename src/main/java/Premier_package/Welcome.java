package Premier_package;

import javax.swing.*;
import java.awt.*;

import java.sql.SQLException;

public class Welcome {
    private JPanel panel1;
    private JCheckBox patientCheckBox;
    private JCheckBox medecinCheckBox;
    int i=-1;

    static JFrame Suite = new JFrame("Suite");

    public void Suu() {
        Suite.setContentPane(panel1);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    public  static void main(String[] s) {
        new Welcome();
    }

    public Welcome() {
        Suu();
        medecinCheckBox.addActionListener(e -> {
                Suite.setVisible(false);try {

                new Application(1);
                Application.Log();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        patientCheckBox.addActionListener(e -> {
            Suite.setVisible(false);
            try {
                new Application(0);
                Application.Log();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

}
