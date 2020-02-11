package com.lazynessmind.blockactions.proxy;

import com.lazynessmind.blockactions.net.NetHandler;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void addToBus() {
        super.addToBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    }

    public void setupClient(FMLClientSetupEvent event) {
        NetHandler.registerClient();
    }
}
