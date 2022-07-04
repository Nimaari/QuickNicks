package jp.marisa.quicknicks;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public Config() {
    }

    public static void set(File file, String path, Object value) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        try {
            configuration.set(path, value);
            configuration.save(file);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public static Object get(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.get(path);
    }

    public static int getInt(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getInt(path);
    }

    public static String getString(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getString(path);
    }
}
