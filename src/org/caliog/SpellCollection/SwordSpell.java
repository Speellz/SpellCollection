package org.caliog.SpellCollection;

import org.caliog.myRPG.Manager;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;
import org.caliog.myRPG.Utils.ParticleEffect;

public class SwordSpell extends Spell {
    public SwordSpell(myClass player) {
	super(player, "SwordSpell");
    }

    public boolean execute() {
	if (!super.execute()) {
	    return false;
	}
	long time = Math.round(getPower() / 5.0F * 20.0F * 60.0F);
	activate(time);
	Manager.scheduleRepeatingTask(new Runnable() {
	    public void run() {
		ParticleEffect.VILLAGER_HAPPY.display(0.2F, 0.15F, 0.2F, 0.8F, 2, getPlayer().getPlayer().getLocation()
			.add(0, getPlayer().getPlayer().getEyeHeight() * 3 / 4, 0), 30D);

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
	    return 2;
	}
	if (level < 10) {
	    return 3;
	}
	if (level < 15) {
	    return 4;
	}
	return 5;
    }

    public int getDamage() {
	return getPower();
    }

    public int getDefense() {
	return 0;
    }
}
