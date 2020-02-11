package com.lazynessmind.blockactions.actions.breakaction;

import com.lazynessmind.blockactions.base.BlockActionBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BreakerBlock extends BlockActionBase {

    public BreakerBlock() {
        super("breaker");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BreakerTileEntity();
    }
}