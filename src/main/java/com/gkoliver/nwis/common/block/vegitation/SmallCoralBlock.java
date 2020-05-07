package com.gkoliver.nwis.common.block.vegitation;

import java.util.ArrayList;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;

public class SmallCoralBlock extends Block implements IWaterLoggable {
	private static final VoxelShape SHAPE_FAN = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
	protected static final VoxelShape SHAPE_SMALL = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 15.0D, 14.0D);
	private ECoralType type;
	private CoralWallFanBlock wallFan;
	public SmallCoralBlock(Properties properties, ECoralType type, CoralWallFanBlock wallFan) {
		super(properties);
		this.type = type;
		this.wallFan = wallFan;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (type==ECoralType.SMALL) {
			return SHAPE_SMALL;
		} else {
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

}
