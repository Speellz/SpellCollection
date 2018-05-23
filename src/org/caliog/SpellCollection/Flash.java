package org.caliog.SpellCollection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class Flash extends Spell {

	public Flash(RolecraftPlayer player) {
		super(player, "Flash");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute() {
		if (!super.execute())
			return false;
		int r = getRadius();
		Location loc = getPlayer().getPlayer().getLocation();
		boolean f = true;
		for (Entity e : loc.getWorld().getEntities()) {
			if (((e instanceof LivingEntity)) && (e.getEntityId() != getPlayer().getPlayer().getEntityId())
					&& (e.getLocation().distanceSquared(loc) <= r * r)) {
				EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(getPlayer().getPlayer(), e, DamageCause.CUSTOM,
						getDamage());
				Bukkit.getPluginManager().callEvent(event);
				loc.getWorld().strikeLightningEffect(e.getLocation());
				f = false;
			}
		}
		if (f) {
			for (int i = 3; i <= getPower(); i++) {
				Location target = loc.clone();
				target.add(i, 0, -i);
				if (Math.random() < 0.4)
					target.getWorld().strikeLightningEffect(target);
				target.add(-i, 0, i);
				if (Math.random() < 0.4)
					target.getWorld().strikeLightningEffect(target);
				target.add(i, 0, i);
				if (Math.random() < 0.4)
					target.getWorld().strikeLightningEffect(target);
				target.add(-i, 0, -i);
				if (Math.random() < 0.4)
					target.getWorld().strikeLightningEffect(target);
			}
		}

		return true;
	}

	public int getRadius() {
		final float x = ((float) getPower()) / getMaxPower();
		if (x < 0.1)
			return 4;
		if (x < 0.2)
			return 6;
		if (x < 0.4)
			return 7;
		if (x < 0.6)
			return 8;
		if (x < 0.8)
			return 10;
		return 12;

	}

}
