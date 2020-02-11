package com.lazynessmind.blockactions.client.overlay;

import com.lazynessmind.blockactions.Configs;
import com.lazynessmind.blockactions.actions.breakaction.BreakerBlock;
import com.lazynessmind.blockactions.actions.placeaction.PlacerBlock;
import com.lazynessmind.blockactions.base.BlockActionBase;
import com.lazynessmind.blockactions.client.util.RayTraceUtil;
import com.lazynessmind.blockactions.net.NetHandler;
import com.lazynessmind.blockactions.net.msg.GetInfo;
import com.lazynessmind.blockactions.utils.InvUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;

import java.awt.*;

public class InfoOverlay {

    private static Minecraft mc;
    public static CompoundNBT infoNbt = new CompoundNBT();

    public static void init(int width, int height) {
        mc = Minecraft.getInstance();
        RayTraceUtil.setResult();
        RayTraceResult result = RayTraceUtil.getResult();

        if (result.getType().equals(RayTraceResult.Type.BLOCK)) {
            Block block = RayTraceUtil.getBlockFromRayTrace(result);
            BlockPos pos = RayTraceUtil.getPosFromRayTrace(result);

            if (block != null && pos != null) {
                if (block instanceof BlockActionBase) {
                    NetHandler.INSTANCE.sendToServer(new GetInfo("infoNbt", pos));
                    renderBlockActionInfo(infoNbt, width, height, (BlockActionBase) block, pos);
                }
            }
        }
    }

    private static void renderBlockActionInfo(CompoundNBT infoNbt, int width, int height, BlockActionBase blockActionBase, BlockPos pos) {
        int startPosX = width / 2;
        int startPosY = height / 2;

        if (mc.player.isSneaking()) {
            int index = 1;
            for (String key : infoNbt.keySet()) {
                mc.fontRenderer.drawStringWithShadow(infoNbt.getString(key), startPosX, startPosY + (index * 10), Color.WHITE.getRGB());
                index++;
            }
        } else {
            mc.fontRenderer.drawStringWithShadow(new TranslationTextComponent("infooverlay.sneak").getFormattedText(), startPosX, startPosY + 10, Color.GRAY.getRGB());

            String specialCase = "";
            if (blockActionBase instanceof PlacerBlock) {
                if (!InvUtils.hasInvAbove(pos, mc.world)) {
                    specialCase = new TranslationTextComponent("infooverlay.placer.needchest").getFormattedText();
                }
            } else if (blockActionBase instanceof BreakerBlock) {
                BlockPos facingPos = pos.offset(mc.world.getBlockState(pos).get(BlockActionBase.FACING));
                BlockState facingState = mc.world.getBlockState(facingPos);

                if (InvUtils.hasInv(facingPos, mc.world)) {
                    specialCase = new TranslationTextComponent("infooverlay.breaker.canbreakinv").getFormattedText();
                } else if (facingState.hasTileEntity() && !Configs.BREAK_TE.get()) {
                    specialCase = new TranslationTextComponent("infooverlay.breaker.canbreakte").getFormattedText();
                }
            }

            if (!specialCase.isEmpty()) {
                mc.fontRenderer.drawSplitString(specialCase, startPosX, startPosY + 20, 100, Color.RED.getRGB());
            }
        }
    }
}
