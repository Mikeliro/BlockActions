package com.lazynessmind.blockactions.actions.breakaction;

import com.lazynessmind.blockactions.Configs;
import com.lazynessmind.blockactions.actions.placeaction.PlacerBlock;
import com.lazynessmind.blockactions.base.BlockActionTileEntityBase;
import com.lazynessmind.blockactions.event.TileEntityRegister;
import com.lazynessmind.blockactions.utils.InvUtils;
import com.lazynessmind.blockactions.utils.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class BreakerTileEntity extends BlockActionTileEntityBase {

    public Direction direction;

    public BreakerTileEntity() {
        super(TileEntityRegister.BREAKER_TILE.get());
    }

    @Override
    public void tick() {
        if (this.world != null) {
            if (!this.world.isRemote) {

                if (this.getBlockState().has(PlacerBlock.FACING)) {
                    this.direction = this.getBlockState().get(PlacerBlock.FACING);
                }

                //Stop if the block is being powered.
                if (!world.isBlockPowered(this.pos)) {

                    //Stop working is the current block to place is null or the block in front isn't air.
                    if (this.world.getBlockState(pos.offset(this.getFacing())) != Blocks.AIR.getDefaultState()) {
                        this.workTime--;
                        this.markDirty();
                    }

                    if (this.canWork()) {
                        if (this.getFacing() != null) {
                            BlockPos facingPos = this.pos.offset(this.getFacing());
                            BlockState facingState = this.world.getBlockState(facingPos);

                            boolean isAir = facingState.isAir(this.world, facingPos);
                            boolean isLiquid = BreakerUtil.isFluidAt(facingState.getBlock());
                            boolean isUnbreakable = facingState.getBlockHardness(this.world, facingPos) >= 0;
                            boolean isTileEntity = facingState.hasTileEntity();
                            boolean hasInv = InvUtils.hasInv(facingPos, this.world);

                            if (isTileEntity && !Configs.BREAK_TE.get()) return;
                            if (hasInv) return;
                            if (!isAir && !isLiquid && isUnbreakable) {
                                if (InvUtils.hasInvAbove(this.pos, this.world)) {
                                    boolean isInvFull = InvUtils.addToInvAbove(this.pos, this.world, Utils.newStack(facingState.getBlock().asItem()));
                                    this.world.destroyBlock(facingPos, !isInvFull); //return false if inv is full so - false is true
                                } else {
                                    this.world.destroyBlock(facingPos, true);
                                }
                                this.setWorkTime(this.getCoolDown());
                                this.markDirty();
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
