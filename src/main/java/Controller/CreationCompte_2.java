package Controller;

import View.Fenetre_Creat;
import View.Fentre_Creat_2;

import javax.mail.MessagingException;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;


public class CreationCompte_2 {

    private final Fentre_Creat_2 Fenetre = new Fentre_Creat_2();

    public CreationCompte_2( int indice,Application App) {
        Fenetre.Suu(indice);
        if (indice==0){//MEDECIN
            Fenetre.creerButton().addActionListener(e -> {
                String str = Fenetre.MDPField();
                try {
                    App.AjouterMedecin(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,Fenetre.SPEField(),Fenetre.getTextField1(),Photo_const(),Fenetre.getTextField2());
                    App.Loggg();
                } catch (SQLException | IOException ex)
                {
                    ex.printStackTrace();
                }
                Fenetre.SetView(false);
            });

        } else {//PATIENT

            Fenetre.creerButton().addActionListener(e -> {
                String str =  Fenetre.MDPField();
                try {
                    String email = Fenetre.getTextField1();
                    App.AjouterPatient(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,email,Photo_const());
                    App.Loggg();

                } catch (MessagingException | IOException ex) {
                    ex.printStackTrace();
                }
                Fenetre.SetView(false);
            });

        }
    }

    public byte[] Photo_const() throws IOException {
        File file = new File("C:\\Users\\33783\\Documents\\sign.jpg");
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
}
