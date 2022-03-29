package Model;

import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoConnexionInterface {
    void ajouterElement(Object O) throws SQLException;
    void UpdateElement(String table, String champ,Object Depart , Object Final,String genre,int id) throws SQLException;
    void SuppElement(@NotNull Object O, Object Final, Object FinalB) throws SQLException;
    ResultSet Search (String table, String Elem, String whouere) throws SQLException;
}
