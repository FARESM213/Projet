package Controller;

import Model.Medecin;
import Model.Patient;
import Model.Rdv;
import View.Fenetre_Profil;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class Profil
{
    private Fenetre_Profil Fenetre= new Fenetre_Profil();
    private Application Appli = new Application();
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
            S.getFenetre().setSuite(true);
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
            try {
                App.init();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Fenetre.SetView(false);
            S.getFenetre().setSuite(true);
        });

        Fenetre.getchoisirUneImageButton().addActionListener(e -> {
            if (P.getClass()==Patient.class)
            {
                try
                {
                    App.getMaconnexion().UpdateImage("Patient","Image","","","patno",((Patient) P).Get_id(),P);
                    App.init();
                    Fenetre.renit(P);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                try {
                    App.getMaconnexion().UpdateImage("Medecin","Image","","","medno",((Medecin) P).Get_id(),P);
                    App.init();
                    Fenetre.renit(P);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Fenetre.getSupprimerRdvButton().addActionListener(e -> {

            Rdv A = (Rdv) Fenetre.getList1().getSelectedValue();
            if(P.getClass()==Medecin.class)
            {
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
            }
            else
            {
                LocalTime time = LocalTime.now(ZoneId.systemDefault());
                int h =time.getHour();
                if (  (A.Get_date().compareTo(LocalDate.now())>0) || (  (A.Get_date().compareTo(LocalDate.now())==0) && (A.Get_horaire()>h)  )   )
                {
                    App.decomander(A);
                }
                else
                {
                    try {
                        App.SuppRdv(A);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    App.init();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                DefaultListModel model = (DefaultListModel) Fenetre.getList1().getModel();
                int selectedIndex = Fenetre.getList1().getSelectedIndex();
                if (selectedIndex != -1) {
                    model.remove(selectedIndex);
                }
            }

        });

        S.REnit();
    }
    public int ISVALID(int indice){
        Medecin A = new Medecin();
        Patient B = new Patient();
        String str = Fenetre.getMdp();
        int buff=0;
        if (isValidText(Fenetre.getNom())){Fenetre.MessNOM(false,Fenetre.getNom1());}else {
            buff++;
            Fenetre.MessNOM(true,Fenetre.getNom1());}
        if (isValidEmail(Fenetre.getEmail())){Fenetre.MessEMAIL(false,Fenetre.getEmail1());}else {
            buff++;
            Fenetre.MessEMAIL(true,Fenetre.getEmail1());}
        if (isValidPassword(str)){Fenetre.Messmdp(false,Fenetre.getMdp1());}else{
            buff++;
            Fenetre.Messmdp(true,Fenetre.getMdp1());}
        if (indice==1){
            if (isValidText_1(Fenetre.getLogin(),Appli,B)){Fenetre.MessLOGIN(false,Fenetre.getLogin1());}else{
                buff++;
                Fenetre.MessLOGIN(true,Fenetre.getLogin1());}
        }else if (indice==0){
            if (isValidText_1(Fenetre.getLogin(),Appli,A)){Fenetre.MessLOGIN(false,Fenetre.getLogin1());}else{
                buff++;
                Fenetre.MessLOGIN(true,Fenetre.getLogin1());}
            if (isValidText(Fenetre.getSpec())){Fenetre.MessSPE(false,Fenetre.getSpec1());}else{
                buff++;
                Fenetre.MessSPE(true,Fenetre.getSpec1());}
            if (isValidText(Fenetre.getHospi())){Fenetre.MessHOP(false,Fenetre.getHospi1());}else {
                buff++;
                Fenetre.MessHOP(true,Fenetre.getHospi1());}
        }

        return buff;
    }

    public static boolean isValidText(String obj){return obj.length() > 3;}

    public static boolean isValidText_1(String obj,Application App,Object O){

        int oui=0;
        if (O.getClass()==Medecin.class)
        {
            for (Medecin N : App.getMed()) {
                if (Objects.equals(N.Get_log(), obj))
                {
                    oui=1;
                    System.out.println(" PSEUDO DEJA PRIS ");

                }
            }
        }
        else
        {
            for (Patient N : App.getPat())
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
                + "(?=.*[@#$%^&+=!*'<>:;,])"
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

    public Fenetre_Profil getFenetre() {
        return Fenetre;
    }
}
