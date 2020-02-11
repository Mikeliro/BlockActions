package com.lazynessmind.blockactions.net;

import com.lazynessmind.blockactions.BlockActions;
import com.lazynessmind.blockactions.net.msg.GetInfo;
import com.lazynessmind.blockactions.net.msg.ReturnInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetHandler {

    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(BlockActions.MOD_ID, "netchannel"), () -> "1.0", s -> true, s -> true);
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerCommon() {
        INSTANCE.registerMessage(nextID(), GetInfo.class, GetInfo::toBytes, GetInfo::new, GetInfo::handle);
        INSTANCE.registerMessage(nextID(), ReturnInfo.class, ReturnInfo::toBytes, ReturnInfo::new, ReturnInfo::handle);
    }

    public static void registerClient() {

    }
}
