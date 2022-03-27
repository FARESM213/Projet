package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Welcome {
    private JPanel panel1;
    private JCheckBox patientCheckBox;
    private JCheckBox medecinCheckBox;
    int i=-1;

    static JFrame Suite = new JFrame("Suite");

    public void Suu() throws SQLException, ClassNotFoundException
    {
        Suite.setContentPane(panel1);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    public  static void main(String[] s) throws SQLException, ClassNotFoundException {
        Welcome tst = new Welcome();
    }

    public Welcome() throws SQLException, ClassNotFoundException {
        Suu();
        medecinCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Suite.setVisible(false);try {

                    Application App = new Application(1);
                    App.Log();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        patientCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Suite.setVisible(false);
                try {
                    Application App = new Application(0);
                    App.Log();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
