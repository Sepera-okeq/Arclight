package io.izzel.arclight.bridge.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface AbstractFurnaceTileEntityBridge {

    void bridge$dropExp(PlayerEntity entity, ItemStack itemStack, int amount);
}
