package com.gkoliver.nwis.common.block.vegitation.swex;

import com.gkoliver.nwis.core.register.BlockRegistry;
import com.google.common.util.concurrent.Service.State;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class DoubleDoubleCropBlock extends Block {
	public static BooleanProperty isDone = BooleanProperty.create("grown");
	public static EnumProperty<DoubleBlockHalf> blockHalf = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private EDoubleCropType type;
	public DoubleDoubleCropBlock(Properties properties, EDoubleCropType type) {
		super(properties);
		this.type = type;
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(isDone);
		builder.add(blockHalf);
	}
	public static void generateDoubleBlock(int type, BlockPos lower_pos, BlockState i, World worldIn) {
		if (worldIn.getBlockState(lower_pos.up()).getBlock() == Blocks.AIR) {
			if (type==0) {
				 BlockState newstate = BlockRegistry.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.LOWER);
				 BlockState newstate_up = BlockRegistry.CATTAIL_BIG.get().getDefaultState().with(blockHalf, DoubleBlockHalf.UPPER);
				 worldIn.setBlockState(lower_pos, newstate);
				 worldIn.setBlockState(lower_pos.up(), newstate_up);
			}
			else {
				
			}
		}
		
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (state.get(isDone)) {
			
		} else {
			worldIn.setBlockState(pos, state.with(isDone, !state.get(isDone));
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}

}
