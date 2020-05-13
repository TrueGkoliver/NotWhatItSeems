package com.gkoliver.nwis.common.block.vegitation;

import java.util.Map;

import com.gkoliver.nwis.core.keybind.InverseKeybind;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.Block.Properties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class NewChorusPlantBlock extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	      builder.add(WATERLOGGED);
	}
	protected final VoxelShape[] shapes;
	
	public NewChorusPlantBlock(Properties properties) {
		super(properties);
		this.shapes = this.makeShapes();
		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, false).with(EAST, false).with(WEST, false).with(SOUTH, false).with(UP, false).with(DOWN, false).with(WATERLOGGED, false));
	}
	private static final Direction[] FACING_VALUES = Direction.values();
	   public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	   public static final BooleanProperty EAST = BlockStateProperties.EAST;
	   public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	   public static final BooleanProperty WEST = BlockStateProperties.WEST;
	   public static final BooleanProperty UP = BlockStateProperties.UP;
	   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	   public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p_203421_0_) -> {
	      p_203421_0_.put(Direction.NORTH, NORTH);
	      p_203421_0_.put(Direction.EAST, EAST);
	      p_203421_0_.put(Direction.SOUTH, SOUTH);
	      p_203421_0_.put(Direction.WEST, WEST);
	      p_203421_0_.put(Direction.UP, UP);
	      p_203421_0_.put(Direction.DOWN, DOWN);
	   });
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult result) {
		if (InverseKeybind.KEYBIND_BOTHSIDES.isKeyDown()) {
			BooleanProperty ste1 = FACING_TO_PROPERTY_MAP.get(result.getFace());
			BooleanProperty ste2 = FACING_TO_PROPERTY_MAP.get(result.getFace().getOpposite());
			worldIn.setBlockState(pos, state.with(ste1, !state.get(ste1)));
			BlockState newState = worldIn.getBlockState(pos);
			worldIn.setBlockState(pos, newState.with(ste2, !state.get(ste2)));
			return ActionResultType.SUCCESS;
		}
		if (InverseKeybind.KEYBIND_INVERSE.isKeyDown()) {
			BooleanProperty ste = FACING_TO_PROPERTY_MAP.get(result.getFace().getOpposite());
			worldIn.setBlockState(pos, state.with(ste, !state.get(ste)));
			return ActionResultType.SUCCESS;
		}
		if (player.isShiftKeyDown()) {
			BooleanProperty ste = FACING_TO_PROPERTY_MAP.get(result.getFace());
			worldIn.setBlockState(pos, state.with(ste, !state.get(ste)));
			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, result);
		}
		
	}
	private VoxelShape[] makeShapes() {
		float apothem = (float) 0.315;
		float f = 0.5F - apothem;
		float f1 = 0.5F + apothem;
		VoxelShape voxelshape = Block.makeCuboidShape((double)(f * 16.0F), (double)(f * 16.0F), (double)(f * 16.0F), (double)(f1 * 16.0F), (double)(f1 * 16.0F), (double)(f1 * 16.0F));
		VoxelShape[] avoxelshape = new VoxelShape[FACING_VALUES.length];
		
		for(int i = 0; i < FACING_VALUES.length; ++i) {
		   Direction direction = FACING_VALUES[i];
		   avoxelshape[i] = VoxelShapes.create(0.5D + Math.min((double)(-apothem), (double)direction.getXOffset() * 0.5D), 0.5D + Math.min((double)(-apothem), (double)direction.getYOffset() * 0.5D), 0.5D + Math.min((double)(-apothem), (double)direction.getZOffset() * 0.5D), 0.5D + Math.max((double)apothem, (double)direction.getXOffset() * 0.5D), 0.5D + Math.max((double)apothem, (double)direction.getYOffset() * 0.5D), 0.5D + Math.max((double)apothem, (double)direction.getZOffset() * 0.5D));
		}
		
		VoxelShape[] avoxelshape1 = new VoxelShape[64];
		
		for(int k = 0; k < 64; ++k) {
		   VoxelShape voxelshape1 = voxelshape;
		
		   for(int j = 0; j < FACING_VALUES.length; ++j) {
		      if ((k & 1 << j) != 0) {
		         voxelshape1 = VoxelShapes.or(voxelshape1, avoxelshape[j]);
		      }
		   }
		
		   avoxelshape1[k] = voxelshape1;
		}

		return avoxelshape1;
	 }
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.shapes[this.getShapeIndex(state)];
	}
	
	protected int getShapeIndex(BlockState state) {
	      int i = 0;

	      for(int j = 0; j < FACING_VALUES.length; ++j) {
	         if (state.get(FACING_TO_PROPERTY_MAP.get(FACING_VALUES[j]))) {
	            i |= 1 << j;
	         }
	      }

	      return i;
	   }
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
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
