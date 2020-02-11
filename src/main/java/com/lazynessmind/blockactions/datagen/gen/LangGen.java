package com.lazynessmind.blockactions.datagen.gen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lazynessmind.blockactions.BlockActions;
import com.lazynessmind.blockactions.utils.Log;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class LangGen extends LanguageProvider {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<String, String> data = new TreeMap<>();

    public String locale;
    public DataGenerator gen;

    public LangGen(DataGenerator gen, String locale) {
        super(gen, BlockActions.MOD_ID, locale);
        this.locale = locale;
        this.gen = gen;
    }

    @Override
    protected void addTranslations() {
        if (locale.equals("pt_br")) {
            portuguese();
        } else if (locale.equals("en_us")) {
            english();
        }
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        addTranslations();
        if (!data.isEmpty())
            savethis(cache, data, this.gen.getOutputFolder().resolve("assets/" + BlockActions.MOD_ID + "/lang/" + locale + ".json"));
    }

    private void savethis(DirectoryCache cache, Object object, Path target) throws IOException {
        String data = GSON.toJson(object).replace("\u00C2", ""); //Fix the \u00C2 before §
        Log.l(1, data);
        data = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(data); // Escape unicode after the fact so that it's not double escaped by GSON
        String hash = IDataProvider.HASH_FUNCTION.hashUnencodedChars(data).toString();
        if (!Objects.equals(cache.getPreviousHash(target), hash) || !Files.exists(target)) {
            Files.createDirectories(target.getParent());

            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(target)) {
                bufferedwriter.write(new String(data.getBytes(StandardCharsets.UTF_8)));
            }
        }

        cache.recordHash(target, hash);
    }

    private void portuguese() {
        //Names Blocks
        addTrans("block.blockactions.breaker", "§6Quebrador");
        addTrans("block.blockactions.placer", "§6Colocador");
        addTrans("block.blockactions.hit", "§6Atacante");
        //Names Items
        addTrans("item.blockactions.speed_upgrade", "§6Upgrade de Velocidade");
        addTrans("item.blockactions.attack_upgrade", "§6Upgrade de Dano");
        //Names tooltips
        addTrans("tooltip.breaker.action", "§2§lFunção: §rQuebrar blocos");
        addTrans("tooltip.breaker.tip", "§2§lDica: §rO bloco destruído será transferido para um inventário acima se disponível.");
        addTrans("tooltip.placer.action", "§2§lFunção: §rColocar blocos");
        addTrans("tooltip.hit.action", "§2§lFunção: §rAtacar entidades");
        addTrans("tooltip.hit.tip", "§2§lDica: §rColoca um Item Frame na frente com uma arma para aumentar o dano.");
        addTrans("tooltip.speedupgrade.canapply", "§2§lPode aplicar em: §rTodos.");
        addTrans("tooltip.speedupgrade.info", "§2§lInfo: §rQuando aplicado reduz o tempo de cooldown em metade.");
        addTrans("tooltip.attackupgrade.canapply", "§2§lPode aplicar em: §rAtacador.");
        addTrans("tooltip.attackupgrade.info", "§2§lInfo: §rQuando aplicado aumenta o dano base em 1.");
        //Info Overlay
        addTrans("infooverlay.damage", "§3§lDano: §r");
        addTrans("infooverlay.working", "§3§lFuncionando: §r");
        addTrans("infooverlay.cooldown", "§3§lCooldown: §r");
        addTrans("infooverlay.upgrades", "§3§lUpgrades: §r");
        addTrans("infooverlay.worktime", "§3§lTempo de trabalho: §r");
        addTrans("infooverlay.sneak", "Agachar para ver info!");
        addTrans("infooverlay.placer.needchest", "Precisas de um inventário com blocos por cima.");
        addTrans("infooverlay.breaker.canbreakinv", "Não pode quebrar blocos com um inventário");
        addTrans("infooverlay.breaker.canbreakte", "Não pode quebrar Tile Entities");
    }

    private void english() {
        //Names blocks
        addTrans("block.blockactions.breaker", "§6Breaker");
        addTrans("block.blockactions.placer", "§6Placer");
        addTrans("block.blockactions.hit", "§6Hit");
        //Names items
        addTrans("item.blockactions.speed_upgrade", "§6Speed Upgrade");
        addTrans("item.blockactions.attack_upgrade", "§6Attack Upgrade");
        //tooltips
        addTrans("tooltip.breaker.action", "§2§lAction: §rBreak blocks");
        addTrans("tooltip.breaker.tip", "§2§lTip: §rThe breaker will automatically transfer the destroyed block if has an inventory above.");
        addTrans("tooltip.placer.action", "§2§lAction: §rPlace blocks");
        addTrans("tooltip.hit.action", "§2§lAction: §rAttack entities");
        addTrans("tooltip.hit.tip.one", "§2§lTip #1: §rPut an Item Frame with a weapon to increase the damage.");
        addTrans("tooltip.hit.tip.two", "§2§lTip #2: §rEnchant the weapon with Fire Aspect to get cooked food.");
        addTrans("tooltip.speedupgrade.canapply", "§2§lCan apply on: §rAll.");
        addTrans("tooltip.speedupgrade.info", "§2§lInfo: §rOn applied cuts the current cooldown on half.");
        addTrans("tooltip.attackupgrade.canapply", "§2§lCan apply on: §rHit.");
        addTrans("tooltip.attackupgrade.info", "§2§lInfo: §rOn applied increases the base attack by 1.");
        //info overlay
        addTrans("infooverlay.damage", "§3§lDamage: §r");
        addTrans("infooverlay.working", "§3§lWorking: §r");
        addTrans("infooverlay.cooldown", "§3§lCooldown: §r");
        addTrans("infooverlay.upgrades", "§3§lUpgrades: §r");
        addTrans("infooverlay.worktime", "§3§lWork time: §r");
        addTrans("infooverlay.sneak", "Sneak to see the info!");
        addTrans("infooverlay.placer.needchest", "The placer needs an inventory above with blocks.");
        addTrans("infooverlay.breaker.canbreakinv", "Can't break blocks with inventories");
        addTrans("infooverlay.breaker.canbreakte", "Can't break blocks with tile entities");
    }

    public void addTrans(String key, String trans) {
        if (data.put(key, trans) != null)
            throw new IllegalStateException("Duplicate translation key " + key);
    }
}
