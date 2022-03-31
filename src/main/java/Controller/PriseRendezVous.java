package Controller;

import Model.Rdv;
import View.Fenetre_PriseRdv;
import java.sql.SQLException;
import java.text.ParseException;

public class PriseRendezVous
{
    Fenetre_PriseRdv Fenetre;
    public PriseRendezVous(int a, Application App, Rdv O, Suite s,Object P) throws ParseException {

        Fenetre = new Fenetre_PriseRdv(a,App,O);
        Fenetre.getPrendreLeRendezVousButton().addActionListener(e ->
        {
            try {
                String A = (String)Fenetre.getComboBox1().getSelectedItem();
                assert A != null;
                String B= String.valueOf(A.charAt(0));
                App.Update_Rdv(O,Integer.parseInt(B),Fenetre.getTextField3().getText(),P);
                Fenetre.SetView(false);
                App.init();
                s.Fenetre.SetView(true,App);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Fenetre.getAnnulerButton().addActionListener(e -> {
            Fenetre.SetView(false);
            s.Fenetre.SetView(true,App);
        });
    }
}
