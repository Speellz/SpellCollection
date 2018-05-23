package org.caliog.SpellCollection;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.EntityManager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class ArrowHail extends Spell {
	public ArrowHail(RolecraftPlayer player) {
		super(player, "ArrowHail");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		double pitch = (getPlayer().getPlayer().getLocation().getPitch() + 90.0F) * 3.141592653589793D / 180.0D;
		double yaw = (getPlayer().getPlayer().getLocation().getYaw() + 90.0F) * 3.141592653589793D / 180.0D;
		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);
		Vector look = new Vector(x, z, y);
		activate(10L);
		Manager.scheduleTask(new Runnable() {

			@Override
			public void run() {
				int counter = 0;
				for (Entity entity : getPlayer().getPlayer().getNearbyEntities(10.0D, 10.0D, 10.0D)) {
					if (counter > getAmount()) {
						break;
					}
					if (EntityManager.getMob(entity.getUniqueId()) != null) {
						Vector v = entity.getLocation().clone().toVector();
						v.subtract(getPlayer().getPlayer().getEyeLocation().clone().toVector());
						if (v.getX() * look.getX() + v.getY() * look.getY() + v.getZ() * look.getZ() >= 0.0D) {
							counter++;
							getPlayer().getPlayer().launchProjectile(Arrow.class, v);
						}
					}
				}
				getPlayer().getPlayer().setFoodLevel(getPlayer().getPlayer().getFoodLevel() - counter);
				activate(10L);

			}
		}, 5L);
		return true;
	}

	public int getAmount() {
		final float x = ((float) getPower()) / getMaxPower();
		if (x < 0.33)
			return 8;
		if (x < 0.66)
			return 12;
		if (x == 1)
			return 24;
		return 18;
	}

}
