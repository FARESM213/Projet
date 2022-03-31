package Model;


import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Connexion implements DaoConnexionInterface {

    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private PreparedStatement ps;
    public Connexion() {

    }

    /**
     * ArrayList public pour les tables
     */

    public void coco(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase + "?useUnicode=true\n" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&\n" +
                "serverTimezone=UTC&\n"
                + "autoReconnect=true&useSSL=false";
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
    }


    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase, List<Patient> Pat, List<Medecin> Med, List<Rdv> Rendezvous) throws SQLException, ClassNotFoundException {

        coco(nameDatabase, loginDatabase, passwordDatabase);

        stmt = conn.createStatement();
        rset = stmt.executeQuery("SELECT * FROM Patient");
        while (rset.next()) {
            Pat.add(new Patient(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5),rset.getBytes(6)));
        }
        rset = stmt.executeQuery("SELECT * FROM Medecin");
        while (rset.next()) {
            Med.add(new Medecin(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6),rset.getBytes(7),rset.getString(8)));
        }
        rset = stmt.executeQuery("SELECT * FROM Rendez_vous");
        while (rset.next()) {
            Rendezvous.add(new Rdv(rset.getInt(1), rset.getInt(2), rset.getInt(3), rset.getDate(4).toLocalDate(), rset.getString(5), rset.getInt(6), rset.getInt(7), rset.getString(8), rset.getBoolean(9), rset.getString(10)));
        }
    }


    public void ajouterElement(Object O) throws SQLException {
        stmt = conn.createStatement();
        if (O.getClass() == Patient.class) {
            stmt.executeUpdate("INSERT INTO Patient VALUES (" + ((Patient) O).Get_id() + ",'" + ((Patient) O).Get_nom() + "','" + ((Patient) O).Get_log() + "','" + ((Patient) O).Get_mdp() + "','" + ((Patient) O).Get_Mail() + "','" + ((Patient) O).getImage() + "' )");
        } else if (O.getClass() == Medecin.class) {
            stmt.executeUpdate("INSERT INTO Medecin VALUES (" + ((Medecin) O).Get_id() + ",'" + ((Medecin) O).Get_nom() + "','" + ((Medecin) O).Get_log() + "','" + ((Medecin) O).Get_mdp() + "','" + ((Medecin) O).Get_job() + "','" + ((Medecin) O).Get_mail() +  "','" + ((Medecin) O).getImage() + "','" + ((Medecin) O).getHopital() + "')");
        } else {
            stmt.executeUpdate("INSERT INTO Rendez_vous VALUES (" + ((Rdv) O).Get_id() + "," + ((Rdv) O).Get_med() + "," + ((Rdv) O).Get_pat() + ",'" + ((Rdv) O).Get_date() + "','" + ((Rdv) O).Get_motif() + "'," + ((Rdv) O).Get_duree() + "," + ((Rdv) O).Get_horaire() + ",'" + ((Rdv) O).Get_lieu() + ",'" + ((Rdv) O).Get_etat() +  ",'" + ((Rdv) O).getType() + "')");
        }
    }

    public void UpdateImage (String table, String champ, Object Depart, Object Final, String genre, int id,Object O) throws SQLException, IOException {

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        File file = new File(filename);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte [] buf = new byte[1024];
        for (int number; (number = Objects.requireNonNull(fis).read(buf))!=-1;)
        {
            bos.write(buf,0,number);
        }
        String sql=null;
        if (O.getClass()==Medecin.class)
        {
            sql = "UPDATE Medecin SET Image= ? WHERE "+ genre+"=" + id;
            ((Medecin) O).setImage(bos.toByteArray());
        }
        else if (O.getClass()==Patient.class)
        {
            sql = "UPDATE Patient SET Image= ? WHERE "+ genre+"=" + id;
            ((Patient) O).setImage(bos.toByteArray());
        }
        ps = conn.prepareStatement(sql);
        ps.setBytes(1,bos.toByteArray());
        ps.executeUpdate();
        ps.close();
    }

    public void UpdateElement(String table, String champ, Object Depart, Object Final, String genre, int id) throws SQLException {
        stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE " + table + " SET " + champ + "='" + Final + "' WHERE " + champ + "='" + Depart + "' AND " + genre + "=" + id);
    }

    public void SuppElement(@NotNull Object O, Object Final, Object FinalB) throws SQLException {
        stmt = conn.createStatement();
        if (O.getClass() == Patient.class) {
            stmt.executeUpdate("DELETE FROM Patient WHERE patno=" + Final + " AND patpassword='" + FinalB + "'");
            stmt.executeUpdate("DELETE FROM rendez_vous WHERE patno=" + Final);
        } else if (O.getClass() == Medecin.class) {
            stmt.executeUpdate("DELETE FROM Medecin WHERE medno=" + Final + " AND medpassword='" + FinalB + "'");
            stmt.executeUpdate("DELETE FROM rendez_vous WHERE medno=" + Final);
        } else
        {
            stmt.executeUpdate("DELETE FROM Rendez_vous WHERE medno=" + Final + " AND patno=" + FinalB);
        }
    }

    public ResultSet Search(String table, String Elem, String whouere) throws SQLException {
        stmt = conn.createStatement();
        return stmt.executeQuery("SELECT " + Elem + " FROM " + table + " " + whouere);
    }

    @Override
    public void tst() throws SQLException, IOException {

        File file = new File("C:\\Users\\33783\\Desktop\\preview-_1_.png");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte [] buf = new byte[1024];
        for (int number; (number = Objects.requireNonNull(fis).read(buf))!=-1;)
        {
            bos.write(buf,0,number);
        }

        String sql = "UPDATE Patient SET Image= ? WHERE 1";
        ps = conn.prepareStatement(sql);
        ps.setBytes(1,bos.toByteArray());
        ps.executeUpdate();
        ps.close();

        sql = "UPDATE Medecin SET Image= ? WHERE 1";
        ps = conn.prepareStatement(sql);
        ps.setBytes(1,bos.toByteArray());
        ps.executeUpdate();
        ps.close();
    }

        public ArrayList<Object> Selection_distinct(String Table, String Champ) throws SQLException
        {
            ArrayList <Object> test = new ArrayList<>();
            rset = stmt.executeQuery("SELECT DISTINCT "+Champ+" FROM "+Table+" ");
            while (rset.next())
            {
                test.add(rset.getObject(1));
            }
            return test;
        }

}


