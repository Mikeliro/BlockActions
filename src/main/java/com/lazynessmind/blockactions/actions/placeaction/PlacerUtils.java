package com.lazynessmind.blockactions.actions.placeaction;

import com.lazynessmind.blockactions.utils.InvUtils;
import com.lazynessmind.blockactions.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlacerUtils {

    public static InvUtils.InvObject getBlockStateFromInvAbove(BlockPos pos, World world) {
        if (InvUtils.hasInvAbove(pos, world)) {
            IInventory iInventory = InvUtils.getInvAbove(pos, world);
            if (iInventory != null) {
                for (int i = 0; i < iInventory.getSizeInventory(); ) {
                    if (iInventory.getStackInSlot(i).getItem() instanceof BlockItem) {
                        iInventory.markDirty(); //Fact: Probably this isn't needed.
                        return new InvUtils.InvObject(i, Utils.getStateFromItem(iInventory.getStackInSlot(i)));
                    } else {
                        i++;
                    }
                }
            }
        }
        return new InvUtils.InvObject(0, Blocks.AIR.getDefaultState());
    }
}
