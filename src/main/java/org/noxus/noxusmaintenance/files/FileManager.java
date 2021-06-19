package org.noxus.noxusmaintenance.files;

import org.noxus.noxusmaintenance.NoxusMaintenance;
import org.noxus.noxusmaintenance.files.yaml.YamlCreator;

public class FileManager {

    private final NoxusMaintenance plugin;

    private YamlCreator lang;
    private YamlCreator config;

    public FileManager(NoxusMaintenance plugin){
        this.plugin = plugin;
    }

    public void setupFiles(){
        lang = this.fileCreator("lang");
        config = this.fileCreator("config");

    }

    public YamlCreator fileCreator(String string){
        return new YamlCreator(plugin, string);
    }

    public YamlCreator getLang(){
        return lang;
    }

    public YamlCreator getConfig(){
        return config;
    }
}
