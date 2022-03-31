package Model;

public class Medecin {

    private int id_medecin;
    private String nom_medecin;
    private String login_medecin;
    private String mdp_medecin;
    private String med_job;
    private String email_med;
    private byte[] image;
    private String hopital;

    public Medecin() {

    }

    public void set(Medecin B)
    {
        id_medecin=B.id_medecin;
        login_medecin=B.login_medecin;
        nom_medecin=B.nom_medecin;
        mdp_medecin=B.mdp_medecin;
        med_job=B.med_job;
        email_med=B.email_med;
        image=B.image;
        hopital=B.hopital;
    }

    public Medecin(int id, String nom, String login, String mdp, String job,String mail,byte[] img,String h)
    {
        id_medecin=id;
        login_medecin=login;
        nom_medecin=nom;
        mdp_medecin=mdp;
        med_job=job;
        email_med=mail;
        image=img;
        hopital=h;
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
    public String Get_mail () { return email_med;}
    public String getHopital() {
        return hopital;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public String toString() {
        return nom_medecin;
    }
}
