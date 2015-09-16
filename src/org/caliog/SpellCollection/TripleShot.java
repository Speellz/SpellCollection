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
	}, 10L, 4L, getPower() * 4L);

	return true;
    }

    public int getMinLevel() {
	return 2;
    }

    public int getFood() {
	return 5;
    }

    public int getPower() {
	int level = getPlayer().getLevel();
	if (level < 5) {
	    return 2;
	}
	if (level < 10) {
	    return 5;
	}
	if (level < 15) {
	    return 7;
	}
	if (level < 20) {
	    return 9;
	}
	return 10;
    }

    public int getDamage() {
	return 0;
    }

    public int getDefense() {
	return 0;
    }
}
