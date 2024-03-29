package yv.tils.smp.logger.logger;

import yv.tils.smp.logger.Logger;

import java.io.IOException;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class PluginLogger {
    public void PluginEvent(String logclass ,String logtext) {
        try {
            new Logger().writer(logclass + ": " + logtext, "PluginEvent");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
