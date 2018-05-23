package org.caliog.SpellCollection;

import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class ArmorSpell extends Spell {
	public ArmorSpell(RolecraftPlayer player) {
		super(player, "ArmorSpell");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		activate(getTime() * 20L);
		return true;
	}

	public int getTime() {
		final float x = ((float) getPower()) / getMaxPower();
		if (x < 1 / 12F)
			return 18;

		if (x < 1 / 6F)
			return 36;

		if (x < 0.25)
			return 54;

		if (x < 1 / 3F)
			return 72;

		if (x < 2 / 3F)
			return 90;

		if (x == 1)
			return 110;

		return 10;
	}

}
