package com.gkoliver.nwis.common.block.other;

import java.util.Map;

import com.gkoliver.nwis.core.keybind.InverseKeybind;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class NWISNorthableBlock extends Block {

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}
	
	
	public NWISNorthableBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, false).with(EAST, false).with(WEST, false).with(SOUTH, false).with(UP, false).with(DOWN, false));
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

}
