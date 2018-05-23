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
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;
import org.caliog.Rolecraft.XMechanics.Utils.ParticleEffect;

public class Explosion extends Spell {
	public Explosion(RolecraftPlayer player) {
		super(player, "Explosion");
	}

	@SuppressWarnings("deprecation")
	@Override
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
				target.getWorld().playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.6F, 0.9F);
				for (Entity e : target.getWorld().getEntities()) {
					if (((e instanceof LivingEntity)) && (e.getEntityId() != getPlayer().getPlayer().getEntityId())
							&& (e.getLocation().distanceSquared(target.getLocation()) <= r * r)) {
						EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(getPlayer().getPlayer(), e, DamageCause.CUSTOM,
								getDamage());
						Bukkit.getPluginManager().callEvent(event);
					}
				}
			}

		}, 1L * Math.round(v.length()));

		return true;
	}

	public int getRadius() {
		final float x = ((float) getPower()) / getMaxPower();
		if (x < 0.15)
			return 4;
		if (x < 0.33)
			return 6;
		if (x < 0.66)
			return 8;
		if (x < 0.9)
			return 10;
		return 11;
	}

}
