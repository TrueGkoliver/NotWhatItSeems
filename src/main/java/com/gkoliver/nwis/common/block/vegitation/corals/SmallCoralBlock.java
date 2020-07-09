package com.gkoliver.nwis.common.block.vegitation.corals;

import java.util.ArrayList;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.fml.RegistryObject;

public class SmallCoralBlock extends Block implements IWaterLoggable {
	private static final VoxelShape SHAPE_FAN = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
	protected static final VoxelShape SHAPE_SMALL = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);
	protected static final VoxelShape SHAPE_SHOWER = Block.makeCuboidShape(2.0D, 1.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private ECoralType type;
	private CoralWallFanBlock wallFan;
	public SmallCoralBlock(Properties properties, ECoralType type, CoralWallFanBlock wallFan) {
		super(properties);
		this.type = type;
		this.wallFan = wallFan;
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (type==ECoralType.SMALL) {
			return SHAPE_SMALL;
		} 
		else if (type==ECoralType.SHOWER) {
			return SHAPE_SHOWER;
		}
		else {
			return SHAPE_FAN;
		}
	}
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (type==ECoralType.FAN) {
			boolean isGoodFace = !(context.getFace() == Direction.UP || context.getFace() == Direction.DOWN);
			if (isGoodFace) {
				return wallFan.getDefaultState().with(CoralWallFanBlock.FACING, context.getFace());
			}
		}
		return this.getDefaultState();
	}
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
	}
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

}
