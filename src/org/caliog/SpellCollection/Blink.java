package org.caliog.SpellCollection;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class Blink extends Spell {

	public Blink(RolecraftPlayer player) {
		super(player, "Blink");

	}

	@Override
	public boolean execute() {
		if (!super.execute())
			return false;
		HashSet<Material> set = new HashSet<Material>();
		set.add(Material.AIR);
		Location target = getPlayer().getPlayer().getTargetBlock(set, Math.round(getPower() + 5)).getLocation();
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

}
