package Controller;


import javax.mail.*;
import Model.*;
import View.Login;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;


public class Application {

    private DaoConnexionInterface maconnexion= new Connexion();
    private final List<Patient> Pat = new ArrayList<>();
    private final List<Medecin> Med = new ArrayList<>();
    private final List<Rdv> Rendezvous = new ArrayList<>();
    private int ind;


    private Login L =new Login();
    private Suite sui;
    private ChangementMdp mot;

    public Application() {}

    public void wow () throws SQLException, ClassNotFoundException
    {
        maconnexion = new Connexion("bdd", "root", "",Pat,Med,Rendezvous);
    }

    public void Update_Medecin(Medecin A,Medecin B) throws SQLException
    {
        maconnexion.UpdateElement("Medecin","medname",A.Get_nom(),B.Get_nom(),"medno",A.Get_id());
        maconnexion.UpdateElement("Medecin","medlogin",A.Get_log(),B.Get_log(),"medno",A.Get_id());
        maconnexion.UpdateElement("Medecin","medpassword",A.Get_mdp(),B.Get_mdp(),"medno",A.Get_id());
        maconnexion.UpdateElement("Medecin","medjob",A.Get_job(),B.Get_job(),"medno",A.Get_id());
        maconnexion.UpdateElement("Medecin","email",A.Get_mail(),B.Get_mail(),"medno",A.Get_id());
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Update_Patient(Patient A,Patient B) throws SQLException {

        maconnexion.UpdateElement("Patient","patname",A.Get_nom(),B.Get_nom(),"patno",A.Get_id());
        maconnexion.UpdateElement("Patient","patlogin",A.Get_log(),B.Get_log(),"patno",A.Get_id());
        maconnexion.UpdateElement("Patient","patpassword",A.Get_mdp(),B.Get_mdp(),"patno",A.Get_id());
        maconnexion.UpdateElement("Patient","email",A.Get_Mail(),B.Get_Mail(),"patno",A.Get_id());
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void Update_Rdv(Rdv A, Object selectedItem, String text, Object p) throws SQLException {

        int durree=A.Get_horaire()+(Integer)selectedItem;
        maconnexion.ExecuteRequest("UPDATE Rendez_vous SET etat=0 WHERE rdvno='"+A.Get_id()+"'");
        maconnexion.ExecuteRequest("UPDATE Rendez_vous SET rdv_duree='"+selectedItem+"' WHERE rdvno='"+A.Get_id()+"'");
        maconnexion.ExecuteRequest("UPDATE Rendez_vous SET rdv_motif='"+text+"' WHERE rdvno='"+A.Get_id()+"'");
        maconnexion.ExecuteRequest("DELETE FROM Rendez_vous WHERE rdv_date='"+A.Get_date()+"' AND rdv_horaire BETWEEN '"+A.Get_horaire() +"' AND '"+ durree+"' AND rdvno!='"+A.Get_id()+"'");

        if (Patient.class== p.getClass())
        {
            maconnexion.ExecuteRequest("UPDATE Rendez_vous SET patno='"+((Patient) p).Get_id()+"'");
        }
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init() throws SQLException, ClassNotFoundException
        {
            wow();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now(ZoneId.systemDefault());
            int h =time.getHour();
            maconnexion.ExecuteRequest("UPDATE rendez_vous SET etat='0' WHERE CASE " +
                    "WHEN rdv_date ='"+date+"' THEN rdv_date <= '"+date+"' AND rdv_horaire <='"+h+"'" +
                    "WHEN rdv_date <'"+date+"' THEN rdv_date < '"+date+"'"+
                    " END");
            Pat.clear();
            Med.clear();
            Rendezvous.clear();
            wow();
    }

    public void SuppMedecin(int id, String mdp) throws SQLException
    {
        int i=-1;
        for(int a=0;a<Med.size();a++)
        {
            if ( (Med.get(a).Get_id()==(id)) && (Med.get(a).Get_mdp().equals(mdp)) )
            {
                i=a;
                break;
            }
        }
        if (i!=-1)
        {
            maconnexion.SuppElement(Med.get(i),id,mdp);
            Med.remove(i);
        }
        else
        {
            System.out.println("PROBLEME");
        }
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void SuppPatient(int id, String mdp) throws SQLException {

        int i=-1;
        for(int a=0;a<Pat.size();a++)
        {
            if ( Pat.get(a).Get_id()==id)
            {
                i=a;
                break;
            }
        }
        if (i!=-1)
        {

            maconnexion.SuppElement(Pat.get(i),id,mdp);
            Pat.remove(i);
        }
        else{System.out.println("PROBLEME");}


        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void SuppRdv(Rdv R) throws SQLException
    {
        int i=-1;
        for(int a=0;a<Rendezvous.size();a++)
        {
            if ((Rendezvous.get(a).Get_id()==R.Get_id()))
            {
                i=a;
                break;
            }
        }
        if (i!=-1)
        {
            maconnexion.SuppElement(Rendezvous.get(i),R.Get_id(),R.Get_pat());
            Rendezvous.remove(i);
        }
        else
        {
            System.out.println("PROBLEME");
        }

        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void AjouterMedecin(String nom, String login, String mdp, String job,String mail,byte [ ] img,String hopital) throws SQLException {

        int id = Med.get(Med.size()-1).Get_id()+1;
        Med.add(new Medecin(id,nom,login,mdp,job,mail,img,hopital));
        maconnexion.ajouterElement(Med.get(Med.size()-1));
        EnvoyerEmail(mail,nom);
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AjouterPatient(String nom, String login, String mdp,String email,byte[] img) throws UnsupportedEncodingException, MessagingException
    {
        int id = Pat.get(Pat.size()-1).Get_id()+1;
        Pat.add(new Patient(id,nom,login,mdp,email,img));
        try {
            maconnexion.ajouterElement(Pat.get(Pat.size()-1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EnvoyerEmail(email,nom);
        try {
            init();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    void AjouterRdv(int id , int med , Integer pat , LocalDate date , String lieu , String motif , String type , int duree , int horaire, boolean etat) throws SQLException
    {
        Rendezvous.add(new Rdv(id,med,0,date,motif,duree,horaire,lieu,etat,type));
        maconnexion.ajouterElement(Rendezvous.get(Rendezvous.size()-1));
        try {
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void EnvoyerEmail(String Recever,String nom)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.MyConstants.MY_EMAIL,MailConfig.MyConstants.MY_PASSWORD);
            }
        });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailConfig.MyConstants.MY_EMAIL));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(Recever));
            message.setSubject("Confirmation");
            message.setText("Bonjour  "+nom +"\n Nous vous confirmons la creation de votre compte.");
            Transport.send(message);
        }catch (MessagingException mex) {mex.printStackTrace();}
    }

    public Application(int i,Welcome2 w) throws SQLException, ClassNotFoundException {
        init();
        L.Logt();
        L.getRetourButton().addActionListener(e -> {
            Set_frame(false);
            w.getFenetreW().SetView(true);
        });
        L.getButton1().addActionListener(e -> {
            int trouve=0;
            if ( i==1)
            {
                for ( Patient N : Pat)
                {
                    if  ( (Objects.equals(N.Get_log(), L.getTextField1()) ))
                    {
                        if  ( (Objects.equals(N.Get_mdp(), L.getPasswordField1()) ))
                        {
                            trouve=1;
                        }
                        else
                        {
                            trouve=2;
                        }
                        break;
                    }
                    else
                    {
                        ind++;
                    }
                }
            }
            else {
                for (Medecin N : Med) {
                    if ((Objects.equals(N.Get_log(), L.getTextField1())))
                    {
                        if ((Objects.equals(N.Get_mdp(),  L.getPasswordField1() ))) {
                            trouve = 1;
                        } else {
                            trouve = 2;
                        }
                        break;
                    }
                    else
                    {
                        ind++;
                    }
                }
            }
            try {
                switch (trouve)
                {
                    case 0 :
                    {
                        if (L.Fentre_Erreur()==0)
                        {
                            L.SetView(false);
                            new CreationCompte_2(i,this);
                        }
                        ind=0;
                        trouve=4;
                    } break;
                    case 1 :
                    {
                        L.SetView(false);
                        if(i==1)
                             sui= new Suite(Application.this,i,Pat.get(ind));
                        else
                            sui= new Suite(Application.this,i,Med.get(ind));

                        ind=0;
                        trouve=4;

                    } break;
                    case 2 :
                    {
                        if (L.Fenetre_confirm()==0)
                        {
                            L.SetView(false);
                            mot= new ChangementMdp(Application.this);
                        }
                        ind=0;
                        trouve=4;

                    } break;
                }
            } catch (SQLException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        });
    }

     public void Set_frame(boolean state)
    {
        L.SetView(state);
        L.getPasswordField().setText("");
    }

     public void Loggg()
    {
        L.Logt();
    }

    @Override
    public String toString() {
        return "Application{" +
                ", Pat=" + Pat +
                ", Med=" + Med +
                ", Rendezvous=" + Rendezvous +
                '}';
    }


    public List<Medecin> getMed() {
        return Med;
    }

    public List<Patient> getPat() {
        return Pat;
    }

    public List<Rdv> getRendezvous() {
        return Rendezvous;
    }

    public DaoConnexionInterface getMaconnexion() {
        return maconnexion;
    }

    public void decomander(Rdv O)
    {
        try {

            LocalTime time = LocalTime.now(ZoneId.systemDefault());
            int h =time.getHour();
            if (O.Get_date().compareTo(LocalDate.now())>0)
            {
                maconnexion.ExecuteRequest("UPDATE Rendez_vous SET etat='1' WHERE rdvno='"+O.Get_id()+"' ");
                maconnexion.ExecuteRequest("UPDATE Rendez_vous SET patno='0' WHERE rdvno='"+O.Get_id()+"' ");
                maconnexion.ExecuteRequest("UPDATE Rendez_vous SET rdv_motif=' ' WHERE rdvno='"+O.Get_id()+"' ");
                maconnexion.ExecuteRequest("UPDATE Rendez_vous SET rdv_duree='0' WHERE rdvno='"+O.Get_id()+"' ");
            }
            else if (O.Get_date().compareTo(LocalDate.now())==0)
            {
                if (O.Get_horaire()>h)
                {
                    maconnexion.ExecuteRequest("UPDATE Rendez_vous SET etat='1' WHERE rdvno='"+O.Get_id()+"' ");
                    maconnexion.ExecuteRequest("UPDATE Rendez_vous SET patno='0' WHERE rdvno='"+O.Get_id()+"' ");
                    maconnexion.ExecuteRequest("UPDATE Rendez_vous SET rdv_motif=' ' WHERE rdvno='"+O.Get_id()+"' ");
                    maconnexion.ExecuteRequest("UPDATE Rendez_vous SET rdv_duree='0' WHERE rdvno='"+O.Get_id()+"' ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
