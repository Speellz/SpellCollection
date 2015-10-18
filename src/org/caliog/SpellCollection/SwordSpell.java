package org.caliog.SpellCollection;

import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class SwordSpell extends Spell {
	public SwordSpell(myClass player) {
		super(player, "SwordSpell");
	}

	@Override
	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		long time = Math.round(getPower() / 5.0F * 20.0F * 60.0F);
		activate(time);
		return true;
	}

	@Override
	public double getDamage() {
		return (1 + getPower()) * getPlayer().getDamage();
	}

	@Override
	public double getDefense() {
		return 0;
	}

	@Override
	public float getPower() {
		int level = getPlayer().getLevel();
		if (level < 5) {
			return 0.2F;
		}
		if (level < 10) {
			return 0.3F;
		}
		if (level < 15) {
			return 0.45F;
		}
		return 0.5F;
	}

	@Override
	public int getFood() {
		return Math.round(getPower());
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

}
