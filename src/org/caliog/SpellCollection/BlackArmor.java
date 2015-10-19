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

	@Override
	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		long time = Math.round(getPower()) * 6 * 20L;
		activate(time);
		getPlayer().getPlayer().getWorld().playSound(getPlayer().getPlayer().getLocation(), Sound.ENDERDRAGON_GROWL, 0.4F, 1.0F);
		Manager.scheduleRepeatingTask(new Runnable() {
			public void run() {
				ParticleEffect.SPELL_MOB.display(new OrdinaryColor(1, 1, 1),
						getPlayer().getPlayer().getLocation().add(0, getPlayer().getPlayer().getEyeHeight() * 3 / 4, 0), 30D);

			}
		}, 0L, 1L, time);

		return true;
	}

	@Override
	public double getDamage() {
		return 0;
	}

	@Override
	public double getDefense() {
		return getPower();
	}

	@Override
	public float getPower() {
		int p = getPlayer().getLevel();
		return (float) (Math.sqrt(p) * 0.1);
	}

	@Override
	public int getFood() {
		return Math.round(getPower() * 6 + 2);
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

}
