package Controller;

import Model.Medecin;
import Model.Patient;
import View.Fentre_Creat_2;

import javax.mail.MessagingException;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreationCompte_2 {

    private final Fentre_Creat_2 Fenetre = new Fentre_Creat_2();

    public CreationCompte_2( int indice,Application App) throws SQLException, ClassNotFoundException {

        Fenetre.Suu(indice);
        if (indice==0){//MEDECIN
            Fenetre.creerButton().addActionListener(e -> {
                String str = Fenetre.MDPField();
                int buff = ISVALID(indice,App);

                if (buff == 0){try {
                    App.AjouterMedecin(Fenetre.NomTextField(), Fenetre.LoginTextField(), str, Fenetre.SPEField(), Fenetre.getTextField1(), Photo_const(), Fenetre.getTextField2());
                    App.Loggg();
                    Fenetre.renit();
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }
                    Fenetre.SetView(false);}
            });

        } else if(indice==1){//PATIENT
            Fenetre.creerButton().addActionListener(e ->
            {
                String str = Fenetre.MDPField();
                int buff=ISVALID(indice,App);

                if (buff==0){
                    try {
                        String email = Fenetre.getTextField1();
                        App.AjouterPatient(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,email,Photo_const());
                        App.Loggg();
                        Fenetre.renit();
                    }
                    catch (MessagingException | IOException ex) {ex.printStackTrace();}
                    Fenetre.SetView(false);}
            });

        }
        Fenetre.getRetourButton().addActionListener(e -> {
            App.Set_frame(true);
            Fenetre.SetView(false);
            Fenetre.renit();
        });
    }


    public int ISVALID(int indice, Application Appli){

        Medecin A = new Medecin();
        Patient B = new Patient();
        String str = Fenetre.MDPField();
        int buff=0;
        if (isValidText(Fenetre.NomTextField())){Fenetre.MENOM(false,Fenetre.getNomTF());}else {
            buff++;
            Fenetre.setNomField("");
            Fenetre.MENOM(true,Fenetre.getNomTF());}
        if (isValidEmail(Fenetre.getTextField1())){Fenetre.MEEMAIL(false,Fenetre.getEmailTF());}else {
            Fenetre.setEmailField("");
            buff++;
            Fenetre.MEEMAIL(true,Fenetre.getEmailTF());}
        if (isValidPassword(str)){Fenetre.MEmdp(false,Fenetre.getMDPTF());}else{
            Fenetre.setMdpField("");
            buff++;
            Fenetre.MEmdp(true,Fenetre.getMDPTF());}
        if (indice==1){
            if (isValidText_1(Fenetre.LoginTextField(),Appli,B)){Fenetre.MELOGIN(false,Fenetre.getLoginTF());}else{
                buff++;
                Fenetre.setLoginTextField("");
                Fenetre.MELOGIN(true,Fenetre.getLoginTF());}
        }else if (indice==0){
            if (isValidText_1(Fenetre.LoginTextField(),Appli,A)){Fenetre.MELOGIN(false,Fenetre.getLoginTF());}else{
                buff++;
                Fenetre.setLoginTextField("");
                Fenetre.MELOGIN(true,Fenetre.getLoginTF());}
            if (isValidText(Fenetre.SPEField())){Fenetre.MESPE(false,Fenetre.getSPETF());}else{
                buff++;
                Fenetre.setSPEField("");
                Fenetre.MESPE(true,Fenetre.getSPETF());}
            if (isValidText(Fenetre.getTextField2())){Fenetre.MEHOP(false,Fenetre.getHopiTF());}else {
                buff++;
                Fenetre.sethopField("");
                Fenetre.MEHOP(true,Fenetre.getHopiTF());}

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

            Pattern p = Pattern.compile(regex);

            if (password == null) {
                return false;
            }
            Matcher m = p.matcher(password);

            // Return if the password
            // matched the ReGex

            return m.matches();
        }
}
