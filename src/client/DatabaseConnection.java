package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public Connection connect_to_db(String dbname, String user, String password){
        Connection conn = null;

        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);
            Statement statement = conn.createStatement();

            if(conn != null){
                System.out.println("Connection Established");
                System.out.println();
            }
            else{
                System.out.println("Connection Failed");
                System.out.println();
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

}
