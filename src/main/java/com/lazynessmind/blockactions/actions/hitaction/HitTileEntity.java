package com.lazynessmind.blockactions.actions.hitaction;

import com.lazynessmind.blockactions.actions.placeaction.PlacerBlock;
import com.lazynessmind.blockactions.base.BlockActionTileEntityBase;
import com.lazynessmind.blockactions.event.TileEntityRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

public class HitTileEntity extends BlockActionTileEntityBase {

    private static final String TAG_DAMAGE = "Damage";
    public float baseDamage = 1f;

    private Direction direction;

    public HitTileEntity() {
        super(TileEntityRegister.HIT_TILE.get());
    }

    @Override
    public void tick() {
        if (this.world != null) {
            if (!this.world.isRemote) {

                if (this.getBlockState().has(PlacerBlock.FACING)) {
                    this.direction = this.getBlockState().get(PlacerBlock.FACING);
                }

                if (!this.world.isBlockPowered(this.pos)) {
                    this.workTime--;
                    this.markDirty();

                    if (this.canWork()) {
                        if (this.getFacing() != null) {
                            BlockPos posAtFacing = this.pos.offset(this.getFacing());
                            AxisAlignedBB area = new AxisAlignedBB(posAtFacing);

                            for (LivingEntity livingEntity : this.world.getEntitiesWithinAABB(LivingEntity.class, area)) {
                                float dmg = (float) HitUtils.getDamage(this.baseDamage, this.world, posAtFacing).getValue();
                                if (HitUtils.causeFireDamage(world, posAtFacing)) {
                                    livingEntity.attackEntityFrom(DamageSource.ON_FIRE, dmg);
                                    livingEntity.setFire(3);
                                } else {
                                    livingEntity.attackEntityFrom(DamageSource.GENERIC, dmg);
                                }
                            }

                            this.setWorkTime(this.getCoolDown());
                            this.markDirty();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.baseDamage = compound.getFloat(TAG_DAMAGE);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        nbt.putFloat(TAG_DAMAGE, this.baseDamage);
        return nbt;
    }

    public Direction getFacing() {
        return this.direction;
    }

    @Override
    public CompoundNBT getLines() {
        CompoundNBT nbt = super.getLines();
        nbt.putString("damage", new TranslationTextComponent("infooverlay.damage").appendText("" + HitUtils.getDamage(this.baseDamage, this.world, this.pos.offset(this.getFacing())).getValue()).getFormattedText());
        return nbt;
    }
}
