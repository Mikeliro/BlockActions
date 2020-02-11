package com.lazynessmind.blockactions.event;

import com.lazynessmind.blockactions.BlockActions;
import com.lazynessmind.blockactions.items.AttackUpgradeItem;
import com.lazynessmind.blockactions.items.SpeedUpgradeItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BlockActions.MOD_ID);

    public static RegistryObject<SpeedUpgradeItem> SPEED_UPGRADE = ITEMS.register("speed_upgrade", SpeedUpgradeItem::new);
    public static RegistryObject<AttackUpgradeItem> ATTACK_UPGRADE = ITEMS.register("attack_upgrade", AttackUpgradeItem::new);
}
