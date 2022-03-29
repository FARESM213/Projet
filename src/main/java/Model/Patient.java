package Model;
public class Patient {

    private int id_patient;
    private String nom_patient;
    private String login_patient;
    private String mdp_patient;

    public Patient(int id, String nom, String login, String mdp)
    {
        id_patient=id;
        login_patient=login;
        nom_patient=nom;
        mdp_patient=mdp;
    }
    void Set_id (int id) { id_patient=id;}
    void Set_nom (String nom) {nom_patient=nom;}
    void Set_log (String login) {login_patient=login;}
    void Set_mdp (String mdp) {mdp_patient=mdp;}

    public int Get_id() { return id_patient;}
    public String Get_nom() { return nom_patient;}
    public String Get_log() { return login_patient;}
    public String Get_mdp() { return mdp_patient;}

    @Override
    public String toString() {
        return nom_patient;
    }

}
