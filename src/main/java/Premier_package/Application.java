package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Application {

    private JButton button1;
    public JPanel PannelMain;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public Connexion maconnexion;
    public final List<Patient> Pat = new ArrayList<>();
    public final List<Medecin> Med = new ArrayList<>();
    public final List<Rdv> Rendezvous = new ArrayList<>();
    public static JFrame Login = new JFrame("Login");

    Suite sui;
    ChangementMdp mot;

    public void main(String[] s) throws SQLException, ClassNotFoundException {
        new Application(0);
    }

    public static void Log() throws SQLException, ClassNotFoundException
    {
        Login.setContentPane(new Application(1).PannelMain);
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login.setPreferredSize(new Dimension(500,200));
        Login.setResizable(true);
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
        else{             System.out.println("PROBLEME");}

    }
    void AjouterMedecin(String NOM, String LOGIN, String MDP, String JOB) throws SQLException {
        int id = 0;
        Med.add(new Medecin(id,NOM,LOGIN,MDP,JOB));
        maconnexion.ajouterElement(Med.get(Med.size()-1));
    }
    void AjouterPatient(String NOM, String LOGIN, String MDP) throws SQLException {
        int id = 0;
        Pat.add(new Patient(id,NOM,LOGIN,MDP));
        maconnexion.ajouterElement(Pat.get(Pat.size()-1));
    }
    void AjouterRdv() throws SQLException {
        int id=0;
        int med=0;
        int pat=0;
        String date=null;
        String lieu=null;
        String motif=null;
        int duree=0;
        int horaire=0;
        boolean etat=true;
        Rendezvous.add(new Rdv(id,med,pat,date,motif,duree,horaire,lieu,etat));
        maconnexion.ajouterElement(Rendezvous.get(Rendezvous.size()-1));
    }

    public Application(int i) throws SQLException, ClassNotFoundException {
        init();
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
                            break;
                        }
                        else
                        {
                            trouve=2;
                            break;
                        }
                    }
                }
            }
            else {
                for (Medecin N : Med) {
                    if ((Objects.equals(N.Get_log(), textField1.getText())))
                    {
                        if ((Objects.equals(N.Get_mdp(), String.valueOf(passwordField1.getPassword())))) {
                            trouve = 1;
                            break;
                        } else {
                            trouve = 2;
                            break;
                        }
                    }
                }
            }
            try {
                switch (trouve)
                {
                    case 0 : {JOptionPane.showMessageDialog(null,"PAS DE COMPTE, CREER UN NOUVEAU ? ","Erreur",JOptionPane.INFORMATION_MESSAGE);} break;
                    case 1 : {Login.setVisible(false); sui= new Suite(Application.this,i);} break;
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
