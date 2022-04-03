package Model;

import java.time.LocalDate;

public class Rdv {

    private int id_rdv;
    private int id_med;
    private int id_pat;
    private LocalDate rdv_date;
    private String rdv_lieu;
    private String rdv_motif;
    private int rdv_duree;
    private int rdv_horaire;
    private boolean etat;

    private String  Type;


    public Rdv(int id, int med, int pat, LocalDate date, String motif, int duree, int horaire, String lieu, boolean e,String t)
    {
          id_rdv=id;
          id_med=med;
          id_pat=pat;
          rdv_date=date;
          rdv_lieu=lieu;
          rdv_motif=motif;
          rdv_duree=duree;
          rdv_horaire=horaire;
          etat=e;
          Type=t;
    }

    public Rdv() {

    }

    public int Get_id() { return id_rdv;}
    public int Get_med() { return id_med;}
    public int Get_pat() { return id_pat;}
    public String Get_lieu() { return rdv_lieu;}
    String Get_motif () { return rdv_motif;}
    public LocalDate Get_date() { return rdv_date;}
    public int Get_duree() { return rdv_duree;}
    public int Get_horaire() { return rdv_horaire;}
    public Boolean Get_etat() {return etat;}

    public String getType() {
        return Type;
    }

    @Override
    public String toString() {
        String e;
        if (etat)
            e="Libre";
        else
            e="Reservé";

        return "Numero : " + id_rdv +
                "\n\n Date : " + rdv_date +
                "\n\n Lieu : " + rdv_lieu +
                "\n\n Motif : " + rdv_motif +
                "\n\n Duree : " + rdv_duree +
                "h\n\n Horaire : " + rdv_horaire+"h"+
                "\n\n Etat : " + e;

    }
}
