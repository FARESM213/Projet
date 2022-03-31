package Controller;

import Model.Medecin;
import View.Fenetre_Suite;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Suite {

    public Fenetre_Suite Fenetre = new Fenetre_Suite();

    public Suite(Application App, int i,Object A) throws SQLException, ClassNotFoundException
    {
        Fenetre.Suu(App);
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
                whouere+= "AND etat=true ";
            }
            else if (Fenetre.getReserveRadioButton().isSelected())
            {
                whouere+= "AND etat=false ";
            }
            else if (Fenetre.getPasseRadioButton().isSelected())
            {
                whouere+= "AND rdv_date < '"+date+"' ";
            }

            if (Fenetre.getToutLesRendezVousRadioButton().isSelected())
            {
                whouere+= "AND rdv_date > '"+date+"' ";
            }
            else if (Fenetre.getDuRadioButton().isSelected())
            {
                whouere+= "AND rdv_date BETWEEN '"+date1+ "' AND '"+date2+"' ";
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

        });

        Suite S= this;
        Fenetre.getConsulterProfilButton().addActionListener(e -> {

            Fenetre.setSuite(false);
            Profil F = new Profil(App,A,S);
            F.Fenetre.SetView(true);
        });
    }

}
