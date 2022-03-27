package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class ChangementMdp {

    private JPanel Wow;
    private JButton button1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    static JFrame MDP = new JFrame("MDP");
    int trouve=-1;
    int id=0;
    String mdp="";

    public ChangementMdp(Application App) throws SQLException, ClassNotFoundException {
        this.Suu();
        button1.addActionListener(e -> {
            System.out.println(App.Pat.size());
            for ( Patient N : App.Pat)
            {
                if  ( (Objects.equals(N.Get_nom(), textField1.getText()) ))
                {
                    if  ((Objects.equals(String.valueOf(passwordField1.getPassword()), String.valueOf(passwordField2.getPassword())) ))
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
                    case 0 : {JOptionPane.showMessageDialog(null,"Le nom entré n'est pas valide ! veuillez reesayer","Erreur",JOptionPane.INFORMATION_MESSAGE);} break;
                    case 1 : {
                        if (JOptionPane.showConfirmDialog(null," Confirmez vous le changement ?","Confirmation",JOptionPane.YES_NO_OPTION)==0)
                        {
                            App.maconnexion.UpdateElement("Patient","patpassword",mdp,String.valueOf(passwordField1.getPassword()),"patno",id);
                            MDP.setVisible(false);
                            App.init();

                            Application.Login.setVisible(true);

                        }break;}
                    case 2 : {JOptionPane.showMessageDialog(null,"Le mot de passe n'as pas été correctement confirmé, reesayez ! ","Erreur",JOptionPane.INFORMATION_MESSAGE);} break;
                }
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        });
    }

    public void Suu()
    {
        MDP.setContentPane(Wow);
        MDP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MDP.setPreferredSize(new Dimension(300,300));
        MDP.setResizable(false);
        MDP.pack();
        MDP.setVisible(true);
    }
}
