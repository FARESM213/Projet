package Controller;

import View.Fenetre_Welcome;

import java.sql.SQLException;

public class Welcome {

    private final Fenetre_Welcome Fenetre= new  Fenetre_Welcome();

    public  static void main(String[] s)
    {
        new Welcome();
    }

    public Welcome() {
        Fenetre.Suu();

        Fenetre.getMedecin().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
                new Welcome2(0);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Fenetre.getPatient().addActionListener(e -> {
            Fenetre.SetView(false);
            try {
               // new Fenetre_test();
                new Welcome2(1);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

    }

}
