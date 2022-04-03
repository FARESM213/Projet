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

    public CreationCompte( int indice,Welcome2 w) throws SQLException, ClassNotFoundException {
        Fenetre.Suu(indice);
        Appli.wow();
        if (indice==0){//MEDECIN
            Fenetre.creerButton().addActionListener(e -> {
                        int buff = ISVALID(indice);
                        String str = Fenetre.MDPField();

                    if (buff == 0){try {
                        Application App = new Application(indice,w);
                        App.AjouterMedecin(Fenetre.NomTextField(), Fenetre.LoginTextField(), str, Fenetre.SPEField(), Fenetre.getTextField1(), Photo_const(), Fenetre.getTextField2());
                        App.Loggg();
                        Fenetre.renit();
                    } catch (SQLException | ClassNotFoundException | IOException ex) {
                        ex.printStackTrace();
                    }
                    Fenetre.SetView(false);}
            });


        } else if(indice==1){//PATIENT
            Fenetre.creerButton().addActionListener(e ->
            {
                String str = Fenetre.MDPField();
                int buff = ISVALID(indice);
                        if (buff==0){
                            try {
                                Application App = new Application(indice,w);
                                String email = Fenetre.getTextField1();
                                App.AjouterPatient(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,email,Photo_const());
                                App.Loggg();
                                Fenetre.renit();
                            }
                        catch (SQLException | ClassNotFoundException | MessagingException | IOException ex) {ex.printStackTrace();}
                    Fenetre.SetView(false);}

            });

                 }

        Fenetre.getRetourButton().addActionListener(e -> {
            w.Fenetre.SetView(true);
            Fenetre.SetView(false);
            Fenetre.renit();

        });
    }


    public int ISVALID(int indice){
        Medecin A = new Medecin();
        Patient B = new Patient();
        String str = Fenetre.MDPField();
        int buff=0;
        if (isValidText(Fenetre.NomTextField())){Fenetre.MENOM(false,Fenetre.NomTextField);}else {
            buff++;
            Fenetre.setNomField("");
            Fenetre.MENOM(true,Fenetre.NomTextField);}
        if (isValidEmail(Fenetre.getTextField1())){Fenetre.MEEMAIL(false,Fenetre.textField1);}else {
            Fenetre.setEmailField("");
            buff++;
            Fenetre.MEEMAIL(true,Fenetre.textField1);}
        if (isValidPassword(str)){Fenetre.MEmdp(false,Fenetre.MDPField);}else{
            Fenetre.setMdpField("");
            buff++;
            Fenetre.MEmdp(true,Fenetre.MDPField);}
        if (indice==1){
            if (isValidText_1(Fenetre.LoginTextField(),Appli,B)){Fenetre.MELOGIN(false,Fenetre.LoginTextField);}else{
                buff++;
                Fenetre.setLoginTextField("");
                Fenetre.MELOGIN(true,Fenetre.LoginTextField);}
        }else if (indice==0){
            if (isValidText_1(Fenetre.LoginTextField(),Appli,A)){Fenetre.MELOGIN(false,Fenetre.LoginTextField);}else{
                buff++;
                Fenetre.setLoginTextField("");
                Fenetre.MELOGIN(true,Fenetre.LoginTextField);}
            if (isValidText(Fenetre.SPEField())){Fenetre.MESPE(false,Fenetre.SPEField);}else{
                buff++;
                Fenetre.setSPEField("");
                Fenetre.MESPE(true,Fenetre.SPEField);}
            if (isValidText(Fenetre.getTextField2())){Fenetre.MEHOP(false,Fenetre.textField2);}else {
                buff++;
                Fenetre.sethopField("");
                Fenetre.MEHOP(true,Fenetre.textField2);}
        }

        return buff;
    }

    public byte[] Photo_const() throws IOException {

        File file = new File("Images\\chirurgien.png");

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

        Matcher m = p.matcher(password);

        return m.matches();}
}
