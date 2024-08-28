package cl.praxis.models.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

	private static Connection connect = null;

    private Conn() {
        
    	try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/StartUp", "postgres", "1234");
            System.out.println(connect.isValid(5000) ? "Conexión Base de Datos OK" : "Conexión Base de Datos Failed");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }

    public static Connection getConnect() throws SQLException  {
        if (connect == null || connect.isClosed()) {
            new Conn();
        }
        return connect;
    }
}

