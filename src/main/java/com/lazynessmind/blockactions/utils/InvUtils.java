package com.lazynessmind.blockactions.utils;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InvUtils {

    public static boolean hasInv(BlockPos pos, World world) {
        if (world.getBlockState(pos).hasTileEntity()) {
            if (world.getTileEntity(pos) instanceof IInventory) {
                IInventory iInventory = (IInventory) world.getTileEntity(pos);
                return iInventory != null;
            }
        }
        return false;
    }

    public static boolean hasInvAbove(BlockPos pos, World world) {
        if (world.getBlockState(pos.up()).hasTileEntity()) {
            if (world.getTileEntity(pos.up()) instanceof IInventory) {
                IInventory iInventory = (IInventory) world.getTileEntity(pos.up());
                return iInventory != null;
            }
        }
        return false;
    }

    public static IInventory getInvAbove(BlockPos pos, World world) {
        return hasInvAbove(pos, world) ? (IInventory) world.getTileEntity(pos.up()) : null;
    }

    public static void removeFromInvAbove(BlockPos pos, World world, int index) {
        if (hasInvAbove(pos, world)) {
            IInventory iInventory = getInvAbove(pos, world);
            if (iInventory != null) {
                iInventory.decrStackSize(index, 1);
                iInventory.markDirty();
            }
        }
    }

    public static boolean addToInvAbove(BlockPos pos, World world, ItemStack stack) {
        if (hasInvAbove(pos, world)) {
            IInventory iInventory = getInvAbove(pos, world);
            if (iInventory != null) {
                for (int i = 0; i < iInventory.getSizeInventory(); ) {
                    ItemStack slot = iInventory.getStackInSlot(i);
                    if (slot.getItem() == stack.getItem() && ItemStack.areItemStackTagsEqual(slot, stack)) {
                        int count = slot.getCount() + 1;
                        if (count <= iInventory.getInventoryStackLimit()) {
                            slot.setCount(count);
                            iInventory.markDirty();
                            return true;
                        } else {
                            i++;
                        }
                    } else if (slot == ItemStack.EMPTY) {
                        iInventory.setInventorySlotContents(i, stack);
                        iInventory.markDirty();
                        return true;
                    } else {
                        i++;
                    }
                }
            }
        }
        return false;
    }

    public static class InvObject {

        private int index;
        private BlockState state;

        public InvObject(int index, BlockState state) {
            this.index = index;
            this.state = state;
        }

        public int getIndex() {
            return this.index;
        }

        public BlockState getState() {
            return this.state;
        }
    }
}
