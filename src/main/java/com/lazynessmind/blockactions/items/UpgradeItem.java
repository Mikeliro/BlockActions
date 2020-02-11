package com.lazynessmind.blockactions.items;

import com.lazynessmind.blockactions.Configs;
import com.lazynessmind.blockactions.base.BlockActionBase;
import com.lazynessmind.blockactions.base.BlockActionTileEntityBase;
import com.lazynessmind.blockactions.base.ModItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class UpgradeItem extends ModItem {

    public UpgradeItem() {
        super(ItemGroup.REDSTONE);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();

        if (!world.isRemote) {
            if (world.getBlockState(pos).getBlock() instanceof BlockActionBase) {
                if (world.getBlockState(pos).hasTileEntity()) {
                    if (world.getTileEntity(pos) instanceof BlockActionTileEntityBase) {
                        BlockActionTileEntityBase tileEntityBase = (BlockActionTileEntityBase) world.getTileEntity(pos);
                        if (tileEntityBase != null) {
                            if (tileEntityBase.getCurrentUpgrades() < Configs.MAX_UPGRADE_COUNT.get()) {
                                ApplyState applyState = this.applyUpgrade(tileEntityBase);
                                if (applyState.getResult()) {
                                    tileEntityBase.currentUpgrades++;
                                    tileEntityBase.getUpgradeItems().add(applyState.getAppliedUpg());
                                    context.getItem().shrink(1);
                                }
                            }
                        }
                    }
                }
            }
        }
        return super.onItemUse(context);
    }

    public abstract ApplyState applyUpgrade(BlockActionTileEntityBase tileEntityBase);

    public static class ApplyState {

        private ItemStack appliedUpg;
        private boolean result;

        public static final ApplyState FAIL = new ApplyState(null, false);

        public ApplyState(ItemStack appliedUpg, boolean result) {
            this.appliedUpg = appliedUpg;
            this.result = result;
        }

        public ItemStack getAppliedUpg() {
            return this.appliedUpg;
        }

        public boolean getResult() {
            return this.result;
        }
    }
}
