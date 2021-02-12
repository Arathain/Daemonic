package net.arathain.daemonic.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class TransmutationStatusEffect extends StatusEffect {
    public TransmutationStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL,
                0x66ff33); // color in RGB
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if (((PlayerEntity) entity).experienceLevel > 40) {
                if ((entity).getHealth() < (entity).getMaxHealth()) {
                    (entity).heal(1 << amplifier);
                    ((PlayerEntity) entity).addExperience(-30 << amplifier);
                }
            }
        }
    }
}