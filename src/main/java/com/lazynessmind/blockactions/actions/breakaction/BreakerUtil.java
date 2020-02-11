package com.lazynessmind.blockactions.actions.breakaction;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;

public class BreakerUtil {

    public static boolean isFluidAt(Block block) {
        return block instanceof FlowingFluidBlock;
    }
}
