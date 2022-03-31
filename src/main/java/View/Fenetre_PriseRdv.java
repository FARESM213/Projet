package View;

import Controller.Application;
import Model.Medecin;
import Model.Rdv;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

public class Fenetre_PriseRdv {

    private JButton prendreLeRendezVousButton;
    private JTextField textField3;

    private JButton annulerButton;
    private JPanel Pnl;
    private JComboBox<String> comboBox1;
    private JLabel nmrLabel;
    private JLabel mdcLabel;
    private JLabel dtLabel;
    private JLabel hrrLabel;
    private JLabel hptLabel;

    static JFrame Suite = new JFrame("Suite");

    public Fenetre_PriseRdv(int indice, Application App, Rdv O) throws ParseException
    {
        Suu(indice);
        createUIComponents(App,O);
    }

    public void Suu(int indice)
    {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Suite.setContentPane(Pnl);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(500, 500));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    private void createUIComponents(Application App,Rdv O) throws ParseException
    {
        String nom="";
        for (Medecin N:App.Med) {
            if (N.Get_id()==O.Get_id())
            {
                nom=N.Get_nom();
            }
        }
          nmrLabel.setText(String.valueOf(O.Get_id()));
          mdcLabel.setText(nom);
          dtLabel.setText(String.valueOf(O.Get_date()));
          hrrLabel.setText(O.Get_horaire()+" h ");
          hptLabel.setText(O.Get_lieu());

        int min=3;
        for (Rdv N :App.Rendezvous)
        {
            if (Objects.equals(N.Get_date(), O.Get_date()) &&(N.Get_horaire()>O.Get_horaire()) && (N.Get_id()!=O.Get_id()))
            {
                if ((!N.Get_etat())&& (N.Get_horaire()-O.Get_horaire() < min)&& (N.Get_med()==O.Get_med()))
                {
                    min =N.Get_horaire()-O.Get_horaire();
                }
            }
        }
        for( int i=1;i<min+1;i++)
        {
           comboBox1.addItem(i+" h");
        }
    }

    public JButton getPrendreLeRendezVousButton() {
        return prendreLeRendezVousButton;
    }


    public JComboBox<String> getComboBox1() {
        return comboBox1;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JButton getAnnulerButton() {
        return annulerButton;
    }


    public void SetView(boolean b) {
        Suite.setVisible(b);
    }
}
