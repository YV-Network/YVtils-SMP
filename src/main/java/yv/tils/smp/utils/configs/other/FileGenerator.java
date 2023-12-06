package yv.tils.smp.utils.configs.other;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;

import java.io.File;

/**
 * @since CH2-1.0.0
 * @version CH2-1.0.0
 */
public class FileGenerator {
    private void doNotEdit() {
        File file = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(file);

        //DoNotEdit.yml
        yamlConfig.addDefault("Started", false);
        yamlConfig.addDefault("MissingLanguage", false);
        yamlConfig.addDefault("MaintenanceMode", "false");
        yamlConfig.options().copyDefaults(true);

        try {
            yamlConfig.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateFiles() throws Exception {
        doNotEdit();

        generateFolders("Language");
        generateFolders("Discord");
        generateFolders("Status");
        generateFolders("CCR");

        onGenerate("DoNotEdit.yml");

        onGenerate("Discord/config.yml");
        onGenerate("Discord/save.yml");

        onGenerate("Status/config.yml");
        onGenerate("Status/save.yml");

        onGenerate("CCR/config.yml");
        onGenerate("CCR/save.yml");

        onGenerate("Language/de.yml");
        onGenerate("Language/en.yml");
    }

    private void generateFolders(String folder) {
        File dataFolder = new File(YVtils.getInstance().getDataFolder() + "/" + folder);
        if(!dataFolder.exists()) {
            dataFolder.mkdir();
        }
    }

    public void onGenerate(String name) throws Exception {
        File file = new File(YVtils.getInstance().getDataFolder(), name);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
