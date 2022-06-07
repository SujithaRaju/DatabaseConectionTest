import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTesingDemo {
    // Connection object
    static Connection con = null;
    // Statement object
    private static Statement stmt;
    // Constant for Database URL
    /*Adding Explicit timezonr =root=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    MySQL Workbench DB name"jdbc:mysql://127.0.0.1:3306/?user=root" rightclick and copy the value*/
    public static String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // Constant for Database Username
    public static String DB_USER = "root";
    // Constant for Database Password
    public static String DB_PASSWORD = "db-pwd";

    @Before
    public void setUp() {
        try{

            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Statement object to send the SQL statement to the Database
            stmt = con.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try{
            String query = "SELECT * FROM Customers_db.customers_test_db";
            // Get the contents of userinfo table from DB
            ResultSet res = stmt.executeQuery(query);
            // Print the result untill all the records are printed
            // res.next() returns true if there is any next record else returns false
            while (res.next())
            {
                System.out.print(res.getString(1));
                System.out.print("\t" + res.getString(2));
                System.out.print("\t" + res.getString(3));
                System.out.println("\t" + res.getString(4));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        // Close DB connection
        if (con != null) {
            con.close();
        }
    }
}
