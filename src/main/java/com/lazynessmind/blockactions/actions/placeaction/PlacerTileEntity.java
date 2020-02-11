package com.lazynessmind.blockactions.actions.placeaction;

import com.lazynessmind.blockactions.base.BlockActionTileEntityBase;
import com.lazynessmind.blockactions.event.TileEntityRegister;
import com.lazynessmind.blockactions.utils.InvUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class PlacerTileEntity extends BlockActionTileEntityBase implements ITickableTileEntity {

    public Direction direction;

    public PlacerTileEntity() {
        super(TileEntityRegister.PLACER_TILE.get());
    }

    @Override
    public void tick() {
        if (this.world != null) {
            if (!this.world.isRemote) {
                if (this.getBlockState().has(PlacerBlock.FACING)) {
                    this.direction = this.getBlockState().get(PlacerBlock.FACING);
                }

                if (!this.world.isBlockPowered(this.pos)) {
                    InvUtils.InvObject invObject = PlacerUtils.getBlockStateFromInvAbove(this.pos, this.world);
                    BlockState fromInv = invObject.getState();

                    //Stop working is the current block to place is null or the block in front isn't air.
                    if (fromInv != Blocks.AIR.getDefaultState() && this.world.getBlockState(pos.offset(this.getFacing())) == Blocks.AIR.getDefaultState()) {
                        this.workTime--;
                        this.markDirty();
                    }

                    if (this.canWork()) {
                        if (this.getFacing() != null) {
                            if (fromInv != Blocks.AIR.getDefaultState()) {
                                BlockPos facingPos = this.pos.offset(this.getFacing());
                                if (this.world.isAirBlock(facingPos)) {
                                    this.world.setBlockState(facingPos, fromInv, 11);
                                    this.world.playSound(null, pos, this.world.getBlockState(facingPos).getSoundType().getPlaceSound(), SoundCategory.BLOCKS, 1f, 1f);
                                    this.setWorkTime(this.getCoolDown());
                                    this.markDirty();
                                    if (invObject.getState() != Blocks.AIR.getDefaultState()) {
                                        InvUtils.removeFromInvAbove(this.pos, this.world, invObject.getIndex());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Direction getFacing() {
        return this.direction;
    }
}
