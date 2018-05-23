package org.caliog.SpellCollection;

import org.bukkit.Sound;
import org.caliog.Rolecraft.Manager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;
import org.caliog.Rolecraft.XMechanics.Utils.ParticleEffect;
import org.caliog.Rolecraft.XMechanics.Utils.ParticleEffect.OrdinaryColor;

public class BlackArmor extends Spell {
	public BlackArmor(RolecraftPlayer player) {
		super(player, "BlackArmor");
	}

	@Override
	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		final float x = ((float) getPower()) / getMaxPower();
		long time = Math.round(x * 180 * 20L + 10 * 20);
		activate(time);
		getPlayer().getPlayer().getWorld().playSound(getPlayer().getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 0.4F, 1.0F);
		Manager.scheduleRepeatingTask(new Runnable() {
			public void run() {
				ParticleEffect.SPELL_MOB.display(new OrdinaryColor(1, 1, 1),
						getPlayer().getPlayer().getLocation().add(0, getPlayer().getPlayer().getEyeHeight() * 3 / 4D, 0), 30D);

			}
		}, 0L, 1L, time);

		return true;
	}

}
