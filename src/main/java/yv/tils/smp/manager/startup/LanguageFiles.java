package yv.tils.smp.manager.startup;

import yv.tils.smp.utils.configs.language.CreateFile_de;
import yv.tils.smp.utils.configs.language.CreateFile_en;
import yv.tils.smp.utils.configs.language.LanguageFile;

/**
 * @version CH2-1.0.0
 * @since Ch2-1.0.0
 */
public class LanguageFiles {
    public void onEnable() {
        CreateFile_de createFile_de = new CreateFile_de();
        createFile_de.StringInput();
        CreateFile_en createFile_en = new CreateFile_en();
        createFile_en.StringInput();
        LanguageFile.LanguageFileGet();
    }
}
