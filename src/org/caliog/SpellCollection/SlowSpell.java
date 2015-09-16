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
		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, getPower() * 20, 2));
	    }
	}
	return true;
    }

    public int getMinLevel() {
	return 3;
    }

    public int getFood() {
	return Math.round(getPower() / 60.0F * 6.0F);
    }

    public int getPower() {
	int level = getPlayer().getLevel();
	if (level < 5) {
	    return 20;
	}
	if (level < 10) {
	    return 30;
	}
	if (level < 20) {
	    return 50;
	}
	return 60;
    }

    public int getDamage() {
	return 0;
    }

    public int getDefense() {
	return 0;
    }
}
