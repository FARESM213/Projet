package Controller;

import Model.Medecin;
import Model.Patient;
import View.Fenetre_Creat;
import javax.mail.MessagingException;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.*;
import java.lang.String;


public class CreationCompte {

    private final Fenetre_Creat Fenetre = new Fenetre_Creat();
    Application Appli = new Application();

    public CreationCompte( int indice) throws SQLException, ClassNotFoundException {
        Fenetre.Suu(indice);
        Appli.wow();
        if (indice==0){//MEDECIN
            Fenetre.creerButton().addActionListener(e -> {
                        String str = Fenetre.MDPField();
                        Medecin A = new Medecin();
                int buff=0;
                 if (isValidText(Fenetre.NomTextField())){Fenetre.MENOM(false);}else {
                     buff++;
                     Fenetre.setNomField("");
                     Fenetre.MENOM(true);}
                 if (isValidText_1(Fenetre.LoginTextField(),Appli,A)){Fenetre.MELOGIN(false);}else {
                     buff++;
                     Fenetre.setLoginTextField("");
                     Fenetre.MELOGIN(true);}
                 if (isValidText(Fenetre.SPEField())){Fenetre.MESPE(false);}else{
                     buff++;
                     Fenetre.setSPEField("");
                     Fenetre.MESPE(true);}
                 if (isValidText(Fenetre.getTextField1())){Fenetre.MEEMAIL(false);}else {
                     buff++;
                     Fenetre.setEmailField("");
                     Fenetre.MEEMAIL(true);}
                 if (isValidText(Fenetre.getTextField2())){Fenetre.MEHOP(false);}else {
                     buff++;
                     Fenetre.sethopField("");
                     Fenetre.MEHOP(true);}
                 if (isValidText(str)){Fenetre.MEmdp(false);}else {
                     buff++;
                     Fenetre.setMdpField("");
                     Fenetre.MEmdp(true);}

                    if (buff == 0){try {
                        Application App = new Application(indice);
                        App.AjouterMedecin(Fenetre.NomTextField(), Fenetre.LoginTextField(), str, Fenetre.SPEField(), Fenetre.getTextField1(), Photo_const(), Fenetre.getTextField2());
                        App.Loggg();
                    } catch (SQLException | ClassNotFoundException | IOException ex) {
                        ex.printStackTrace();
                    }
                    Fenetre.SetView(false);}
            });


        } else if(indice==1){//PATIENT
            Fenetre.creerButton().addActionListener(e ->
            {
                Patient B = new Patient();
                String str = Fenetre.MDPField();
                   int buff=0;
                if (isValidText(Fenetre.NomTextField())){Fenetre.MENOM(false);}else {
                    buff++;
                    Fenetre.setNomField("");
                    Fenetre.MENOM(true);}
                if (isValidText_1(Fenetre.LoginTextField(),Appli,B)){Fenetre.MELOGIN(false);}else{
                    buff++;
                    Fenetre.setLoginTextField("");
                    Fenetre.MELOGIN(true);}
                if (isValidText(Fenetre.getTextField1())){Fenetre.MEEMAIL(false);}else {
                    Fenetre.setEmailField("");
                    buff++;
                    Fenetre.MEEMAIL(true);}
                if (isValidText(str)){Fenetre.MEmdp(false);}

                else{
                    Fenetre.setMdpField("");
                    buff++;
                    Fenetre.MEmdp(true);}
                        if (buff==0){
                            try {
                                Application App = new Application(indice);
                                String email = Fenetre.getTextField1();
                                App.AjouterPatient(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,email,Photo_const());
                                App.Loggg();}
                        catch (SQLException | ClassNotFoundException | MessagingException | IOException ex) {ex.printStackTrace();}
                    Fenetre.SetView(false);}

            });

                 }else {

        }
    }


    public byte[] Photo_const() throws IOException {

        File file = new File("C:\\Users\\Usuario\\Desktop\\Projet\\sign.jpg");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte [] buf = new byte[1024];
        for (int number; (number = Objects.requireNonNull(fis).read(buf))!=-1;)
        {
            bos.write(buf,0,number);
        }
        return bos.toByteArray();
    }


    public static boolean isValidText(String obj){
        return obj.length() > 3;
    }

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
