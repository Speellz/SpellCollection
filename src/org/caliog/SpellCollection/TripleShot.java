package org.caliog.SpellCollection;

import org.bukkit.entity.Arrow;
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class TripleShot extends Spell {
	public TripleShot(RolecraftPlayer player) {
		super(player, "TripleShot");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		Manager.scheduleRepeatingTask(new Runnable() {
			public void run() {
				getPlayer().getPlayer().launchProjectile(Arrow.class);
			}
		}, 6L, 5L, 15L);
		activate(25L);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.caliog.Rolecraft.Spells.Spell#getMaxPower()
	 * 
	 * This spell doesn't depend on power, so we set max-power to 1
	 */
	@Override
	public int getMaxPower() {
		return 1;
	}
}