package yv.tils.smp.updateutils.database;

import yv.tils.smp.logger.ConsoleLog;

import java.sql.*;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class ConnectionManager {
    private static Connection connection;
    private static Statement statement;

    static String link = "jdbc:mariadb://109.71.253.24:3306/yvtils";
    static String user = "yvtilschecker";
    static String pw = "Z1pmp974@";

    public static void openConnection() {
        connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(link, user, pw);
            statement = connection.createStatement();
            new ConsoleLog("DB Connection ✔");
        }catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static ResultSet getInformation(String input) throws SQLException {
        return statement.executeQuery(input);
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            new ConsoleLog("DB Connection ❌");
        }
    }
}
