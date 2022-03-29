package Controller;

import Model.Medecin;
import View.Fenetre_Suite;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Suite {

    Fenetre_Suite Fenetre = new Fenetre_Suite();

    public Suite(Application App, int i) throws SQLException, ClassNotFoundException
    {
        Fenetre.Suu(App);
        Fenetre.getAppliquerButton().addActionListener(e -> {
            String whouere="WHERE rdvno IS NOT NULL ";
            Date dateToStr1;
            Date dateToStr2;
            LocalDate date = LocalDate.now();

            dateToStr1= (Date) Fenetre.getSpinner1();
            dateToStr2= (Date) Fenetre.getSpinner2();

            LocalDate date1=dateToStr1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date2=dateToStr2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            int idmedcin=-1;

            if (Fenetre.getMed_cin()!=null)
            {
                Object O = Fenetre.getMed_cin();
                idmedcin= ((Medecin) O).Get_id();
            }

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
            if (idmedcin!=-1)
            {
                whouere+= "AND medno="+idmedcin;
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
    }

}
