import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main (String[] args) throws ClassNotFoundException, SQLException {

        //1. step: Register Driver - (optional)
        Class.forName("org.postgresql.Driver");

        //2. step: Create Connection to get connected to BD

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        //3. step: Create Statement -- to execute SQL queries
        Statement statement = con.createStatement();

        // to test if we have created connection to DB
        System.out.println("Connected successfully");


    }
}
