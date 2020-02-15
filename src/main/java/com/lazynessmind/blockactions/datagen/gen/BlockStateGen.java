package com.lazynessmind.blockactions.datagen.gen;

import com.lazynessmind.blockactions.BlockActions;
import com.lazynessmind.blockactions.event.BlockRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

public class BlockStateGen extends BlockStateProvider {

    private static final String MOD_ID = BlockActions.MOD_ID;

    public BlockStateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, BlockActions.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(BlockRegister.BREAKER.get(), new ModelFile.UncheckedModelFile(new ResourceLocation(MOD_ID, "block/breaker")));
        horizontalBlock(BlockRegister.PLACER.get(), new ModelFile.UncheckedModelFile(new ResourceLocation(MOD_ID, "block/placer")));
        horizontalBlock(BlockRegister.HIT.get(), new ModelFile.UncheckedModelFile(new ResourceLocation(MOD_ID, "block/hit")));
    }
}
