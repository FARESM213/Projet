package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Welcome2 {
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

    public Welcome2(int indice) throws SQLException, ClassNotFoundException {

        Suu();
        Connexion.addActionListener(e -> {

            if (indice==1) {
                Welcome2.setVisible(false);
                try {

                    Application App = new Application(indice);
                    App.Log();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                Welcome2.setVisible(false);
                try {

                    Application App = new Application(indice);
                    App.Log();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        CreerCompte.addActionListener(e -> {

            if (indice==1)
            {
                Welcome2.setVisible(false);
                try {

                    new Welcome2(indice);

                       } catch (SQLException | ClassNotFoundException ex) {
                           ex.printStackTrace();
                       }
            }
            else
            {
                Welcome2.setVisible(false);
                try {
                           new Welcome2(indice);
                       } catch (SQLException | ClassNotFoundException ex) {
                           ex.printStackTrace();
                       }
            }
        });
}

}
