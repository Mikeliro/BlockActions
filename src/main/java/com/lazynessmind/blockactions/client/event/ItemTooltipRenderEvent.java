package com.lazynessmind.blockactions.client.event;

import com.lazynessmind.blockactions.event.BlockRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ItemTooltipRenderEvent {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack hoverStack = event.getItemStack();
        List<ITextComponent> tooltips = event.getToolTip();

        if (hoverStack.getItem() == BlockRegister.BREAKER.get().asItem()) {
            tooltips.add(new TranslationTextComponent("tooltip.breaker.action"));
            tooltips.add(new TranslationTextComponent("tooltip.breaker.tip"));
        } else if (hoverStack.getItem() == BlockRegister.PLACER.get().asItem()) {
            tooltips.add(new TranslationTextComponent("tooltip.placer.action"));
        } else if (hoverStack.getItem() == BlockRegister.HIT.get().asItem()) {
            tooltips.add(new TranslationTextComponent("tooltip.hit.action"));
            tooltips.add(new TranslationTextComponent("tooltip.hit.tip.one"));
            tooltips.add(new TranslationTextComponent("tooltip.hit.tip.two"));
        }
    }
}
