package org.caliog.SpellCollection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class Flash extends Spell {

	public Flash(myClass player) {
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

	@Override
	public double getDamage() {
		return (0.6F + getPower()) * getPlayer().getDamage();
	}

	@Override
	public double getDefense() {
		return 0;
	}

	@Override
	public float getPower() {
		return getPlayer().getLevel() * 0.008F + 0.2F;
	}

	@Override
	public int getFood() {
		int level = getPlayer().getLevel();
		if (level < 10)
			return 2;
		else if (level < 15)
			return 4;
		else if (level < 25)
			return 5;
		else if (level < 50)
			return 7;
		else
			return 6;
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

	public int getRadius() {
		return Math.round(getPower() * 10);
	}

}
