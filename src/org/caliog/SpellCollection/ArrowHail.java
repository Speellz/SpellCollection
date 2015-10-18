package org.caliog.SpellCollection;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.caliog.myRPG.Entities.VolatileEntities;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class ArrowHail extends Spell {
	public ArrowHail(myClass player) {
		super(player, "ArrowHail");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		int counter = 0;
		double pitch = (getPlayer().getPlayer().getLocation().getPitch() + 90.0F) * 3.141592653589793D / 180.0D;
		double yaw = (getPlayer().getPlayer().getLocation().getYaw() + 90.0F) * 3.141592653589793D / 180.0D;
		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);
		Vector look = new Vector(x, z, y);
		for (Entity entity : getPlayer().getPlayer().getNearbyEntities(10.0D, 10.0D, 10.0D)) {
			if (counter > getAmount()) {
				break;
			}
			if (VolatileEntities.getMob(entity.getUniqueId()) != null) {
				Vector v = entity.getLocation().clone().toVector();
				v.subtract(getPlayer().getPlayer().getEyeLocation().clone().toVector());
				if (v.getX() * look.getX() + v.getY() * look.getY() + v.getZ() * look.getZ() >= 0.0D) {
					counter++;
					getPlayer().getPlayer().launchProjectile(Arrow.class, v);
				}
			}
		}
		getPlayer().getPlayer().setFoodLevel(getPlayer().getPlayer().getFoodLevel() - counter);
		return true;
	}

	@Override
	public double getDamage() {
		return 0;
	}

	@Override
	public double getDefense() {
		return 0;
	}

	@Override
	public float getPower() {
		return getPlayer().getLevel() * 0.9F + 0.1F;
	}

	@Override
	public int getFood() {
		return 0;
	}

	@Override
	public int getMinLevel() {
		return 5;
	}

	public int getAmount() {
		return Math.round(getPower() * 12);
	}

}
