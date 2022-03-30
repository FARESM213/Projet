package Controller;

import View.Fenetre_Creat;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;


public class CreationCompte {

    private final Fenetre_Creat Fenetre = new Fenetre_Creat();

    public CreationCompte( int indice) throws SQLException, ClassNotFoundException {
        Fenetre.Suu(indice);
        if (indice==0){//MEDECIN
            Fenetre.creerButton().addActionListener(e -> {
                String str = Fenetre.MDPField();
                try {
                    Application App = new Application(indice);
                    App.AjouterMedecin(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,Fenetre.SPEField(),Fenetre.getTextField1());
                    App.Loggg();
                } catch (SQLException | ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                Fenetre.SetView(false);
            });

        } else {//PATIENT

            Fenetre.creerButton().addActionListener(e -> {
                String str =  Fenetre.MDPField();
                try {
                    Application App = new Application(indice);
                    String email = Fenetre.getTextField1();
                    App.AjouterPatient(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,email);
                    App.Loggg();
                } catch (SQLException | ClassNotFoundException | UnsupportedEncodingException | MessagingException ex) {
                    ex.printStackTrace();
                }
                Fenetre.SetView(false);
            });

        }
    }
}
