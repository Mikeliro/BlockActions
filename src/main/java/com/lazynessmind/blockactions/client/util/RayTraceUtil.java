package com.lazynessmind.blockactions.client.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.*;

public class RayTraceUtil {

    public static RayTraceResult result = null;

    public static void setResult() {
        if (Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.ENTITY) {
            result = Minecraft.getInstance().objectMouseOver;
            return;
        } else if (Minecraft.getInstance().player != null && Minecraft.getInstance().playerController != null) {
            result = rayTrace(Minecraft.getInstance().player, Minecraft.getInstance().playerController.getBlockReachDistance(), 0);
        }
    }

    public static RayTraceResult getResult() {
        return result;
    }

    public static RayTraceResult rayTrace(PlayerEntity entity, double playerReach, float partialTicks) {
        Vec3d eyePosition = entity.getEyePosition(partialTicks);
        Vec3d lookVector = entity.getLook(partialTicks);
        Vec3d traceEnd = eyePosition.add(lookVector.x * playerReach, lookVector.y * playerReach, lookVector.z * playerReach);

        RayTraceContext context = new RayTraceContext(eyePosition, traceEnd, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.SOURCE_ONLY, entity);
        return entity.getEntityWorld().rayTraceBlocks(context);
    }

    public static Block getBlockFromRayTrace(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            return Minecraft.getInstance().world.getBlockState(((BlockRayTraceResult) result).getPos()).getBlock();
        } else {
            return null;
        }
    }

    public static BlockPos getPosFromRayTrace(RayTraceResult result) {
        if (result.getType().equals(RayTraceResult.Type.BLOCK)) {
            return ((BlockRayTraceResult) result).getPos();
        } else {
            return null;
        }
    }

    public static Entity getEntityFromRayTrace(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            return ((EntityRayTraceResult) result).getEntity();
        } else {
            return null;
        }
    }
}
