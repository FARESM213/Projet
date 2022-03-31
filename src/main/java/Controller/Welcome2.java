package Controller;
import View.Fenetre_Welcome2;
import java.sql.SQLException;

public class Welcome2 {

    private final Fenetre_Welcome2 Fenetre= new Fenetre_Welcome2();

    public Welcome2(int indice) throws SQLException, ClassNotFoundException {

        Fenetre.Suu();
        Fenetre.getConnexion().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                Application App = new Application(indice);
                App.Loggg();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
        Fenetre.getCreerCompte().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                new CreationCompte(indice);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
}
}