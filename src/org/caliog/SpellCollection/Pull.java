package org.caliog.SpellCollection;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class Pull extends Spell {

	public Pull(RolecraftPlayer player) {
		super(player, "Pull");
	}

	// TODO not tested yet
	@Override
	public boolean execute() {
		if (!super.execute()) {
			return false;
		}

		final int r = getRadius();
		int amount = 0;
		final int maxAmount = getAmount();
		final Vector v = getPlayer().getPlayer().getLocation().toVector().clone();
		List<Entity> entities = getPlayer().getPlayer().getNearbyEntities(r, r, r);
		List<LivingEntity> pullList = new ArrayList<LivingEntity>();
		for (Entity e : entities) {
			if (e instanceof LivingEntity) {
				if (amount > maxAmount)
					break;
				pullList.add((LivingEntity) e);
				e.teleport(v.toLocation(getPlayer().getPlayer().getWorld()));
				amount++;
			}
		}
		final int s = getDuration();
		Manager.scheduleRepeatingTask(new Runnable() {

			@Override
			public void run() {
				for (Entity e : pullList)
					e.teleport(v.toLocation(getPlayer().getPlayer().getWorld()));
			}
		}, 5L, 10L, s * 20L);

		return true;
	}

	private int getDuration() {
		return 3;// TODO in seconds
	}

	public int getRadius() {
		return 5;// TODO
	}

	public int getAmount() {
		return 2;// TODO
	}
}
