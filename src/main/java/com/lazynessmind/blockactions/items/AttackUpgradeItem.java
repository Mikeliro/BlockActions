package com.lazynessmind.blockactions.items;

import com.lazynessmind.blockactions.actions.hitaction.HitTileEntity;
import com.lazynessmind.blockactions.base.BlockActionTileEntityBase;
import com.lazynessmind.blockactions.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AttackUpgradeItem extends UpgradeItem {

    @Override
    public ApplyState applyUpgrade(BlockActionTileEntityBase tileEntityBase) {
        if (tileEntityBase instanceof HitTileEntity) {
            HitTileEntity hitTileEntity = (HitTileEntity) tileEntityBase;
            hitTileEntity.baseDamage++;
            return new ApplyState(Utils.newStack(this), true);
        }
        return ApplyState.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.attackupgrade.canapply"));
        tooltip.add(new TranslationTextComponent("tooltip.attackupgrade.info"));
    }
}
