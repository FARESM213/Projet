package Premier_package;
public class Rdv {

    private int id_rdv;
    private int id_med;
    private int id_pat;
    private String rdv_date;
    private String rdv_lieu;
    private String rdv_motif;
    private int rdv_duree;
    private int rdv_horaire;
    private boolean etat;

    Rdv( int id , int med,int pat, String date, String motif, int duree, int horaire,String lieu,boolean e)
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
    }

    public Rdv() {

    }

    void Set_id (int id) { id_rdv=id;}
    void Set_med (int med) {id_med=med;}
    void Set_pat (int pat) {id_pat=pat;}
    void Set_lieu (String lieu) {rdv_lieu=lieu;}
    void Set_motif (String motif) {rdv_motif=motif;}
    void Set_duree(int duree) {rdv_duree=duree;}
    void Set_horaire (int horarire) {rdv_horaire=horarire;}
    void Set_date (String date) {rdv_date=date;}
    void Set_etat (boolean e) {etat=e;}



    int Get_id () { return id_rdv;}
    int Get_med () { return id_med;}
    int Get_pat () { return id_pat;}
    String Get_lieu () { return rdv_lieu;}
    String Get_motif () { return rdv_motif;}
    String Get_date () { return rdv_date;}
    int Get_duree() { return rdv_duree;}
    int Get_horaire () { return rdv_horaire;}
    Boolean Get_etat () {return etat;}

    @Override
    public String toString() {
        String e="";
        if (etat==true)
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
