package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Application {

    private JButton button1;
    public  JPanel PannelMain;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public Connexion maconnexion;
    public final List<Patient> Pat = new ArrayList<>();
    public final List<Medecin> Med = new ArrayList<>();
    public final List<Rdv> Rendezvous = new ArrayList<>();

    public static JFrame Login = new JFrame("Login");

    Suite sui;
    ChangementMdp mot;

    public void Log() throws SQLException, ClassNotFoundException
    {
        Login.setContentPane(PannelMain);
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login.setPreferredSize(new Dimension(250,200));
        Login.setResizable(false);
        Login.pack();
        Login.setVisible(true);
    }
        public void wow () throws SQLException, ClassNotFoundException {
        maconnexion = new Connexion("bdd", "root", "",Pat,Med,Rendezvous);
    }

    void init () throws SQLException, ClassNotFoundException {
        Pat.clear();
        Med.clear();
        Rendezvous.clear();
        wow();
    }

    void SuppMedecin() throws SQLException {
        String id="null";
        String mdp="null";
        int i=-1;
        for(int a=0;a<Med.size();a++)
        {
            if ( (Med.get(a).Get_log().equals(id)) && (Med.get(a).Get_mdp().equals(mdp)) )
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
    }
    void SuppPatient() throws SQLException {
        String id="null";
        String mdp="null";
        int i=-1;
        for(int a=0;a<Pat.size();a++)
        {
            if ( (Pat.get(a).Get_log().equals(id)) && (Pat.get(a).Get_mdp().equals(mdp)) )
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
        else{              System.out.println("PROBLEME");}
    }
    void SuppRdv() throws SQLException {
        int idpat=0;
        int idmed=0;
        int i=-1;
        for(int a=0;a<Rendezvous.size();a++)
        {
            if ( (Rendezvous.get(a).Get_pat()==idpat) && (Rendezvous.get(a).Get_med()==idmed) )
            {
                i=a;
                break;
            }
        }
        if (i!=-1)
        {
            maconnexion.SuppElement(Rendezvous.get(i),idmed,idpat);
            Rendezvous.remove(i);
        }
        else{System.out.println("PROBLEME");}

    }
    void AjouterMedecin(String nom, String login, String mdp, String job) throws SQLException {
        int id = Med.size();
        Med.add(new Medecin(id,nom,login,mdp,job));
        maconnexion.ajouterElement(Med.get(Med.size()-1));
    }
    void AjouterPatient(String nom, String login, String mdp) throws SQLException {
        int id = Pat.size();
        Pat.add(new Patient(id,nom,login,mdp));
        maconnexion.ajouterElement(Pat.get(Pat.size()-1));
    }
    void AjouterRdv() throws SQLException {
        int id=0;
        int med=0;
        int pat=0;
        LocalDate date=null;
        String lieu=null;
        String motif=null;
        int duree=0;
        int horaire=0;
        boolean etat=true;
        Rendezvous.add(new Rdv(id,med,pat,date,motif,duree,horaire,lieu,etat));
        maconnexion.ajouterElement(Rendezvous.get(Rendezvous.size()-1));
    }
    /*void EnvoyerEmail()
    {
        String to = "fares.messaoudi@edu.ece.fr";
        // Sender's email ID needs to be mentioned
        String from = "infoprojet582@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getInstance(properties,null);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage( (MimeMessage)session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException | javax.mail.MessagingException mex) {
            mex.printStackTrace();
        }
    }
*/

    public Application(int i) throws SQLException, ClassNotFoundException {
        init();
        System.out.println(i);
        button1.addActionListener(e -> {
            int trouve=0;
            if ( i==1)
            {
                for ( Patient N : Pat)
                {
                    if  ( (Objects.equals(N.Get_log(), textField1.getText()) ))
                    {
                        if  ( (Objects.equals(N.Get_mdp(), String.valueOf(passwordField1.getPassword())) ))
                        {
                            trouve=1;
                        }
                        else
                        {
                            trouve=2;
                        }
                        break;
                    }
                }
            }
            else {
                for (Medecin N : Med) {
                    if ((Objects.equals(N.Get_log(), textField1.getText())))
                    {
                        if ((Objects.equals(N.Get_mdp(), String.valueOf(passwordField1.getPassword())))) {
                            trouve = 1;
                        } else {
                            trouve = 2;
                        }
                        break;
                    }
                }
            }
            try {
                switch (trouve)
                {
                    case 0 :
                    {
                        JOptionPane.showMessageDialog(null,"PAS DE COMPTE, CREER UN NOUVEAU ? ","Erreur",JOptionPane.INFORMATION_MESSAGE);
                    } break;
                    case 1 :
                    {
                        Login.setVisible(false); sui= new Suite(Application.this,i);
                    } break;
                    case 2 :
                    {
                        if (JOptionPane.showConfirmDialog(null,"  Mot de passe incorrect... Voulez vous le modifier ?","Clear TextField",JOptionPane.YES_NO_OPTION)==0)
                        {
                            Login.setVisible(false);
                            mot= new ChangementMdp(Application.this);
                        }
                    } break;
                }
            } catch (SQLException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        });
    }

}
