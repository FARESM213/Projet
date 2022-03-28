package Premier_package;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class CreationCompte {
    private JPanel j;
    private JTextField LoginTextField;
    private JTextField NomTextField;
    private JButton creerButton;
    private JPasswordField MDPField;
    private JTextField SPEField;
    private JPanel IFMEDECIN;

    static JFrame Suite = new JFrame("Suite");

    public void Suu(int indice) {

        Suite.setContentPane(j);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500,500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
        IFMEDECIN.setVisible(indice != 1);

    }

    public CreationCompte( int indice) throws SQLException, ClassNotFoundException {
        Suu(indice);
        if (indice==0){//MEDECIN

            creerButton.addActionListener(e -> {

                String str = new String(MDPField.getPassword());
                try {
                    Application App = new Application(indice);
                    App.AjouterMedecin(NomTextField.getText(),LoginTextField.getText(),str,SPEField.getText());
                    App.Log();
                } catch (SQLException | ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                Suite.setVisible(false);
            });

        } else {//PATIENT

            creerButton.addActionListener(e -> {
                String str = new String(MDPField.getPassword());
                try {
                    Application App = new Application(indice);
                    App.AjouterPatient(NomTextField.getText(),LoginTextField.getText(),str);
                    App.Log();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                Suite.setVisible(false);
            });

        }
    }
}
