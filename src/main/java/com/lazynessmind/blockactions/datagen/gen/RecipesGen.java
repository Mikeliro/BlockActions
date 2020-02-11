package com.lazynessmind.blockactions.datagen.gen;

import com.lazynessmind.blockactions.BlockActions;
import com.lazynessmind.blockactions.event.BlockRegister;
import com.lazynessmind.blockactions.event.ItemRegister;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class RecipesGen extends RecipeProvider {

    public RecipesGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(BlockRegister.BREAKER.get())
                .patternLine("CCC")
                .patternLine("CRC")
                .patternLine("CPC")
                .key('C', Blocks.COBBLESTONE)
                .key('R', Blocks.REDSTONE_BLOCK)
                .key('P', Blocks.PISTON)
                .setGroup(BlockActions.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegister.PLACER.get())
                .patternLine("CCC")
                .patternLine("CRC")
                .patternLine("CDC")
                .key('C', Blocks.COBBLESTONE)
                .key('R', Blocks.REDSTONE_BLOCK)
                .key('D', Blocks.DROPPER)
                .setGroup(BlockActions.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockRegister.HIT.get())
                .patternLine("CCC")
                .patternLine("CRC")
                .patternLine("CSC")
                .key('C', Blocks.COBBLESTONE)
                .key('R', Blocks.REDSTONE_BLOCK)
                .key('S', Items.DIAMOND_SWORD)
                .setGroup(BlockActions.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.SPEED_UPGRADE.get())
                .patternLine("ONO")
                .patternLine("NRN")
                .patternLine("ONO")
                .key('O', Blocks.OBSIDIAN)
                .key('R', Blocks.REDSTONE_BLOCK)
                .key('N', Items.NETHER_WART)
                .setGroup(BlockActions.MOD_ID)
                .addCriterion("obsidian", InventoryChangeTrigger.Instance.forItems(Blocks.OBSIDIAN))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.ATTACK_UPGRADE.get())
                .patternLine("OQO")
                .patternLine("QRQ")
                .patternLine("OQO")
                .key('O', Blocks.OBSIDIAN)
                .key('R', Blocks.REDSTONE_BLOCK)
                .key('Q', Items.QUARTZ)
                .setGroup(BlockActions.MOD_ID)
                .addCriterion("obsidian", InventoryChangeTrigger.Instance.forItems(Blocks.OBSIDIAN))
                .build(consumer);

    }
}