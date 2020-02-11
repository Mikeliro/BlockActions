package com.lazynessmind.blockactions.base;

import com.lazynessmind.blockactions.event.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModBlock extends Block {

    public ModBlock(String name, Properties properties, ItemGroup group) {
        super(properties);
        Item.Properties itemProps = new Item.Properties().group(group);
        ItemRegister.ITEMS.register(name, () -> new BlockItem(this, itemProps));
    }
}
