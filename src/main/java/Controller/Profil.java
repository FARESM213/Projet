package Controller;

import Model.Medecin;
import Model.Patient;
import View.Fenetre_Profil;

import java.io.IOException;
import java.sql.SQLException;

public class Profil
{
    public Fenetre_Profil Fenetre= new Fenetre_Profil();
    public Profil(Application App, Object P,Suite S)
    {
        Fenetre.Suu(App, P);
        Fenetre.getSupprimerCompteButton().addActionListener(e -> {
            if (Fenetre.Fenetre_confirm(" Voulez vous vraiment supprimer votre compte ? ")==0)
            {
                try
                {
                    if (P.getClass()==Patient.class)
                    {
                        App.SuppPatient(((Patient) P).Get_id(),((Patient) P).Get_mdp());
                    }
                    else if (P.getClass()== Medecin.class)
                    {
                        App.SuppMedecin(((Medecin) P).Get_id(),((Medecin) P).Get_mdp());
                    }
                    Fenetre.SetView(false);
                    App.Set_frame(true);
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        Fenetre.getModifierInformationsButton().addActionListener(e -> Fenetre.Visible(P));
        Fenetre.getRetourButton().addActionListener(e -> {
            Fenetre.SetView(false);
            S.Fenetre.setSuite(true);
        });
        Fenetre.getAfficherRdvRadioButton().addActionListener(e -> Fenetre.radio(App,P));
        Fenetre.getModifierButton().addActionListener(e -> {
            if (P.getClass()==Medecin.class) {
                Medecin B= new Medecin(((Medecin) P).Get_id(),Fenetre.getNom(),Fenetre.getLogin(),Fenetre.getMdp(),Fenetre.getSpec(),Fenetre.getEmail(),((Medecin) P).getImage(),Fenetre.getHospi());
                try
                {
                    App.Update_Medecin((Medecin) P, B);
                    ((Medecin) P).set(B);
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                try
                {
                    Patient B= new Patient(((Patient) P).Get_id(),Fenetre.getNom(),Fenetre.getLogin(),Fenetre.getMdp(),Fenetre.getEmail(),((Patient) P).getImage());
                    App.Update_Patient((Patient) P, B);
                    ((Patient) P).set(B);
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                App.init();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Fenetre.getchoisirUneImageButton().addActionListener(e -> {
            if (P.getClass()==Patient.class)
            {
                try
                {
                    App.maconnexion.UpdateImage("Patient","Image","","","patno",((Patient) P).Get_id(),P);
                    App.init();
                    Fenetre.renit(P);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                try {
                    App.maconnexion.UpdateImage("Medecin","Image","","","medno",((Medecin) P).Get_id(),P);
                    App.init();
                    Fenetre.renit(P);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
