package io.izzel.arclight.mixin.core.inventory.container;

import io.izzel.arclight.bridge.entity.player.PlayerEntityBridge;
import io.izzel.arclight.bridge.inventory.CraftingInventoryBridge;
import io.izzel.arclight.mod.util.ArclightCaptures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.text.TranslationTextComponent;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftInventoryView;
import org.bukkit.inventory.InventoryView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerContainer.class)
public class PlayerContainerMixin extends ContainerMixin {

    // @formatter:off
    @Shadow @Final private CraftingInventory field_75181_e;
    @Shadow @Final private CraftResultInventory field_75179_f;
    // @formatter:on

    private CraftInventoryView bukkitEntity;
    private PlayerInventory player;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void arclight$init(PlayerInventory playerInventory, boolean localWorld, PlayerEntity playerIn, CallbackInfo ci) {
        this.player = playerInventory;
        ((CraftingInventoryBridge) this.field_75181_e).bridge$setOwner(playerInventory.player);
        ((CraftingInventoryBridge) this.field_75181_e).bridge$setResultInventory(this.field_75179_f);
        this.setTitle(new TranslationTextComponent("container.crafting"));
    }

    @Inject(method = "onCraftMatrixChanged", at = @At("HEAD"))
    public void arclight$captureContainer(IInventory inventoryIn, CallbackInfo ci) {
        ArclightCaptures.captureWorkbenchContainer((Container) (Object) this);
    }

    @Override
    public InventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.field_75181_e, this.field_75179_f);
        bukkitEntity = new CraftInventoryView(((PlayerEntityBridge) this.player.player).bridge$getBukkitEntity(), inventory, (Container) (Object) this);
        return bukkitEntity;
    }
}
