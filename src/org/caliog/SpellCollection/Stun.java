package org.caliog.SpellCollection;

import java.util.HashMap;

import org.bukkit.Particle;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.EntityManager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class Stun extends Spell {

	public Stun(RolecraftPlayer player) {
		super(player, "Stun");
	}

	@Override
	public boolean execute() {
		if (!super.execute())
			return false;

		final double x = ((double) getPower()) / getMaxPower();

		final double r = (double) x * 10 + 4;

		for (double w = 0; w <= Math.PI; w += Math.PI / 4D) {
			Vector v = new Vector(Math.cos(2 * w), 0, Math.sin(2 * w));
			v.multiply((int) r);
			getPlayer().getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL,
					getPlayer().getPlayer().getLocation().add(v), 2, 1F, 1F, 1F);
		}

		final HashMap<Creature, Vector> entities = new HashMap<Creature, Vector>();
		for (Entity entity : getPlayer().getPlayer().getNearbyEntities(r, r / 2, r)) {
			if (EntityManager.isRegistered(entity.getUniqueId()) && entity instanceof Creature) {
				entities.put((Creature) entity, entity.getLocation().toVector().clone());
			}

		}

		Manager.scheduleRepeatingTask(new Runnable() {

			@Override
			public void run() {
				for (Creature c : entities.keySet()) {
					if (c != null && !c.isDead())
						c.teleport(entities.get(c).toLocation(c.getWorld()));
				}
			}
		}, 0L, 2L, (long) (x * 30 * 20L + 40L));
		return true;

	}

}
