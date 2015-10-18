package org.caliog.SpellCollection;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class Blink extends Spell {

	public Blink(myClass player) {
		super(player, "Blink");

	}

	@Override
	public boolean execute() {
		if (!super.execute())
			return false;
		HashSet<Material> set = new HashSet<Material>();
		set.add(Material.AIR);
		Location target = getPlayer().getPlayer().getTargetBlock(set, Math.round(getPower())).getLocation();
		Location playerLocation = getPlayer().getPlayer().getLocation();
		target.setPitch(playerLocation.getPitch());
		target.setYaw(playerLocation.getYaw());
		if (getPlayer().getLevel() >= 10)
			getPlayer().getPlayer().getWorld().strikeLightningEffect(playerLocation);
		if (getPlayer().getLevel() >= 5)
			getPlayer().getPlayer().getWorld().strikeLightningEffect(target);
		getPlayer().getPlayer().teleport(target);
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
		return (int) (Math.sqrt(getPlayer().getLevel()) / 2F) + 4;
	}

	@Override
	public int getFood() {
		return Math.round(getPower()) - 4;
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

}
