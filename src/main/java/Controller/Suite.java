package Controller;

import Model.Medecin;
import View.Fenetre_Suite;
import View.PieChartExample;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Suite {

    public Fenetre_Suite Fenetre = new Fenetre_Suite();

    public Suite(Application App, int i,Object A) throws SQLException, ClassNotFoundException
    {
        Suite S= this;
        if (A.getClass()== Medecin.class)
        {
            Fenetre.getReserverButton().setText("Creer");
        }
        Fenetre.Suu(App,i);
        Fenetre.getAppliquerButton().addActionListener(e -> {

            String whouere="";

            String spe=null;
            int idmedcin=-1;

            if (Fenetre.getCombo()!=null)
            {
                spe=(String)Fenetre.getCombo();
            }

            if (Fenetre.getMed_cin()!=null)
            {
                Object O = Fenetre.getMed_cin();
                idmedcin= ((Medecin) O).Get_id();
            }

            if(spe!=null )
            {
                whouere+="INNER JOIN Medecin WHERE loc='"+spe+"' AND loc=hopital ";
            }
            else
            {
                whouere+="WHERE rdvno IS NOT NULL ";
            }

            if (idmedcin!=-1 &&(spe!=null))
            {
                whouere+= "AND rendez_vous.medno="+idmedcin;
            }
            else if (idmedcin!=-1)
            {
                whouere+= "AND medno="+idmedcin;
            }

            Date dateToStr1;
            Date dateToStr2;
            LocalDate date = LocalDate.now();

            dateToStr1= (Date) Fenetre.getSpinner1();
            dateToStr2= (Date) Fenetre.getSpinner2();

            LocalDate date1=dateToStr1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date2=dateToStr2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (Fenetre.getLibreRadioButton().isSelected())
            {
                whouere+= " AND etat='1' ";
            }
            else if (Fenetre.getReserveRadioButton().isSelected())
            {

                whouere+= " AND etat='0'";
            }
            else if (Fenetre.getPasseRadioButton().isSelected())
            {
                LocalTime time = LocalTime.now(ZoneId.systemDefault());
                int h =time.getHour();
                whouere+= " AND CASE " +
                        "WHEN rdv_date ='"+date+"' THEN rdv_date <= '"+date+"' AND rdv_horaire <='"+h+"'" +
                        "WHEN rdv_date <'"+date+"' THEN rdv_date < '"+date+"'"+
                        "   END";
            }

            if (Fenetre.getToutLesRendezVousRadioButton().isSelected())
            {

                LocalTime time = LocalTime.now(ZoneId.systemDefault());
                int h =time.getHour()+1;
                whouere+= " AND CASE " +
                        "WHEN rdv_date ='"+date+"' THEN rdv_date >= '"+date+"' AND rdv_horaire >='"+h+"'" +
                        "WHEN rdv_date >'"+date+"' THEN rdv_date > '"+date+"'"+
                        "   END";
            }
            else if (Fenetre.getDuRadioButton().isSelected())
            {
                whouere+= " AND rdv_date BETWEEN '"+date1+ "' AND '"+date2+"'  ";
            }

            try
            {
                Fenetre.setDlm(whouere,App);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });

        Fenetre.getAnnulerButton().addActionListener(e -> {

            Fenetre.getGroup().clearSelection();
            Fenetre.getGroup2().clearSelection();
            Fenetre.getDlm().clear();
            Fenetre.getListe().setModel(Fenetre.getDlm());
            Fenetre.getTextArea1().setText("");
            Fenetre.ResetButton();

        });
        Fenetre.getConsulterProfilButton().addActionListener(e -> {

            Fenetre.setSuite(false);
            Profil F = new Profil(App,A,S);
            F.Fenetre.SetView(true);
        });
        Fenetre.getStatistiquesButton().addActionListener(e->{

            PieChartExample Chart;
            Chart = new PieChartExample();
            Chart.Plot(App);
        });

        Fenetre.getReserverButton().addActionListener(e -> {

            if (i==1)
            {
                if (!Fenetre.getElementListe())
                {
                    Fenetre.Fentre_Erreur();
                }
                else
                {
                    try {
                        Fenetre.setSuite(false);
                        PriseRendezVous F =new PriseRendezVous(i,App,Fenetre.getListe().getSelectedValue(),S,A);
                        F.Fenetre.getPrendreLeRendezVousButton().setText("Prendre le Rendez-vous");
                        Fenetre.getTextArea1().setText("");
                        F.Fenetre.SetView(true);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else
            {
                    try {
                        Fenetre.setSuite(false);
                        PriseRendezVous F =new PriseRendezVous(i,App,null,S,A);
                        F.Fenetre.getPrendreLeRendezVousButton().setText("Creer le creneau");
                        F.Fenetre.SetView(true);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
            }
        });
        Fenetre.getRetourButton().addActionListener(e -> {
            try {
                Fenetre.SetView(false,App);
                App.init();
                App.Set_frame(true);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

}
