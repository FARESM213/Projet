package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Welcome2 {
    private JLabel Titre;
    private JButton Connexion;
    private JButton CreerCompte;
    private JPanel P;

    static JFrame Suite = new JFrame("Suite");

    public void Suu() {

        Suite.setContentPane(P);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    public Welcome2(int indice) throws SQLException, ClassNotFoundException {

        Suu();
        Connexion.addActionListener(e -> {

            if (indice==1) {
                Suite.setVisible(false);
                try {

                    Application App = new Application(indice);
                    App.Log();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                Suite.setVisible(false);
                try {

                    Application App = new Application(indice);
                    App.Log();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
               CreerCompte.addActionListener(e -> {

                   if (indice==1) {
                       Suite.setVisible(false);
                       try {

                           new CreationCompte(indice);

                       } catch (SQLException | ClassNotFoundException ex) {
                           ex.printStackTrace();
                       }
                   } else {
                       Suite.setVisible(false);
                       try {

                           new CreationCompte(indice);

                       } catch (SQLException | ClassNotFoundException ex) {
                           ex.printStackTrace();
                       }
                   }
        });




}

}
