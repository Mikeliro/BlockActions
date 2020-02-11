package com.lazynessmind.blockactions.datagen;

import com.lazynessmind.blockactions.datagen.gen.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Generators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new RecipesGen(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new LangGen(generator, "pt_br"));
            generator.addProvider(new LangGen(generator, "en_us"));

            generator.addProvider(new BlockModelGen(generator, event.getExistingFileHelper()));
            generator.addProvider(new BlockStateGen(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModelGen(generator, event.getExistingFileHelper()));
        }
    }
}
