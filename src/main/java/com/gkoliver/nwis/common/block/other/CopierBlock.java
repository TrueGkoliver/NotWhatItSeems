package com.gkoliver.nwis.common.block.other;

import com.gkoliver.nwis.common.gui.CopierContainer;
import com.gkoliver.nwis.core.config.NWISConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CopierBlock extends HorizontalBlock {
	private static final ITextComponent NAME = new TranslationTextComponent("container.copier");
	public CopierBlock(Properties builder) {
		super(builder);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}


	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (worldIn.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	    	 CopierContainer.isDragonEgg = NWISConfig.CONFIG.DRAGON_EGG.get();
	    	 NetworkHooks.openGui((ServerPlayerEntity)player, state.getContainer(worldIn, pos));
	         return ActionResultType.SUCCESS;
	      }
	}
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
	      return new SimpleNamedContainerProvider((p_220270_2_, p_220270_3_, p_220270_4_) -> {
	         return new CopierContainer(p_220270_2_, p_220270_3_, IWorldPosCallable.of(worldIn, pos));
	      }, NAME);
	}

}
