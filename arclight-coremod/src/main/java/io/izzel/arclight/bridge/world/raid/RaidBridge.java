package io.izzel.arclight.bridge.world.raid;

import net.minecraft.entity.monster.AbstractRaiderEntity;

import java.util.Collection;

public interface RaidBridge {

    Collection<AbstractRaiderEntity> bridge$getRaiders();
}
