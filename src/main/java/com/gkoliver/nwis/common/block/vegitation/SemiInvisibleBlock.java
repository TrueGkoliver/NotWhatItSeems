package com.gkoliver.nwis.common.block.vegitation;

import java.util.Random;

import com.gkoliver.nwis.common.block.other.NWISNorthableBlock;
import com.gkoliver.nwis.common.block.other.RestrainedDillutedPortalBlock;
import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.register.Triggers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SemiInvisibleBlock extends Block {

	public SemiInvisibleBlock(Properties properties) {
		super(properties);
	}
	@OnlyIn(value=Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
		return super.isSideInvisible(bs1, bs2, side) || bs2.getBlock() == this ;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (worldIn.isRemote()) {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
		}
		if (!(player.getHeldItem(handIn).getItem() instanceof ShearsItem)) {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
		}
		Triggers.RESTRAIN_VOID.trigger((ServerPlayerEntity)player);
		ItemStack itemstack = player.getHeldItem(handIn);
		if (!player.isCreative()) {
			itemstack.attemptDamageItem(1, new Random(), (ServerPlayerEntity)player);
		}
		Axis axis = player.getHorizontalFacing().getAxis();
		if (axis==Axis.X) {
			axis = Axis.Z;
		} else {
			axis = Axis.X;
		}
		if (this == BlockRegistry.DILLUTED_VOID_BLOCK.get()) {
			BlockState stato = BlockRegistry.RESTRAINED_DILLUTED_VOID_BLOCK.get().getDefaultState().with(RestrainedDillutedPortalBlock.AXIS, axis);
			worldIn.setBlockState(pos, stato);
			return ActionResultType.SUCCESS;
		} else if (this == BlockRegistry.DILLUTED_VOID_BLOCK_SEMISOLID.get()) {
			BlockState stato = BlockRegistry.RESTRAINED_DILLUTED_VOID_BLOCK_SEMI.get().getDefaultState().with(RestrainedDillutedPortalBlock.AXIS, axis);
			worldIn.setBlockState(pos, stato);
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}

}
