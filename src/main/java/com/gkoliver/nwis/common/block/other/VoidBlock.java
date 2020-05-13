package com.gkoliver.nwis.common.block.other;

import java.util.Random;

import com.gkoliver.nwis.common.tile.VoidTileEntity;
import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class VoidBlock extends ContainerBlock {

	public VoidBlock(Properties builder) {
		super(builder);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new VoidTileEntity();
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
		ItemStack itemstack = player.getHeldItem(handIn);
		if (!player.isCreative()) {
			itemstack.attemptDamageItem(1, new Random(), (ServerPlayerEntity)player);
		}
		if (this==BlockRegistry.VOID_BLOCK.get()) {
			BlockState stato = BlockRegistry.RESTRAINED_VOID_BLOCK.get().getDefaultState();
			worldIn.setBlockState(pos, stato);
			return ActionResultType.SUCCESS;
		}
		else if (this==BlockRegistry.VOID_BLOCK_SEMISOLID.get()) {
			BlockState stato = BlockRegistry.RESTRAINED_VOID_BLOCK_SEMI.get().getDefaultState();
			worldIn.setBlockState(pos, stato);
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}
	

}
