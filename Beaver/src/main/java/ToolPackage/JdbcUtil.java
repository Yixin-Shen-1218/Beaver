package ToolPackage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p> Tool class to connect the database.
 * Tool class to connect the database, including some static method related to database connection.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 24/02/2021-15:07
 * @since 1.0
 */
public class JdbcUtil {
    /**
     * get connection with database
     */
    public static java.sql.Connection con;
    /**
     * execute sql statement
     */
    public static Statement stmt;
    /**
     * save the result set that has been chosen
     */
    public static ResultSet rs;


    /**
     * connect to database
     *
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot connect to the database
     * @author Yuan DAI
     * @date 12/01/2021-15:27
     */
    public static void connectDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://sh-cdb-4sdq0l40.sql.tencentcdb.com:60624/test?characterEncoding=UTF-8", "root", "reliableman@123");
        stmt = con.createStatement();
    }

    /**
     * connect to database
     *
     * @return Whether the connection is successful.
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot connect to the database
     * @author Yuan DAI
     * @date 12/01/2021-15:27
     */
    public static boolean IsConnectDatabase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sh-cdb-4sdq0l40.sql.tencentcdb.com:60624/test?characterEncoding=UTF-8", "root", "reliableman@123");
        } catch (SQLException e) {
            return true;
        }
        stmt = con.createStatement();
        return false;
    }


    /**
     * close the connection to database
     *
     * @throws SQLException throws exception if connect close failed
     * @author Yuan DAI
     * @date 12/01/2021-15:29
     */
    public static void closeConnect() throws SQLException {
        rs.close();
        stmt.close();
        con.close();
    }

    /**
     * The method aims to close the connection to database
     *
     * @author Donglin Jiang
     * @date 03/02/2021-13:43
     */
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * checkSQL
     * <p>
     * Function used to check whether the sql statement is correct by execute it
     *
     * @param sql sql statement in string
     * @return int the status of the checked result
     * @author Ruibin CHEN
     * @date 2021/3/14-20:41
     */
    public static int checkSQL(String sql) {
        int check = 0;
        try {
            connectDatabase();
            stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            check = 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            check = 2;
        } finally {
            JdbcUtil.closeConnection();
        }
        return check;
    }
}
