package Model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DaoConnexionInterface {

    void ajouterElement(Object O) throws SQLException;
    void UpdateElement(String table, String champ,Object Depart , Object Final,String genre,int id) throws SQLException;
    void SuppElement(@NotNull Object O, Object Final, Object FinalB) throws SQLException;
    ResultSet Search (String table, String Elem, String whouere) throws SQLException;

    void coco(String s, String s1, String s2) throws SQLException, ClassNotFoundException;
    void UpdateImage (String table, String champ, Object Depart, Object Final, String genre, int id,Object O) throws SQLException, IOException;
    ArrayList<Object> Selection_distinct(String Table , String Champ) throws SQLException;
    void SuppressionRdvInter(Rdv O) throws SQLException;

    void ExecuteRequest(String delete_from_rendez_vous_)throws SQLException ;
}
