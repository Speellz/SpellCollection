package org.caliog.SpellCollection;

import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;

public class ArmorSpell extends Spell {
	public ArmorSpell(myClass player) {
		super(player, "ArmorSpell");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		activate(getTime() * 20L);
		return true;
	}

	@Override
	public double getDamage() {
		return 0;
	}

	@Override
	public double getDefense() {
		return (1 + getPower()) * getPlayer().getDefense();
	}

	@Override
	public float getPower() {
		return getPlayer().getLevel() * 0.004F + 0.1F;
	}

	@Override
	public int getFood() {
		return Math.round(getPower() * 4 + 2);
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

	public int getTime() {
		int level = getPlayer().getLevel();
		if (level < 5) {
			return 18;
		}
		if (level < 10) {
			return 36;
		}
		if (level < 15) {
			return 54;
		}
		if (level < 20) {
			return 72;
		}
		return 90;
	}

}
