package com.gkoliver.nwis.common.block.crop;

import java.util.Map;

import com.gkoliver.nwis.core.register.BlockRegistry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.Block.Properties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FakeAttachedBlock extends Block {
	ECropTypes type;
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.SOUTH, Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 16.0D), Direction.WEST, Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D), Direction.NORTH, Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D), Direction.EAST, Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 16.0D, 10.0D, 10.0D)));
	
	public FakeAttachedBlock(Properties properties, ECropTypes e) {
		super(properties);
		this.type = e;
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	    builder.add(FACING);
	}
	public static BlockState getWithDirection(BlockState stateIn, Direction directionIn) {
		return stateIn.with(FACING, directionIn);
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(state.get(FACING));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	    return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			if (type==ECropTypes.MELON_STEM) {
				worldIn.setBlockState(pos, BlockRegistry.FAKE_MELON_STEM.get().getDefaultState());
			}
			else {
				worldIn.setBlockState(pos, BlockRegistry.FAKE_PUMPKIN_STEM.get().getDefaultState());
			}
			
		}
		return ActionResultType.SUCCESS;
	}

}
