package jp.ataru.quicknicks;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

// i love you linckode

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

    public static FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static String[] getStringList(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return (String[]) configuration.getStringList(path).toArray(new String[0]);
    }

    public static List<?> getList(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getList(path);
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

    public static boolean getBool(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getBoolean(path);
    }

    public static double getDouble(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getDouble(path);
    }

    public static boolean pathExists(File file, String path) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.contains(path);
    }
}
