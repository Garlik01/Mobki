package pl.Garlik.Mobki;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

class MobConfig {
	private File mobData;
	private FileConfiguration mobDataConfig;
	HashMap<String, String> map = new HashMap<String, String>();

	public MobConfig(String name) {
		mobData = new File("plugins/Mobki/" + name + ".yml");
		MobManager.addMob(name);

		mobDataConfig = YamlConfiguration.loadConfiguration(mobData);
		if (mobData.length() <= 0) {
			mobDataConfig.set("Wyswietlana nazwa", name);
		}

	}

	public void createMobConfig() {
		try {
			mobData.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createDefault() {
		if (mobData.length() <= 0) {
			mobDataConfig.set("Typ", "" + EntityType.ZOMBIE);
			mobDataConfig.set("Poziom", 1);
			mobDataConfig.set("Cechy.Serca", 10);
			mobDataConfig.set("Cechy.region", "testregion");
			mobDataConfig.set("Cechy.czasSpawnu", 1);
			mobDataConfig.set("Cechy.ilosc", 3);
			mobDataConfig.set("Equipment.helmet", Material.LEATHER_HELMET.toString());
			mobDataConfig.set("Equipment.chestplate", Material.LEATHER_CHESTPLATE.toString());
			mobDataConfig.set("Equipment.leggings", Material.LEATHER_LEGGINGS.toString());
			mobDataConfig.set("Equipment.boots", Material.LEATHER_BOOTS.toString());
			mobDataConfig.set("Equipment.mainHand", Material.STICK.toString());
			mobDataConfig.set("Equipment.offHand", Material.AIR.toString());


			
			
			
			
			
		}
	}

	public FileConfiguration getMobConfig() {
		return mobDataConfig;
	}

	public void saveMobConfig() {
		try {
			getMobConfig().save(mobData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDrops(int i) {
		return  mobDataConfig.getString("Drop." + i);
	}

	public int getMobInt(String path) {
		return mobDataConfig.getInt(path);
	}

	public String getType() {
		return mobDataConfig.getString("Typ");
	}

	public String getRegion() {
		return mobDataConfig.getString("Cechy.region");

	}

	public String getName() {
		return mobDataConfig.getString("Wyswietlana nazwa");
	}

	public double getHealth() {
		return mobDataConfig.getInt("Cechy.Serca");
	}

	public double getAmount() {
		return mobDataConfig.getInt("Cechy.ilosc");
	}

	public int getSpeed() {
		return mobDataConfig.getInt("Cechy.szybkosc");
	}

	public int getExp() {
		return mobDataConfig.getInt("Cechy.exp");
	}

	public int getTime() {
		return mobDataConfig.getInt("Cechy.czasSpawnu");
	}

	public int getLevel() {
		return mobDataConfig.getInt("Poziom");
	}

	public String getHelmet() {
		return mobDataConfig.getString("Equipment.helmet");
	}

	public String getChestplate() {
		return mobDataConfig.getString("Equipment.chestplate");
	}

	public String getLeggings() {
		return mobDataConfig.getString("Equipment.leggings");
	}

	public String getBoots() {
		return mobDataConfig.getString("Equipment.boots");
	}

	public String getMainHand() {
		return mobDataConfig.getString("Equipment.mainHand");
	}

	public String getOffHand() {
		return mobDataConfig.getString("Equipment.offHand");
	}

}