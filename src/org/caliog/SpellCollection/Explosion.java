package org.caliog.SpellCollection;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.util.Vector;
import org.caliog.myRPG.Manager;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;
import org.caliog.myRPG.Utils.ParticleEffect;

public class Explosion extends Spell {
    public Explosion(myClass player) {
	super(player, "Explosion");
    }

    public int getFood() {
	int food = (int) Math.sqrt(getPower() * 2);
	return food;
    }

    public int getPower() {
	int p = getPlayer().getLevel();
	p = (int) Math.round(Math.sqrt(p) * 4.0D);
	p = Math.round(p * (1.0F + getPlayer().getIntelligence() / 200.0F));
	return p;
    }

    public int getRadius() {
	int p = getPower();
	if (p < 10) {
	    return 5;
	}
	if (p < 15) {
	    return 6;
	}
	if (p < 20) {
	    return 7;
	}
	return 9;
    }

    @SuppressWarnings("deprecation")
    public boolean execute() {
	if (!super.execute()) {
	    return false;
	}
	final int r = getRadius();
	Set<Material> set = new HashSet<Material>();
	set.add(Material.AIR);
	final Block target = getPlayer().getPlayer().getTargetBlock(set, r * 2).getRelative(BlockFace.UP);
	Vector v = target.getLocation().toVector().subtract(getPlayer().getPlayer().getEyeLocation().toVector());
	ParticleEffect.EXPLOSION_NORMAL.display(v, 0.2F, getPlayer().getPlayer().getEyeLocation(), 30D);
	Manager.scheduleTask(new Runnable() {

	    @Override
	    public void run() {
		ParticleEffect.EXPLOSION_LARGE.display(0.2F, 0.2F, 0.2F, 0.2F, 5, target.getLocation(), 30D);
		target.getWorld().playSound(target.getLocation(), Sound.EXPLODE, 0.6F, 0.9F);
		for (Entity e : target.getWorld().getEntities()) {
		    if (((e instanceof LivingEntity)) && (e.getEntityId() != getPlayer().getPlayer().getEntityId())
			    && (e.getLocation().distanceSquared(target.getLocation()) <= r * r)) {
			EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(getPlayer().getPlayer(), e,
				DamageCause.CUSTOM, (double) getPower());
			Bukkit.getPluginManager().callEvent(event);
		    }
		}

	    }

	}, 1L * Math.round(v.length()));

	return true;
    }

    public int getMinLevel() {
	return 1;
    }

    public int getDamage() {
	return 0;
    }

    public int getDefense() {
	return 0;
    }
}