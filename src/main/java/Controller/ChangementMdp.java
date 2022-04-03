package Controller;
import Model.Patient;
import View.Fenetre_Mdp;

import java.sql.SQLException;
import java.util.Objects;

public class ChangementMdp {

    private int trouve=-1;
    private int id=0;
    private String mdp="";

    private Fenetre_Mdp F= new Fenetre_Mdp();

    public ChangementMdp(Application App) throws SQLException, ClassNotFoundException {
        F.Suu();
        F.getButton1().addActionListener(e -> {
            for ( Patient N : App.getPat())
            {
                if  ( (Objects.equals(N.Get_nom(),F.getTextField1()) ))
                {
                    if  ((Objects.equals(F.getPasswordField1(),F.getPasswordField2() ) ))
                    {
                        trouve=1;
                        mdp=N.Get_mdp();
                        id=N.Get_id();
                    }
                    else
                    {
                        trouve=2;
                    }
                    break;
                }
                else
                {
                    trouve=0;
                }
            }
            try {
                switch (trouve)
                {
                    case 0 : {F.Fentre_Erreur("Le nom entré n'est pas valide ! veuillez reesayer");} break;
                    case 1 : {
                        if (F.Fenetre_confirm()==0)
                        {
                            App.getMaconnexion().UpdateElement("Patient","patpassword",mdp,F.getPasswordField1(),"patno",id);
                            F.setMDP(false);
                            App.init();
                            App.Set_frame(true);

                        }break;}
                    case 2 : {F.Fentre_Erreur("Le mot de passe n'as pas été correctement confirmé, reesayez !");} break;
                }
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        });
    }
}
