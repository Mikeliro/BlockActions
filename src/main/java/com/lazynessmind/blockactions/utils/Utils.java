package com.lazynessmind.blockactions.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;

public class Utils {

    public static ItemStack newStack(Item item) {
        return new ItemStack(item);
    }

    public static BlockState getStateFromItem(ItemStack item) {
        return Block.getBlockFromItem(item.getItem()).getDefaultState();
    }

    public static String convertTickSec(int ticks) {
        float sec = (float) ticks / 20;
        return String.valueOf(sec).concat("sec");
    }

    public static void saveListToNbt(String key, CompoundNBT tag, NonNullList<ItemStack> stacks) {
        ListNBT items = new ListNBT();
        for (ItemStack stack : stacks) {
            CompoundNBT compoundnbt = new CompoundNBT();
            stack.write(compoundnbt);
            items.add(compoundnbt);
        }
        tag.put(key, items);
    }

    public static NonNullList<ItemStack> loadListNbt(String key, CompoundNBT nbt) {
        NonNullList<ItemStack> itemStacks = NonNullList.create();
        ListNBT items = nbt.getList(key, 10);
        for (int i = 0; i < items.size(); i++) {
            CompoundNBT itemStack = items.getCompound(i);
            itemStacks.add(ItemStack.read(itemStack));
        }
        return itemStacks;
    }
}
