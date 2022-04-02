package Controller;

import Model.Medecin;
import Model.Patient;
import Model.Rdv;
import View.Fenetre_Profil;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class Profil
{
    public Fenetre_Profil Fenetre= new Fenetre_Profil();
    Application Appli = new Application();
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

                int buff = ISVALID(0);

                if (buff==0){
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

            }
            else {

                int buff = ISVALID(1);
                if (buff==0){try
                {
                    Patient B= new Patient(((Patient) P).Get_id(),Fenetre.getNom(),Fenetre.getLogin(),Fenetre.getMdp(),Fenetre.getEmail(),((Patient) P).getImage());
                    App.Update_Patient((Patient) P, B);
                    ((Patient) P).set(B);
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }}

            }
           /* try {
                App.init();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Fenetre.SetView(false);
            S.Fenetre.setSuite(true);*/
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

        Fenetre.getSupprimerRdvButton().addActionListener(e -> {

            Rdv A = (Rdv) Fenetre.getList1().getSelectedValue();
            try {
                App.SuppRdv(A);
                App.init();
                DefaultListModel model = (DefaultListModel) Fenetre.getList1().getModel();
                int selectedIndex = Fenetre.getList1().getSelectedIndex();
                if (selectedIndex != -1) {
                    model.remove(selectedIndex);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });



    }

    public int ISVALID(int indice){
        Medecin A = new Medecin();
        Patient B = new Patient();
        String str = Fenetre.getMdp();
        int buff=0;
        if (isValidText(Fenetre.getNom())){Fenetre.MessNOM(false,Fenetre.Nom);}else {
            buff++;
            Fenetre.MessNOM(true,Fenetre.Nom);}
        if (isValidEmail(Fenetre.getEmail())){Fenetre.MessEMAIL(false,Fenetre.Email);}else {
            buff++;
            Fenetre.MessEMAIL(true,Fenetre.Email);}
        if (isValidPassword(str)){Fenetre.Messmdp(false,Fenetre.mdp);}else{
            buff++;
            Fenetre.Messmdp(true,Fenetre.mdp);}
        if (indice==1){
            if (isValidText_1(Fenetre.getLogin(),Appli,B)){Fenetre.MessLOGIN(false,Fenetre.Login);}else{
                buff++;
                Fenetre.MessLOGIN(true,Fenetre.Login);}
        }else if (indice==0){
            if (isValidText_1(Fenetre.getLogin(),Appli,A)){Fenetre.MessLOGIN(false,Fenetre.Login);}else{
                buff++;
                Fenetre.MessLOGIN(true,Fenetre.Login);}
            if (isValidText(Fenetre.getSpec())){Fenetre.MessSPE(false,Fenetre.Spec);}else{
                buff++;
                Fenetre.MessSPE(true,Fenetre.Spec);}
            if (isValidText(Fenetre.getHospi())){Fenetre.MessHOP(false,Fenetre.hospi);}else {
                buff++;
                Fenetre.MessHOP(true,Fenetre.hospi);}

        }

        return buff;
    }

    public static boolean isValidText(String obj){return obj.length() > 3;}

    public static boolean isValidText_1(String obj,Application App,Object O){

        int oui=0;
        if (O.getClass()==Medecin.class)
        {
            for (Medecin N : App.Med) {
                if (Objects.equals(N.Get_log(), obj))
                {
                    oui=1;
                    System.out.println(" PSEUDO DEJA PRIS ");

                }
            }
        }
        else
        {
            for (Patient N : App.Pat)
            {
                if (Objects.equals(N.Get_log(), obj))
                {
                    oui=1;
                    System.out.println(" PSEUDO DEJA PRIS ");
                }
            }
        }
        return (obj.length() > 3) && (oui == 0);
    }


    public static boolean isValidEmail(String password){

        for (int i=0; i<password.length(); i++){
            char  a  =  password . charAt ( i );
            if('@' == a){
                return true;
            }
        }

        return false;
    }


    public static boolean isValidPassword(String password)
    {

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex

        return m.matches();}
}
