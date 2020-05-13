package com.gkoliver.nwis.common.block.vegitation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class NWISBambooBlock extends Block {
	   public static final EnumProperty<BambooLeaves> BAMBOO_LEAVES = BlockStateProperties.BAMBOO_LEAVES;
	public NWISBambooBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(BAMBOO_LEAVES, BambooLeaves.NONE));
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(BAMBOO_LEAVES);
	}
	protected static final VoxelShape SHAPE_NORMAL = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_LARGE_LEAVES = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	protected static final VoxelShape SHAPE_COLLISION = Block.makeCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isShiftKeyDown()) {
			BambooLeaves newer = BambooLeaves.NONE;
			if (state.get(BAMBOO_LEAVES)==BambooLeaves.NONE) {
				newer = BambooLeaves.SMALL;
			} else if (state.get(BAMBOO_LEAVES)==BambooLeaves.SMALL) {
				newer = BambooLeaves.LARGE;
			}
			else {
				newer = BambooLeaves.NONE;
			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      VoxelShape voxelshape = state.get(BAMBOO_LEAVES) == BambooLeaves.LARGE ? SHAPE_LARGE_LEAVES : SHAPE_NORMAL;
	      Vec3d vec3d = state.getOffset(worldIn, pos);
	      return voxelshape.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
}