package View;

import Controller.Application;
import Model.Medecin;
import Model.Rdv;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    private JLabel motifLabel1;

    private JSpinner spinner1;
    private JSpinner spinner2;
    private JLabel dureeLabel;
    private JLabel horaireLabel;
    private JLabel hopitalLabel;
    private JLabel dateLabel;
    private JLabel medecinLabel;
    private JLabel numeroLabel;
    private JLabel Labelll;
    private JPanel Pannnnel;

    static JFrame Suite = new JFrame("Suite");

    public Fenetre_PriseRdv(int indice, Application App, Rdv O,Object M) throws ParseException
    {
        Suu(indice);
        createUIComponents(App,O,indice,M);
    }

    public void Suu(int indice) throws ParseException {

        Labelll.setIcon(Resize("Images\\stethoscope.png",331,446));
        Visible(indice);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        numeroLabel.setIcon(Resize("Images\\number-blocks.png",20,20));
        medecinLabel.setIcon(Resize("Images\\med.png",20,20));
        dateLabel.setIcon(Resize("Images\\date1.png",20,20));
        motifLabel1.setIcon(Resize("Images\\med.png",20,20));
        dureeLabel.setIcon(Resize("Images\\hourglass.png",20,20));
        horaireLabel.setIcon(Resize("Images\\stopwatch.png",20,20));
        hopitalLabel.setIcon(Resize("Images\\hopital.png",20,20));
        annulerButton.setIcon(Resize("Images\\return.png",20,20));
        prendreLeRendezVousButton.setIcon(Resize("Images\\pilules.png",20,20));


        Suite.add(Pnl, BorderLayout.WEST);
        Suite.add(Pannnnel, BorderLayout.EAST);
        Suite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Suite.setPreferredSize(new Dimension(800, 680));
        Suite.setResizable(false);
        Suite.pack();
        Suite.setVisible(true);
    }

    private void createUIComponents(Application App,Rdv O, int indice,Object M) throws ParseException
    {
        if (indice==1)
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
            prendreLeRendezVousButton.setText("Prendre le Rendez-vous");

            int min=3;
            for (Rdv N :App.Rendezvous)
            {
                if (Objects.equals(N.Get_date(), O.Get_date()) &&(N.Get_horaire()>O.Get_horaire()) && (N.Get_id()!=O.Get_id()))
                {
                    if ((!N.Get_etat())&& (N.Get_horaire()-O.Get_horaire() < min)&& (N.Get_med()==O.Get_med()))
                    {
                        System.out.println(N.Get_horaire()+"   "+ O.Get_horaire());
                        min =N.Get_horaire()-O.Get_horaire();
                    }
                }
            }
            for( int i=1;i<min+1;i++)
            {
                comboBox1.addItem(i+" h");
            }
        }
        else
        {
            int nom=App.Rendezvous.get(App.Rendezvous.size()-1).Get_id()+1;
            nmrLabel.setText(String.valueOf(nom));
            mdcLabel.setText(((Medecin)M).Get_nom());
            dtLabel.setVisible(false);
            hrrLabel.setVisible(false);
            motifLabel1.setText("Type :");
            hptLabel.setText(((Medecin)M).getHopital());
            dureeLabel.setVisible(false);
            comboBox1.setVisible(false);

            int min=3;
            for( int i=1;i<min+1;i++)
            {
                comboBox1.addItem(i+" h");
            }
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


    public void Visible(int indice) throws ParseException
    {
        if (indice ==0)
        {
            SpinnerDateModel model = new SpinnerDateModel();
            spinner1.setModel(model);
            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "yyyy-MM-dd");
            spinner1.setEditor(editor);

            Date dateToStr1= new Date();
            LocalDate date1=dateToStr1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            model.setStart( editor.getFormat().parse(String.valueOf(date1)) );
            SpinnerDateModel model2 = new SpinnerDateModel();
            spinner2.setModel(model2);
            JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "HH");
            spinner2.setEditor(editor2);
            model2.setStart( editor2.getFormat().parse("0") );
        }
        else
        {
            spinner1.setVisible(false);
            spinner2.setVisible(false);
        }
    }

    public Object getSpinner1() {
        return spinner1.getValue();
    }

    public Object getSpinner2() {
        return spinner2.getValue();
    }

    public void Erreur(int min) {
        JOptionPane.showMessageDialog(null,"Il semblerait que vous ayez deja un rdv programmé a "+min+"","Erreur",JOptionPane.INFORMATION_MESSAGE);

    }


    public ImageIcon Resize(String path, int W , int H)
    {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(W, H,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

}

