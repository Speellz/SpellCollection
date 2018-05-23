package org.caliog.SpellCollection;

import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class SwordSpell extends Spell {
	public SwordSpell(RolecraftPlayer player) {
		super(player, "SwordSpell");
	}

	@Override
	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		long time = 140;
		final float x = ((float) getPower()) / getMaxPower();
		if (x < 0.1)
			time = 5 * getPower();
		else if (x < 0.2)
			time = 3 * getPower() + 20;
		else if (x < 0.5)
			time = 2 * getPower() + 40;
		activate(time * 20);
		return true;
	}

}
