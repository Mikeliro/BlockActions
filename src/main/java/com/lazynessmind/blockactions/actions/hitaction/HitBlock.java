package com.lazynessmind.blockactions.actions.hitaction;

import com.lazynessmind.blockactions.base.BlockActionBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class HitBlock extends BlockActionBase {

    public HitBlock() {
        super("hit");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new HitTileEntity();
    }
}
