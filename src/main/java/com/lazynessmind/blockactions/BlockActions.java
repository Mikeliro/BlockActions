package com.lazynessmind.blockactions;

import com.lazynessmind.blockactions.proxy.ClientProxy;
import com.lazynessmind.blockactions.proxy.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(BlockActions.MOD_ID)
public class BlockActions {

    public static final String MOD_ID = "blockactions";

    public static final Logger LOGGER = LogManager.getLogger();

    public static CommonProxy proxy;

    public BlockActions() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configs.COMMON_CONFIG);

        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.init();

        Configs.load(Configs.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("blockactions-common.toml"));
    }
}
