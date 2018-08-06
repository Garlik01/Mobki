package pl.Garlik.Mobki;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Mobki extends JavaPlugin {
	private static Plugin plugin;
	private static Mobki instance;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("mobki").setExecutor(new MobkiCommand());
		registerEvents(this, new MobEvents());
		saveConfig();
		MobManager.registerMobs();
	}

	public void onDisable() {
		plugin = null;

	}

	public static void registerEvents(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	public static Plugin getPlugin() {
		return plugin;

	}

	public static Mobki getInstance() {
		return instance;
	}

}