package yv.tils.smp.mods.admin.logger.logger;

import yv.tils.smp.mods.admin.logger.Logger;

import java.io.IOException;

/**
 * @version 4.6.7
 * @since 4.6.7
 */
public class PluginLogger {
    public void PluginEvent(String logClass, String logText) {
        try {
            new Logger().writer(logClass + ": " + logText, "PluginEvent");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
