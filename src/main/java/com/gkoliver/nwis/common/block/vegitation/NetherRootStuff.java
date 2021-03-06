package com.gkoliver.nwis.common.block.vegitation;

import com.gkoliver.nwis.common.block.vegitation.vanilla.EOrientables;
import com.gkoliver.nwis.common.block.vegitation.vanilla.OrientableVeggies;

import com.gkoliver.nwis.core.register.NWISBlocks;
import com.gkoliver.nwis.core.util.SharedFunctions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
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

import java.util.HashMap;

public class NetherRootStuff extends OrientableVeggies {
	public static final BooleanProperty AGE = BooleanProperty.create("age");
	protected static final HashMap<Direction, VoxelShape> SHAPE_LANTERN = SharedFunctions.makeShapeList(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D);
	public NetherRootStuff(EOrientables type, Properties properties) {
		super(type, properties);
		this.setDefaultState(this.getDefaultState().with(AGE, false));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(AGE);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (player.isCrouching()) {
			worldIn.setBlockState(pos, state.with(AGE, !state.get(AGE)));
			//NotWhatItSeems.Triggers.CROP_CHANGES.trigger((ServerPlayerEntity)player);
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
	}
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (this== NWISBlocks.FAKE_LANTERN.get()||this== NWISBlocks.FAKE_SOUL_LANTERN.get()) {
			return SHAPE_LANTERN.get(state.get(FACING));
		}
		return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	}

}
