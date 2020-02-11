package com.lazynessmind.blockactions.actions.hitaction;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class HitUtils {

    public static ItemStack getItemInFrame(World world, BlockPos facingPos) {
        List<ItemFrameEntity> entity = world.getEntitiesWithinAABB(ItemFrameEntity.class, new AxisAlignedBB(facingPos));
        return entity.isEmpty() ? ItemStack.EMPTY : entity.get(0).getDisplayedItem();
    }

    public static IAttributeInstance getDamage(float baseValue, World world, BlockPos facingPos) {
        ItemStack stack = getItemInFrame(world, facingPos);
        IAttributeInstance damageAttr = new AttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        damageAttr.setBaseValue(baseValue);
        if (!stack.equals(ItemStack.EMPTY)) {
            for (AttributeModifier attributeModifier : stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
                damageAttr.applyModifier(attributeModifier);
            }
        }
        return damageAttr;
    }

    public static boolean causeFireDamage(World world, BlockPos facingPos) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, getItemInFrame(world, facingPos)) > 0;
    }
}
