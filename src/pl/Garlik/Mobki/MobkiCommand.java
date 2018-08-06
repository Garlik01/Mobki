package pl.Garlik.Mobki;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MobkiCommand implements CommandExecutor {


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.GOLD + "Uzycie: /mobki dodaj <nazwa> - tworzy config");
				player.sendMessage(ChatColor.GOLD + "Uzycie: /mobki spawn <nazwa> - usuwa i po czasie spawni moba");
				player.sendMessage(ChatColor.GOLD + "Uzycie: /mobki usun <nazwa> - usuwa moba z configu - nie dziala");
				player.sendMessage(ChatColor.GOLD + "Uzycie: /mobki aktualizacje - wysietla co ostatnio zrobi³ Garlik");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("dodaj")) {
					player.sendMessage(ChatColor.RED + "Uzycie: /mobki dodaj nazwa np. Troll");
				}
				if (args[0].equalsIgnoreCase("spawn")) {
					player.sendMessage(ChatColor.RED + "Uzycie: /mobki dodaj nazwa np. Troll");
				}
				
			}
			if (args.length == 2) {
				File myfile = new File("plugins/Mobki/" + args[1] + ".yml");
				if (args[0].equalsIgnoreCase("dodaj")) {
					if(myfile.exists()) {
						player.sendMessage(ChatColor.RED + "Taki mob istnieje!");
						return false;
						
					} else {
						MobConfig config = new MobConfig(args[1]);
						config.createMobConfig();
						config.createDefault();
						config.saveMobConfig();
						player.sendMessage("Udalo sie stworzyc config dla " + args[1]);
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("spawn")) {
					if(myfile.exists()) {
						MobManager.resetMobs(args[1], player.getWorld());
						player.sendMessage(ChatColor.RED + "Zespawniono " + args[1]);
						
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "Taki mob nie istnieje!");
						return false;
					}
				} else {
					player.sendMessage(ChatColor.RED + "Uzycie: /mobki dodaj <nazwa> ");
					player.sendMessage(ChatColor.RED + "Uzycie: /mobki spawn <nazwa> ");
				}
			}

		}
		return true;
	}


}
