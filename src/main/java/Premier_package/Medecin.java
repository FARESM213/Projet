package Premier_package;

public class Medecin {

    private int id_medecin;
    private String nom_medecin;
    private String login_medecin;
    private String mdp_medecin;
    private String med_job;

    Medecin(){}

    Medecin(int id, String nom,String login , String mdp,String job)
    {
        id_medecin=id;
        login_medecin=login;
        nom_medecin=nom;
        mdp_medecin=mdp;
        med_job=job;
    }
    void Set_id (int id) { id_medecin=id;}
    void Set_nom (String nom) {nom_medecin=nom;}
    void Set_log (String login) {login_medecin=login;}
    void Set_mdp (String mdp) {mdp_medecin=mdp;}
    void Set_job (String job) {med_job=job;}

    int Get_id () { return id_medecin;}
    String Get_nom () { return nom_medecin;}
    String Get_log () { return login_medecin;}
    String Get_mdp () { return mdp_medecin;}
    String Get_job () { return med_job;}

    @Override
    public String toString() {
        return nom_medecin;
    }
}
