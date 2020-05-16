package io.izzel.arclight.mixin.core.world.storage.loot;

import io.izzel.arclight.mod.ArclightConstants;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootParameters;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LootParameters.class)
public class LootParametersMixin {

    private static final LootParameter<Integer> LOOTING_MOD = ArclightConstants.LOOTING_MOD;
}
