package com.lazynessmind.blockactions.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public abstract class ModItem extends Item {

    public ModItem(ItemGroup group) {
        super(new Item.Properties().group(group));
    }

    public ModItem(Properties properties) {
        super(properties);
    }
}
