package pl.Garlik.Mobki;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class MobkiTools {
	private final static Random R = new Random();

	protected static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	public static int getRandom(int min, int max) {
		return R.nextInt((max - min) + 1) + min;
	}

	public static double getRandomDouble() {
		return R.nextDouble();
	}


	public static String getName(String name) {
		name = name.replaceAll("[0-9]", "");
		name = name.replaceAll("\\[", "");
		name = name.replaceAll("\\]", "");
		return name;
	}

	public static Location getRandomLocationInRegion(World world, String region) {
		ProtectedRegion reg = getWorldGuard().getRegionManager(world).getRegion(region);

		int x = getRandom(reg.getMinimumPoint().toBlockVector().getBlockX(),
				reg.getMaximumPoint().toBlockVector().getBlockX());
		int z = getRandom(reg.getMinimumPoint().toBlockVector().getBlockZ(),
				reg.getMaximumPoint().toBlockVector().getBlockZ());
		int y = getRandom(reg.getMinimumPoint().toBlockVector().getBlockY(),
				reg.getMaximumPoint().toBlockVector().getBlockY());
		Location loc = new Location(world, x, y, z);
		while (!(loc.getBlock().getType().isSolid())) {
			loc.setY(loc.getY() - 1);
		}
		do {
			loc.setY(loc.getY() + 1);
		} while (loc.getBlock().getType().isSolid());

		return loc.add(0.5, 0.5, 0.5);
	}

}