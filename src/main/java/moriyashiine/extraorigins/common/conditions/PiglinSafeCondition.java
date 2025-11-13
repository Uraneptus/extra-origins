package moriyashiine.extraorigins.common.conditions;

import io.github.apace100.apoli.condition.ConditionConfiguration;
import io.github.apace100.apoli.condition.context.EntityConditionContext;
import io.github.apace100.apoli.condition.type.EntityConditionType;
import moriyashiine.extraorigins.common.init.ModConditions;
import org.jetbrains.annotations.NotNull;

public class PiglinSafeCondition extends EntityConditionType {

    @Override
    public boolean test(EntityConditionContext context) {
        return context.entity().getWorld().getDimension().piglinSafe();
    }

    @Override
    public @NotNull ConditionConfiguration<?> getConfig() {
        return ModConditions.PIGLIN_SAFE;
    }
}
