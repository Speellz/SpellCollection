package org.caliog.SpellCollection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.caliog.Rolecraft.Entities.EntityManager;
import org.caliog.Rolecraft.Entities.Player.RolecraftPlayer;
import org.caliog.Rolecraft.Spells.Spell;

public class SlowSpell extends Spell {
	public SlowSpell(RolecraftPlayer player) {
		super(player, "SlowSpell");
	}

	public boolean execute() {
		if (!super.execute()) {
			return false;
		}
		final float x = ((float) getPower()) / getMaxPower();
		int w = Math.round(x * 10.0F + 2);
		for (Entity entity : getPlayer().getPlayer().getNearbyEntities(w, w, w)) {
			if ((EntityManager.getMob(entity.getUniqueId()) != null) && ((entity instanceof LivingEntity))) {
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Math.round(x * 90 * 20 + 60), 2));
			}
		}
		return true;
	}

}
