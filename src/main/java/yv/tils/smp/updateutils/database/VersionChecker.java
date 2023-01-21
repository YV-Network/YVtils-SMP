package yv.tils.smp.updateutils.database;

import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class VersionChecker {
    public String NewestVersion() {
        ConnectionManager.openConnection();

        ResultSet resultSet;

        try {
            resultSet = ConnectionManager.getInformation("SELECT * FROM `yvtils_smp` WHERE `state` = 'newest'");
            resultSet.next();

        return resultSet.getString(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String VersionChecker_FullRelease(String ServerPluginVersion) {
        ConnectionManager.openConnection();

        if (!SMPPlugin.getInstance().database_connection) {
            return "ERROR";
        }

        ResultSet resultSet;

        try {
            resultSet = ConnectionManager.getInformation("SELECT * FROM `yvtils_smp` WHERE `state` = 'newest'");

            resultSet.next();
            new ConsoleLog("Full Release: " + resultSet.getString(2));

            if (!Objects.equals(ServerPluginVersion, resultSet.getString(2))) {
                ConnectionManager.closeConnection();
                return "UA";
            }

            ConnectionManager.closeConnection();
            return "UTD";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public String VersionChecker_BetaRelease(String ServerPluginVersion) throws SQLException {
        ConnectionManager.openConnection();

        if (!SMPPlugin.getInstance().database_connection) {
            return "ERROR";
        }

        ResultSet resultSet;

        resultSet = ConnectionManager.getInformation("SELECT * FROM `yvtils_smp` WHERE `state` = 'newest'");

        resultSet.next();
        new ConsoleLog("Full Release: " + resultSet.getString(2));

        if (!Objects.equals(ServerPluginVersion, resultSet.getString(2))) {
            ConnectionManager.closeConnection();
            return "UA";
        }

        ConnectionManager.closeConnection();
        return "UTD";
    }
     */
}
