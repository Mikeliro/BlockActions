package com.lazynessmind.blockactions.actions.placeaction;

import com.lazynessmind.blockactions.base.BlockActionBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class PlacerBlock extends BlockActionBase {

    public PlacerBlock() {
        super("placer");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PlacerTileEntity();
    }
}
