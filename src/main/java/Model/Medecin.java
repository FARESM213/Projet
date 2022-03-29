package Model;

public class Medecin {

    private int id_medecin;
    private String nom_medecin;
    private String login_medecin;
    private String mdp_medecin;
    private String med_job;

    Medecin(){}

    public Medecin(int id, String nom, String login, String mdp, String job)
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

    public int Get_id() { return id_medecin;}
    public String Get_nom () { return nom_medecin;}
    public String Get_log() { return login_medecin;}
    public String Get_mdp() { return mdp_medecin;}
    public String Get_job () { return med_job;}

    @Override
    public String toString() {
        return nom_medecin;
    }
}
