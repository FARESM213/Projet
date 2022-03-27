package Premier_package;


import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connexion {

    private final Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    /**
     * ArrayList public pour les tables
     */


    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase,List<Patient> Pat,List<Medecin> Med ,List<Rdv> Rendezvous ) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlDatabase =  "jdbc:mysql://localhost:3306/" + nameDatabase+"?useUnicode=true\n" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&\n" +
                "serverTimezone=UTC&\n"
                +"autoReconnect=true&useSSL=false";
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
        stmt = conn.createStatement();
        rset=stmt.executeQuery("SELECT * FROM Patient");

        while(rset.next())
        {
            Pat.add(new Patient(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4)));
        }
        rset=stmt.executeQuery("SELECT * FROM Medecin");
        while(rset.next())
        {
            Med.add(new Medecin(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),rset.getString(5)));
        }
        rset=stmt.executeQuery("SELECT * FROM Rendez_vous");
        while(rset.next())
        {
            Rendezvous.add(new Rdv(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getString(4),rset.getString(5), rset.getInt(6),rset.getInt(7),rset.getString(8),rset.getBoolean(9)));
        }
    }


    public void ajouterElement(String table, Object O) throws SQLException
    {
        stmt = conn.createStatement();
        if ( O.getClass()==Patient.class)
        {
            stmt.executeUpdate("INSERT INTO Patient VALUES ("+((Patient) O).Get_id()+",'"+((Patient) O).Get_nom()+"','"+((Patient) O).Get_log()+"','"+((Patient) O).Get_mdp()+"' )");
        }
        else if ( O.getClass()==Medecin.class)
        {
            stmt.executeUpdate("INSERT INTO Medecin VALUES ("+((Medecin) O).Get_id()+",'"+((Medecin) O).Get_nom()+"','"+((Medecin) O).Get_log()+"','"+((Medecin) O).Get_mdp()+"','"+ ((Medecin) O).Get_job()+"')");
        }
        else
        {
            stmt.executeUpdate("INSERT INTO Rendez_vous VALUES ("+((Rdv) O).Get_id()+","+((Rdv) O).Get_med()+","+((Rdv) O).Get_pat()+",'"+((Rdv) O).Get_date()+"','"+ ((Rdv) O).Get_motif()+"',"+  ((Rdv) O).Get_duree()+","+ ((Rdv) O).Get_horaire()+",'"+((Rdv) O).Get_lieu()+",'"+((Rdv) O).Get_etat()+"')");
        }
        // stmt.executeUpdate("CREATE TABLE joshua (Id INT, Message CHAR(20), Appreciation CHAR (20) )");
        //stmt.executeUpdate("INSERT INTO joshua VALUES (500,'JOSHUA','EST UNE PUTE' )");
        //stmt.executeUpdate("UPDATE joshua SET Appreciation='EST UNE GROSSE PUTE' WHERE Appreciation='EST UNE PUTE'");
    }
    public void UpdateElement(String table, String champ,Object Depart , Object Final,String genre,int id) throws SQLException
    {
        stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE "+ table +" SET " + champ +"='"+Final+"' WHERE "+champ+"='"+Depart+"' AND "+genre+"="+id);
    }

    public void SuppElement(@NotNull Object O, Object Final, Object FinalB) throws SQLException
    {
        stmt = conn.createStatement();
        if ( O.getClass()==Patient.class)
        {
            stmt.executeUpdate("DELETE FROM Patient WHERE patlogin="+Final+" AND patpassword="+FinalB);
        }
        else if ( O.getClass()==Medecin.class)
        {
            stmt.executeUpdate("DELETE FROM Medecin WHERE medlogin="+Final+" AND medpassword="+FinalB);
        }
        else
        {
            stmt.executeUpdate("DELETE FROM Rendez_vous WHERE medno="+Final+" AND patno="+FinalB);
        }
    }
}


