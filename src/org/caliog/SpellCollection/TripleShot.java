package org.caliog.SpellCollection;

import org.bukkit.entity.Arrow;
import org.caliog.myRPG.Manager;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class TripleShot extends Spell {
	public TripleShot(myClass player) {
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
		}, 10L, 4L, Math.round(getPower() * 4L));

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
		int level = getPlayer().getLevel();
		if (level < 5) {
			return 2;
		}
		if (level < 10) {
			return 3;
		}
		if (level < 20) {
			return 5;
		}
		if (level < 40) {
			return 6;
		}
		return 7;
	}

	@Override
	public int getFood() {
		return Math.round(getPower() / 7F * 8 + 2);
	}

	@Override
	public int getMinLevel() {
		return 1;
	}
}
