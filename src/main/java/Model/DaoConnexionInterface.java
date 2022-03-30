package Model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoConnexionInterface {
    void ajouterElement(Object O) throws SQLException;
    void UpdateElement(String table, String champ,Object Depart , Object Final,String genre,int id) throws SQLException;
    void SuppElement(@NotNull Object O, Object Final, Object FinalB) throws SQLException;
    ResultSet Search (String table, String Elem, String whouere) throws SQLException;

    void tst() throws SQLException, IOException, ClassNotFoundException;

    void coco(String s, String s1, String s2) throws SQLException, ClassNotFoundException;

    void tst2()throws SQLException;

    void UpdateImage (String table, String champ, Object Depart, Object Final, String genre, int id,Object O) throws SQLException, IOException;
}
