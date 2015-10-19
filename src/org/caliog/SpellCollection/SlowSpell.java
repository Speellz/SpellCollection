package org.caliog.SpellCollection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.caliog.myRPG.Entities.VolatileEntities;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class SlowSpell extends Spell {
	public SlowSpell(myClass player) {
		super(player, "SlowSpell");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		int w = Math.round(getPower() / 60.0F * 12.0F);
		for (Entity entity : getPlayer().getPlayer().getNearbyEntities(w, w, w)) {
			if ((VolatileEntities.getMob(entity.getUniqueId()) != null) && ((entity instanceof LivingEntity))) {
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Math.round(getPower() * 20), 2));
			}
		}
		return true;
	}

	public int getMinLevel() {
		return 1;
	}

	public int getFood() {
		return Math.round(getPower() / 25.0F * 6.0F + 1.5F);
	}

	public float getPower() {
		int level = getPlayer().getLevel();
		if (level < 5) {
			return 5;
		}
		if (level < 10) {
			return 10;
		}
		if (level < 20) {
			return 20;
		}
		return 25;
	}

	public double getDamage() {
		return 0;
	}

	public double getDefense() {
		return 0;
	}
}
