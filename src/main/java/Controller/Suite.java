package Controller;

import Model.Medecin;
import Model.Patient;
import View.Fenetre_Suite;
import View.PieChartExample;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Suite {

    private Fenetre_Suite Fenetre = new Fenetre_Suite();
    public Suite(Application App, int i,Object A) throws SQLException, ClassNotFoundException
    {
        Suite S= this;
        if (A.getClass()== Medecin.class)
        {
            Fenetre.getReserverButton().setText("Creer");
        }
        Fenetre.Suu(App,i);
        Fenetre.getAppliquerButton().addActionListener(e -> {

            try {
                App.init();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

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
                whouere+="LEFT JOIN Medecin ON rendez_vous.loc=Medecin.hopital WHERE loc='"+spe+"'";
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
                if (date1.compareTo(date2)<0)
                {
                    whouere+= " AND rdv_date BETWEEN '"+date1+ "' AND '"+date2.plusDays(1)+"'  ";
                }
                else if (date1.compareTo(date2)>0)
                {
                    whouere+= " AND rdv_date BETWEEN '"+date1.plusDays(1)+ "' AND '"+date2+"'  ";
                }
                else if (date1.equals(date2))
                {
                    whouere+= " AND rdv_date='"+date1+ "' ";
                }
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
            REnit();
        });
        Fenetre.getConsulterProfilButton().addActionListener(e -> {
            try {
                App.init();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            Fenetre.setSuite(false);
            Profil F = new Profil(App,A,S);
            F.getFenetre().SetView(true);
        });
        Fenetre.getStatistiquesButton().addActionListener(e->{

            PieChartExample Chart;
            Chart = new PieChartExample();
            Chart.Plot(App);
        });

        Fenetre.getReserverButton().addActionListener(e -> {

            try {
                App.init();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            if (A.getClass()== Patient.class)
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
                        F.getFenetre().getPrendreLeRendezVousButton().setText("Prendre le Rendez-vous");
                        Fenetre.getTextArea1().setText("");
                        F.getFenetre().SetView(true);
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
                        F.getFenetre().getPrendreLeRendezVousButton().setText("Creer le creneau");
                        F.getFenetre().SetView(true);
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
    public Fenetre_Suite getFenetre() {
        return Fenetre;
    }

    void REnit ()
    {
        Fenetre.getGroup().clearSelection();
        Fenetre.getGroup2().clearSelection();
        Fenetre.getDlm().clear();
        Fenetre.getListe().setModel(Fenetre.getDlm());
        Fenetre.getTextArea1().setText("");
        Fenetre.ResetButton();
    }
}
