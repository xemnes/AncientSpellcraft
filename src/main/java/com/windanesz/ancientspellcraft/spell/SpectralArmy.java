package com.windanesz.ancientspellcraft.spell;

import com.windanesz.ancientspellcraft.AncientSpellcraft;
import com.windanesz.ancientspellcraft.entity.living.EntityAnimatedItem;
import electroblob.wizardry.registry.WizardryItems;
import electroblob.wizardry.util.EntityUtils;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class SpectralArmy extends Animate {

	public SpectralArmy() {
		super(AncientSpellcraft.MODID, "spectral_army");
	}

	@Override
	protected void addMinionExtras(EntityAnimatedItem minion, BlockPos pos, @Nullable EntityLivingBase caster, SpellModifiers modifiers, int alreadySpawned) {
		super.addMinionExtras(minion, pos, caster, modifiers, alreadySpawned);

		minion.setItemStackToSlot(EntityEquipmentSlot.HEAD, conjureItem(modifiers, WizardryItems.spectral_helmet));
		minion.setItemStackToSlot(EntityEquipmentSlot.CHEST, conjureItem(modifiers, WizardryItems.spectral_chestplate));
		minion.setItemStackToSlot(EntityEquipmentSlot.LEGS, conjureItem(modifiers, WizardryItems.spectral_leggings));
		minion.setItemStackToSlot(EntityEquipmentSlot.FEET, conjureItem(modifiers, WizardryItems.spectral_boots));
		// this is just here to trigger the AI task
		minion.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, conjureItem(modifiers, Items.AIR));
		minion.setHasArmour(true);

		// boosting damage
		IAttributeInstance attack_damage = minion.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		if (attack_damage != null) {
			attack_damage.applyModifier( // Apparently some things don't have an attack damage
					new AttributeModifier(POTENCY_ATTRIBUTE_MODIFIER, 2, EntityUtils.Operations.MULTIPLY_FLAT));
		}

		// nerf speed
		IAttributeInstance speed = minion.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if (speed != null) {
			speed.applyModifier( // Apparently some things don't have an attack damage
					new AttributeModifier("speed_modifier", -0.5f, EntityUtils.Operations.MULTIPLY_FLAT));
		}

		// nerf speed
		IAttributeInstance attack_speed = minion.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
		if (attack_speed != null) {
			attack_speed.applyModifier( // Apparently some things don't have an attack damage
					new AttributeModifier("attack_speed_modifier", 0.5f, EntityUtils.Operations.MULTIPLY_FLAT));
		}

		minion.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier(HEALTH_MODIFIER, 0.5  * modifiers.get(HEALTH_MODIFIER) - 1, EntityUtils.Operations.MULTIPLY_CUMULATIVE));
		minion.setHealth(minion.getMaxHealth()); // Need to set this because we may have just modified the value
	}
}
