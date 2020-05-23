package com.gkoliver.nwis.common.block.other;

import com.gkoliver.nwis.common.gui.ImposterContainer;
import com.gkoliver.nwis.core.config.NWISConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ImposterStationBlock extends Block {
	private static final ITextComponent NAME = new TranslationTextComponent("container.copier");

	public ImposterStationBlock(Properties builder) {
		super(builder);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (worldIn.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	    	 ImposterContainer.isDragonEgg = NWISConfig.CONFIG.DRAGON_EGG.get();
	    	 NetworkHooks.openGui((ServerPlayerEntity)player, state.getContainer(worldIn, pos));
	         return ActionResultType.SUCCESS;
	      }
	}
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
	      return new SimpleNamedContainerProvider((p_220270_2_, p_220270_3_, p_220270_4_) -> {
	         return new ImposterContainer(p_220270_2_, p_220270_3_, IWorldPosCallable.of(worldIn, pos));
	      }, NAME);
	}

}
