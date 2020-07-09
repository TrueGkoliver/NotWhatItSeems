package com.gkoliver.nwis.common.block.other;

import java.util.HashMap;

import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.util.SharedFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NWISOrientableBlock extends Block {
	protected static final HashMap<Direction, VoxelShape> SHAPE_DEFAULT = SharedFunctions.makeShapeList(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	protected static final HashMap<Direction, VoxelShape> SHAPE_CHAIN = SharedFunctions.makeShapeList(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	protected static final HashMap<Direction, VoxelShape> SHAPE_LANTERN = SharedFunctions.makeShapeList(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D);
	protected static final HashMap<Direction, VoxelShape> SHAPE_TORCH = SharedFunctions.makeShapeList(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

	public NWISOrientableBlock(Properties properties) {
		super(properties);
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace());
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		if (
				this==BlockRegistry.FAKE_LANTERN.get()
				||(this==BlockRegistry.FAKE_SOUL_LANTERN.get())
				||(this==BlockRegistry.FAKE_TORCH.get())
				||(this==BlockRegistry.FAKE_SOUL_TORCH.get())
				||(this==BlockRegistry.FAKE_CHAIN.get())
		) {
			return true;
		}
		return super.propagatesSkylightDown(state, reader, pos);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (this==BlockRegistry.STATIC_PATH.get()) { return SHAPE_DEFAULT.get(state.get(FACING)); }
		if (this==BlockRegistry.FAKE_TORCH.get()||this==BlockRegistry.FAKE_SOUL_TORCH.get()) {
			return SHAPE_TORCH.get(state.get(FACING));
		}
		if (this==BlockRegistry.FAKE_CHAIN.get()) {
			return SHAPE_CHAIN.get(state.get(FACING));
		}
		if (this==BlockRegistry.FAKE_LANTERN.get()||this==BlockRegistry.FAKE_SOUL_LANTERN.get()) {
			return SHAPE_LANTERN.get(state.get(FACING));
		}
		return super.getShape(state, worldIn, pos, context);
	}
}
