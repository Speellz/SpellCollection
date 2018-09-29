package org.caliog.SpellCollection;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class BlackArmor extends Spell {
	public BlackArmor(RolecraftPlayer player) {
		super(player, "BlackArmor");
	}

	@Override
	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		final float x = ((float) getPower()) / getMaxPower();
		long time = Math.round(x * 180 * 20L + 10 * 20);
		activate(time);
		getPlayer().getPlayer().getWorld().playSound(getPlayer().getPlayer().getLocation(), getSound(), 0.4F, 1.0F);
		Manager.scheduleRepeatingTask(new Runnable() {
			public void run() {
				getPlayer().getPlayer().getWorld().spawnParticle(
						Particle.REDSTONE, getPlayer().getPlayer().getLocation().add(0,
								getPlayer().getPlayer().getEyeHeight() * 3 / 4D, 0),
						10, 1F, 0.2F, 1F, new DustOptions(Color.BLACK, 1F));
			}
		}, 0L, 1L, time);

		return true;
	}

	public static Sound getSound() {
		try {
			Sound s = Sound.valueOf("ENTITY_ENDERDRAGON_GROWL");
			return s;
		} catch (Exception e) {
			return Sound.valueOf("ENTITY_ENDER_DRAGON_GROWL");
		}
	}

}
