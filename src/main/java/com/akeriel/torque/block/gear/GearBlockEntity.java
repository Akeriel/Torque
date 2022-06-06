package com.akeriel.torque.block.gear;

import com.akeriel.torque.block.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GearBlockEntity extends BlockEntity{

    public GearBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.GEAR_ENTITY.get(), pos, state);
    }
}
