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
        if (indice==0){
            IFMEDECIN.setVisible(false);
        }else {IFMEDECIN.setVisible(true);}

    }


    public CreationCompte( int indice) throws SQLException, ClassNotFoundException {

        Suu(indice);
        if (indice==1){//MEDECIN

            creerButton.addActionListener(e -> {

                System.out.println(LoginTextField.getText());
                System.out.println(NomTextField.getText());
                System.out.println(MDPField.getPassword());
                System.out.println(SPEField.getText());


            });

        } else {//PATIENT

            creerButton.addActionListener(e -> {

                System.out.println(LoginTextField.getText());
                System.out.println(NomTextField.getText());
                System.out.println(MDPField.getPassword());


            });

        }


    }


}
