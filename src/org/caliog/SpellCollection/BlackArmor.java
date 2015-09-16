package org.caliog.SpellCollection;

import org.bukkit.Sound;
import org.caliog.myRPG.Manager;
import org.caliog.myRPG.Entities.myClass;
import org.caliog.myRPG.Spells.Spell;
import org.caliog.myRPG.Utils.ParticleEffect;
import org.caliog.myRPG.Utils.ParticleEffect.OrdinaryColor;

public class BlackArmor extends Spell {
    public BlackArmor(myClass player) {
	super(player, "BlackArmor");
    }

    public int getMinLevel() {
	return 1;
    }

    public int getFood() {
	return getPower();
    }

    public int getPower() {
	int p = getPlayer().getLevel();
	p = (int) Math.round(Math.sqrt(p) * (1.0F + getPlayer().getIntelligence() / 100.0F));
	return p;
    }

    public boolean execute() {
	if (!super.execute()) {
	    return false;
	}
	long time = getPower() * 6 * 20L;
	activate(time);
	getPlayer().getPlayer().getWorld()
		.playSound(getPlayer().getPlayer().getLocation(), Sound.ENDERDRAGON_GROWL, 0.4F, 1.0F);
	Manager.scheduleRepeatingTask(new Runnable() {
	    public void run() {
		ParticleEffect.SPELL_MOB
			.display(
				new OrdinaryColor(1, 1, 1),
				getPlayer().getPlayer().getLocation()
					.add(0, getPlayer().getPlayer().getEyeHeight() * 3 / 4, 0), 30D);

	    }
	}, 0L, 1L, time);

	return true;
    }

    public int getDamage() {
	return 0;
    }

    public int getDefense() {
	return getPower();
    }
}
