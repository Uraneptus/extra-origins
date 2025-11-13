package moriyashiine.extraorigins.common.conditions;

import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.context.DamageConditionContext;
import io.github.apace100.apoli.condition.type.DamageConditionType;
import moriyashiine.extraorigins.common.init.ModConditions;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.jetbrains.annotations.NotNull;

public class ShotFromCrossbowCondition extends DamageConditionType {

    @Override
    public boolean test(DamageConditionContext context) {
        return context.source().getSource() instanceof PersistentProjectileEntity projectile && projectile.isShotFromCrossbow();
    }

    @Override
    public @NotNull ConditionConfiguration<?> getConfig() {
        return ModConditions.SHOT_FROM_CROSSBOW;
    }
}
