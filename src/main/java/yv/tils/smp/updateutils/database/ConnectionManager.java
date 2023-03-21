package yv.tils.smp.updateutils.database;

import org.bukkit.Bukkit;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.utils.configs.language.LanguageFile;

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
            SMPPlugin.getInstance().database_connection = true;
            new ConsoleLog("DB Connection ✓");
        }catch (SQLException | ClassNotFoundException exception) {
            SMPPlugin.getInstance().database_connection = false;
            Bukkit.getConsoleSender().sendMessage(LanguageFile.DirectFormatter("The Update Checker has an error! Please contact the Support, if you want to fix this.", "Beim checken nach einem Update ist ein Fehler aufgetreten! Bitte kontaktiere den Support, wenn du das Problem lösen willst!"));
            new ConsoleLog("Database Connection ✕");
            new ConsoleLog(exception.getMessage());
        }
    }

    public static ResultSet getInformation(String input) {
        try {
            return statement.executeQuery(input);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            new ConsoleLog("DB Connection ❌");
        }
    }
}
