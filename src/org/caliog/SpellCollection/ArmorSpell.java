package org.caliog.SpellCollection;

import org.caliog.myRPG.Manager;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;
import org.caliog.myRPG.Utils.ParticleEffect;

public class ArmorSpell extends Spell {
    public ArmorSpell(myClass player) {
	super(player, "ArmorSpell");
    }

    public boolean execute() {
	if (!super.execute()) {
	    return false;
	}
	long time = Math.round(getPower() / 5.0F * 20.0F * 90.0F);
	activate(time);
	Manager.scheduleRepeatingTask(new Runnable() {
	    public void run() {
		ParticleEffect.SPELL_WITCH.display(0.2F, 0.3F, 0.2F, 0.2F, 10, getPlayer().getPlayer().getLocation(),
			20D);

	    }
	}, 0L, 1L, time);
	return true;
    }

    public int getMinLevel() {
	return 1;
    }

    public int getFood() {
	return getPower();
    }

    public int getPower() {
	int level = getPlayer().getLevel();
	if (level < 5) {
	    return 1;
	}
	if (level < 10) {
	    return 2;
	}
	if (level < 15) {
	    return 3;
	}
	if (level < 20) {
	    return 4;
	}
	return 5;
    }

    public int getDamage() {
	return 0;
    }

    public int getDefense() {
	return getPower();
    }
}
