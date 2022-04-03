package Controller;
import View.Fenetre_Welcome2;

import java.sql.SQLException;

public class Welcome2 {

    final private Fenetre_Welcome2 Fenetre= new Fenetre_Welcome2();

    public Welcome2(int indice, Welcome welcome) throws SQLException, ClassNotFoundException {

        Fenetre.Suu();
        Fenetre.getConnexion().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                Application App = new Application(indice,this);
                App.Loggg();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
        Fenetre.getCreerCompte().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                new CreationCompte(indice,this);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Fenetre.getRetourButton().addActionListener(e -> {
            Fenetre.SetView(false);
            welcome.getFenetre().SetView(true);
        });
}
    public Fenetre_Welcome2 getFenetreW() {
        return Fenetre;
    }
}