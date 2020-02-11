package com.lazynessmind.blockactions.net.msg;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class ReturnInfo {

    private final String infoOverlayClassName = "com.lazynessmind.blockactions.client.overlay.InfoOverlay";

    private CompoundNBT compoundNBT;
    private String infoNbtField;

    public ReturnInfo(PacketBuffer buffer) {
        this.compoundNBT = buffer.readCompoundTag();
        this.infoNbtField = buffer.readString();
    }

    public ReturnInfo(CompoundNBT compoundNBT, String infoNbtField) {
        this.compoundNBT = compoundNBT;
        this.infoNbtField = infoNbtField;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeCompoundTag(compoundNBT);
        buffer.writeString(infoNbtField);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            try {
                Class clazz = Class.forName(infoOverlayClassName);
                Field linesFiled = clazz.getDeclaredField(infoNbtField);
                linesFiled.set(clazz, compoundNBT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
