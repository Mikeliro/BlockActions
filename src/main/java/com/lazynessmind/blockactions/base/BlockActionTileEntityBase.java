package com.lazynessmind.blockactions.base;

import com.lazynessmind.blockactions.Configs;
import com.lazynessmind.blockactions.utils.IInfo;
import com.lazynessmind.blockactions.utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class BlockActionTileEntityBase extends TileEntity implements ITickableTileEntity, IInfo {

    private static final String TAG_WORKTIME = "WorkTime";
    public int workTime = -1;

    private static final String TAG_COOLDOWN = "CoolDown";
    private int coolDown = Configs.DEFAULT_COOLDOWN.get();

    private static final String TAG_UPGRADE_COUNT = "CurrentUpgradeCount";
    public int currentUpgrades = 0;

    private static final String TAG_UPGRADES = "Upgrades";
    private NonNullList<ItemStack> upgradeItems = NonNullList.create();

    public BlockActionTileEntityBase(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.workTime = compound.getInt(TAG_WORKTIME);
        this.coolDown = compound.getInt(TAG_COOLDOWN);
        this.currentUpgrades = compound.getInt(TAG_UPGRADE_COUNT);
        this.upgradeItems = Utils.loadListNbt(TAG_UPGRADES, compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        nbt.putInt(TAG_WORKTIME, this.workTime);
        nbt.putInt(TAG_COOLDOWN, this.coolDown);
        nbt.putInt(TAG_UPGRADE_COUNT, this.currentUpgrades);
        Utils.saveListToNbt(TAG_UPGRADES, nbt, this.upgradeItems);
        return nbt;
    }

    public boolean canWork() {
        return this.workTime <= 0;
    }

    public int getWorkTime() {
        return this.workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
        this.markDirty();
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
        this.markDirty();
    }

    public int getCoolDown() {
        return this.coolDown;
    }

    public int getCurrentUpgrades() {
        return currentUpgrades;
    }

    public NonNullList<ItemStack> getUpgradeItems() {
        return this.upgradeItems;
    }

    @Override
    public CompoundNBT getLines() {
        CompoundNBT lines = new CompoundNBT();
        lines.putString("cooldown", new TranslationTextComponent("infooverlay.cooldown").appendText(Utils.convertTickSec(this.getCoolDown())).getFormattedText());
        lines.putString("currentUpgrades", new TranslationTextComponent("infooverlay.upgrades").appendText(this.getCurrentUpgrades() + "/" + Configs.MAX_UPGRADE_COUNT.get()).getFormattedText());
        String onOff;
        if (!this.world.isBlockPowered(this.pos)) {
            onOff = TextFormatting.GREEN + "ON";
        } else {
            onOff = TextFormatting.RED + "OFF";
        }
        lines.putString("working", new TranslationTextComponent("infooverlay.working").appendText(onOff).getFormattedText());
        if (this.getWorkTime() >= 0) {
            lines.putString("workTime", new TranslationTextComponent("infooverlay.worktime").appendText("" + this.getWorkTime()).getFormattedText());
        }
        return lines;
    }
}
