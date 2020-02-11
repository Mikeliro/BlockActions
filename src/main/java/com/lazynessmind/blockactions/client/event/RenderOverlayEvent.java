package com.lazynessmind.blockactions.client.event;

import com.lazynessmind.blockactions.client.overlay.InfoOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderOverlayEvent {

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
            InfoOverlay.init(event.getWindow().getScaledWidth(), event.getWindow().getScaledHeight());
        }
    }
}
