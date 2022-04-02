package Controller;

import View.Fenetre_Welcome;
import java.sql.SQLException;

public class Welcome {

    public final Fenetre_Welcome Fenetre= new  Fenetre_Welcome();

    Application App= new Application();

    public  static void main(String[] s)
    {
        new Welcome();
    }

    public Welcome() {
        Fenetre.Suu();

        Fenetre.getMedecin().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                new Welcome2(0,this);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Fenetre.getPatient().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                new Welcome2(1,this);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

    }

}
