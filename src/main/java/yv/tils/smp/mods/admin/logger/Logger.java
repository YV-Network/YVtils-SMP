package yv.tils.smp.mods.admin.logger;

import yv.tils.smp.YVtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @version 4.6.7
 * @since 4.6.7
 */
public class Logger {

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

    public void writer(String log, String event) throws IOException {

        File dataFolder1 = new File(YVtils.getInstance().getDataFolder() + "/Logs/");
        if (!dataFolder1.exists()) {
            dataFolder1.mkdir();
        }

        File dataFolder2 = new File(YVtils.getInstance().getDataFolder() + "/Logs/" + event);
        if (!dataFolder2.exists()) {
            dataFolder2.mkdir();
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File saveTo = new File(YVtils.getInstance().getDataFolder() + "/Logs/" + event, sdf2.format(timestamp) + ".EventLog.log");
        if (!saveTo.exists()) {
            try {
                saveTo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fw = new FileWriter(saveTo, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println("[" + sdf1.format(timestamp) + "] - " + log);
        pw.flush();
        pw.close();
    }
}
