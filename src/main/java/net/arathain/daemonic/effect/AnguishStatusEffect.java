package net.arathain.daemonic.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class AnguishStatusEffect extends StatusEffect {
    public AnguishStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL,
                0xd40d95); // color in RGB
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

                    (entity).damage(DamageSource.WITHER, 2 << amplifier);

    }
}
