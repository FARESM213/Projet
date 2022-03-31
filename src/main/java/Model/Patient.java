package Model;
public class Patient {

    private int id_patient;
    private String nom_patient;
    private String login_patient;
    private String mdp_patient;
    private String email_patient;

    private byte[] image;



    public Patient(int id, String nom, String login, String mdp, String email, byte[] bytes)
    {
        id_patient=id;
        login_patient=login;
        nom_patient=nom;
        mdp_patient=mdp;
        email_patient=email;
        image=bytes;
    }
    void Set_id (int id) { id_patient=id;}
    void Set_nom (String nom) {nom_patient=nom;}
    void Set_log (String login) {login_patient=login;}
    void Set_mdp (String mdp) {mdp_patient=mdp;}

    public int Get_id() { return id_patient;}
    public String Get_nom() { return nom_patient;}
    public String Get_log() { return login_patient;}
    public String Get_mdp() { return mdp_patient;}

    public String Get_Mail() {
        return email_patient;
    }
    public byte[] getImage() {
        return image;
    }

    @Override
    public String toString() {
        return nom_patient+" " +mdp_patient;
    }

    public void set(Patient B)
    {
        id_patient=B.id_patient;
        login_patient=B.login_patient;
        nom_patient=B.nom_patient;
        mdp_patient=B.mdp_patient;
        email_patient=B.email_patient;
        image=B.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}