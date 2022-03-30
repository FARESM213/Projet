package Controller;

import View.Fenetre_Creat;
import javax.mail.MessagingException;
import java.io.*;
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
                    App.AjouterMedecin(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,Fenetre.SPEField(),Fenetre.getTextField1(),Photo_const());
                    App.Loggg();
                } catch (SQLException | ClassNotFoundException | IOException ex)
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
                    App.AjouterPatient(Fenetre.NomTextField(),Fenetre.LoginTextField(),str,email,Photo_const());
                    App.Loggg();

                } catch (SQLException | ClassNotFoundException | MessagingException | IOException ex) {
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
        for (int number; (number = fis.read(buf))!=-1;)
        {
            bos.write(buf,0,number);
        }
        return bos.toByteArray();
    }
}
