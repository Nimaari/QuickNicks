package tk.nimari;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public final class QuickNicks extends JavaPlugin {

    @Override
    public void onEnable() {
        long currentTime = System.currentTimeMillis();
        getDataFolder().mkdir();
        File userDataFolder = new File(getDataFolder() + File.separator + "userData" + File.separator);

        new Metrics(this, 16782);

        if (!userDataFolder.exists())
            userDataFolder.mkdir();

        File configFile = new File(getDataFolder() + File.separator + "config.yml");
        if (Config.get(configFile, "nickSize") == null)
            Config.set(configFile, "nickSize", 32);

        if (Config.get(configFile, "prefix") == null)
            Config.set(configFile, "prefix", "§8[§bQuickNicks§8]§7");

        getCommand("nick").setExecutor(new NickCommand());
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        getLogger().log(Level.INFO, "Successful startup took " + (System.currentTimeMillis() - currentTime) + "ms.");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Bye-bye! See you another time.");
    }

    public class Listeners implements Listener {

        @EventHandler
        public void join(PlayerJoinEvent e) {
            File playerFile = new File(getDataFolder() + File.separator + "userData" + File.separator + e.getPlayer().getName() + ".yml");
            if (Config.getString(playerFile, "nick") == null)
                Config.set(playerFile, "nick", e.getPlayer().getName());

            e.getPlayer().setDisplayName(Config.getString(playerFile, "nick") + "§r");
            e.getPlayer().setPlayerListName(Config.getString(playerFile, "nick") + "§r");
        }

    }

    public class NickCommand implements CommandExecutor {

        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            Player player = (Player) sender;
            File configFile = new File(getDataFolder() + File.separator + "config.yml");
            File playerFile = new File(getDataFolder() + File.separator + "userData" + File.separator + player.getName() + ".yml");

            if (player.hasPermission("quicknicks.nick")) {
                if (args.length == 1) {
                    if (!args[0].equalsIgnoreCase("off")) {
                        String argument = args[0].replace("&", "§");
                        String sizeCheck = argument.replace("§", "");
                        if (sizeCheck.length() <= Config.getInt(configFile, "nickSize")) {
                            Config.set(playerFile, "nick", argument);
                            player.setDisplayName(Config.getString(playerFile, "nick") + "§r");
                            player.setPlayerListName(Config.getString(playerFile, "nick") + "§r");
                            player.sendMessage(Config.get(configFile, "prefix") + " Your nickname is now §f" + Config.getString(playerFile, "nick") + "§7.");
                        } else {
                            player.sendMessage(Config.get(configFile, "prefix") + " Your nickname is too long!");
                        }
                    } else {
                        player.sendMessage(Config.get(configFile, "prefix") + " Your nickname was removed.");
                        Config.set(playerFile, "nick", player.getName());
                        player.setDisplayName(Config.getString(playerFile, "nick") + "§r");
                        player.setPlayerListName(Config.getString(playerFile, "nick") + "§r");
                    }
                } else {
                    player.sendMessage(Config.get(configFile, "prefix") + " You need to specify a nickname!");
                }
            } else {
                player.sendMessage(Config.get(configFile, "prefix") + " No permission.");
            }
            return true;
        }
    }
}