package pl.Garlik.Mobki;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

class MobEvents implements Listener {
	@EventHandler
	public void EntityDeathEvent(EntityDeathEvent event) {
		String name = event.getEntity().getCustomName();
		LivingEntity eventEntity = event.getEntity();
		if (name != null && !(eventEntity instanceof Player)) {
			event.getDrops().clear();
			event.setDroppedExp(0);
			MobManager.spawnMob(MobkiTools.getName(name), eventEntity.getWorld());

		}
	}
}
