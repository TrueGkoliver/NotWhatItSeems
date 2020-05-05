package com.gkoliver.nwis.common.block.crop;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FakeBeetrootBlock extends FakeGrowableBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
	public FakeBeetrootBlock(Properties properties, int maxStages) {
		super(properties, maxStages, ECropTypes.BEETROOT);
	}

	private static final VoxelShape[] BEETROOT_SHAPES = new VoxelShape[]{
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), 
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), 
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), 
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
		};
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return BEETROOT_SHAPES[state.get(AGE)];
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			int i = state.get(AGE);
			if (i==3) {
				worldIn.setBlockState(pos, state.with(AGE, 0));
			} else {
				worldIn.setBlockState(pos, state.with(AGE, i+1));
			}
		}
		return ActionResultType.SUCCESS;
	}

}
