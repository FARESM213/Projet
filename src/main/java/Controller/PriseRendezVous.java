package Controller;

import Model.Medecin;
import Model.Rdv;
import View.Fenetre_PriseRdv;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class PriseRendezVous
{
    Fenetre_PriseRdv Fenetre;
    public PriseRendezVous(int a, Application App, Rdv O, Suite s,Object P) throws ParseException {

        Fenetre = new Fenetre_PriseRdv(a,App,O,P);
        Fenetre.getPrendreLeRendezVousButton().addActionListener(e ->
        {
            if (a==1)
            {
                try {
                    String A = (String)Fenetre.getComboBox1().getSelectedItem();
                    assert A != null;
                    String B= String.valueOf(A.charAt(0));
                    App.Update_Rdv(O,Integer.parseInt(B),Fenetre.getTextField3().getText(),P);
                    Fenetre.SetView(false);
                    App.init();
                    s.Fenetre.SetView(true,App);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                try {
                    String A = (String)Fenetre.getComboBox1().getSelectedItem();
                    assert A != null;

                    String B= String.valueOf(A.charAt(0));
                    int duree = Integer.parseInt(B);

                    Date dateToStr1= (Date) Fenetre.getSpinner1();
                    Date dateToStr2= (Date) Fenetre.getSpinner2();

                    LocalDate date1=dateToStr1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    SimpleDateFormat sdf = new SimpleDateFormat("HH");
                    int horaire = Integer.parseInt(sdf.format(dateToStr2));

                    int min=3;
                    for (Rdv N :App.Rendezvous)
                    {
                        if (Objects.equals(N.Get_date(), date1) &&(N.Get_horaire()>horaire))
                        {
                            System.out.println("OUIIII");
                            if ( (N.Get_med()==((Medecin)P).Get_id()) &&( min > (N.Get_horaire()-horaire)))
                            {
                                min =(N.Get_horaire()-horaire);
                            }
                        }
                    }

                    if(duree<=min)
                    {
                        App.AjouterRdv(App.Rendezvous.get(App.Rendezvous.size()-1).Get_id()+1,((Medecin)P).Get_id(),0,date1,((Medecin)P).getHopital(),null,Fenetre.getTextField3().getText(),duree,horaire,false);
                        Fenetre.SetView(false);
                        App.init();
                        s.Fenetre.SetView(true,App);
                    }
                    else
                    {
                        Fenetre.Erreur(horaire+min);
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        });
        Fenetre.getAnnulerButton().addActionListener(e -> {
            Fenetre.SetView(false);
            s.Fenetre.SetView(true,App);
        });
    }
}
