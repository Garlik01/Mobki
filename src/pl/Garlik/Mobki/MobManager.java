package pl.Garlik.Mobki;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class MobManager {
	private static HashMap<String, String> mobMap;

	// Nazwa w grze -> nazwa w configu
	public static void spawnMob(String configName, World world) {
		MobConfig mobConfig = new MobConfig(mobMap.get(configName));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Mobki.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Location loc = MobkiTools.getRandomLocationInRegion(world, mobConfig.getRegion());
				EntityType type = EntityType.valueOf(mobConfig.getType());
				LivingEntity entity = (LivingEntity) world.spawnEntity(loc, type);
				EntityEquipment eq = entity.getEquipment();
				eq.setHelmet(new ItemStack(Material.getMaterial(mobConfig.getHelmet())));
				eq.setChestplate(new ItemStack(Material.getMaterial(mobConfig.getChestplate())));
				eq.setLeggings(new ItemStack(Material.getMaterial(mobConfig.getLeggings())));
				eq.setBoots(new ItemStack(Material.getMaterial(mobConfig.getBoots())));
				eq.setItemInMainHand(new ItemStack(Material.getMaterial(mobConfig.getMainHand())));
				eq.setItemInOffHand(new ItemStack(Material.getMaterial(mobConfig.getOffHand())));
				entity.setMaxHealth(mobConfig.getHealth());
				entity.setCustomNameVisible(true);
				String mobName = mobConfig.getName();
				entity.setCustomName(mobName + "[" + mobConfig.getLevel() + "]");
				if (!(mobMap.containsKey(mobName))) {
					mobMap.put(mobName, configName);
				}
			}
		}, 20 * mobConfig.getTime());

	}

	static void addMob(String name) {
		mobMap.put(name, name);
	}

	static HashMap<String, String> getMap() {
		return mobMap;
	}
	
	public static void resetMobs(World world) {
		for (String name : mobMap.keySet()) {
			killMobs(name, world);
			MobConfig config = new MobConfig(mobMap.get(name));
			for (int i = 0; i < config.getAmount(); i++) {
				spawnMob(name, world);
			}
		}
	}

	public static void resetMobs(String name, World world) {
		killMobs(name, world);
		MobConfig config = new MobConfig(mobMap.get(name));
		for (int i = 0; i < config.getAmount(); i++) {
			spawnMob(name, world);
		}
	}

	public static void killMobs(String name, World world) {
		for (Entity entity : world.getEntities()) {
			if (entity.getCustomName() != null) {
				if (!(entity instanceof Player || entity instanceof Item)) {
					MobConfig config = new MobConfig(name);
					String nameFromConfig = config.getName();
					if (MobkiTools.getName(entity.getCustomName()).equalsIgnoreCase(nameFromConfig)) {
						entity.remove();
					}
				}

			}
		}

	}

	static void registerMobs() {
		mobMap = new HashMap<String, String>();
		File folder = new File("plugins/Mobki");
		String[] mobFileNames = folder.list();
		for (int i = 0; i < mobFileNames.length; i++) {
			MobConfig config = new MobConfig(mobFileNames[i].replaceAll(".yml", ""));
			mobMap.put(config.getName(), mobFileNames[i].replaceAll(".yml", ""));
		}

	}

	public static void killAll(World world) {
		for (Entity entity : world.getEntities()) {
			if (entity.getCustomName() != null && !(entity instanceof Player)) {
				entity.remove();

			}
		}

	}

}
