package com.gkoliver.nwis.common.block.vegitation;

import com.gkoliver.nwis.NotWhatItSeems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
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
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
	public NWISBambooBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(BAMBOO_LEAVES, BambooLeaves.NONE));
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(BAMBOO_LEAVES);
		builder.add(AGE);
	}
	protected static final VoxelShape SHAPE_NORMAL = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_LARGE_LEAVES = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	protected static final VoxelShape SHAPE_COLLISION = Block.makeCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (!worldIn.isRemote()) {
			NotWhatItSeems.Triggers.CROP_CHANGES.trigger((ServerPlayerEntity)player);
		}
		if (player.isShiftKeyDown() && state.get(AGE)!=2) {
			BambooLeaves newer = BambooLeaves.NONE;
			BlockState newState = state;
			if (state.get(BAMBOO_LEAVES)==BambooLeaves.NONE) {
				newer = BambooLeaves.SMALL;
			} else if (state.get(BAMBOO_LEAVES)==BambooLeaves.SMALL) {
				newer = BambooLeaves.LARGE;
			}
			else {
				newer = BambooLeaves.NONE;
			}
			newState = newState.with(BAMBOO_LEAVES, newer);
			worldIn.setBlockState(pos, newState);
			return ActionResultType.SUCCESS;
		} else {
			if (state.get(AGE)==2) {
				worldIn.setBlockState(pos, state.with(AGE, 0));
			} else {
				worldIn.setBlockState(pos, state.with(AGE, state.get(AGE)+1));
			}
			return ActionResultType.SUCCESS;
		}
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      VoxelShape voxelshape = state.get(BAMBOO_LEAVES) == BambooLeaves.LARGE ? SHAPE_LARGE_LEAVES : SHAPE_NORMAL;
	      Vec3d vec3d = state.getOffset(worldIn, pos);
	      return voxelshape.withOffset(vec3d.x, vec3d.y, vec3d.z);
	}
}