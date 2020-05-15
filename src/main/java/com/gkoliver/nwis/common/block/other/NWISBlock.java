package com.gkoliver.nwis.common.block.other;

import java.util.Random;

import com.gkoliver.nwis.core.register.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;

public class NWISBlock extends Block {
	protected static final VoxelShape SAPLING_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	protected static final VoxelShape MUSHROOM_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	protected static final VoxelShape EDRAG_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	public boolean waterlog = false;
	public NWISBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState());
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean isSapling = (state.getBlock() == BlockRegistry.FAKE_OAK_SAPLING.get() || 
				state.getBlock() == BlockRegistry.FAKE_BIRCH_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_SPRUCE_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_JUNGLE_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_ACACIA_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FAKE_DARK_OAK_SAPLING.get() ||
				state.getBlock() == BlockRegistry.FROSTY_SAPLING.get() ||
				state.getBlock() == BlockRegistry.MAPLE_SAPLING.get() ||
				state.getBlock() == BlockRegistry.MAPLE_SAPLING_ORANGE.get() ||
				state.getBlock() == BlockRegistry.MAPLE_SAPLING_RED.get() ||
				state.getBlock() == BlockRegistry.ROSEWOOD_SAPLING.get() ||
				state.getBlock() == BlockRegistry.YUCCA_SAPLING.get() ||
				state.getBlock() == BlockRegistry.ASPEN_SAPLING.get() ||
				state.getBlock() == BlockRegistry.KOUSA_SAPLING.get() ||
				state.getBlock() == BlockRegistry.SERENE_SAPLING.get() || 
				state.getBlock() == BlockRegistry.SUNNY_SAPLING.get() ||
				state.getBlock() == BlockRegistry.SWEET_SAPLING.get() ||
				state.getBlock() == BlockRegistry.WARM_SAPLING.get() ||
				state.getBlock() == BlockRegistry.WISTERIA_BLUE.get() ||
				state.getBlock() == BlockRegistry.WISTERIA_PINK.get() ||
				state.getBlock() == BlockRegistry.WISTERIA_PURPLE.get() ||
				state.getBlock() == BlockRegistry.WISTERIA_WHITE.get());
		boolean isMushroom = (state.getBlock() == BlockRegistry.FAKE_MUSHOOM_1.get() ||
				state.getBlock() == BlockRegistry.FAKE_MUSHOOM_2.get() ||
				state.getBlock() == BlockRegistry.FAKE_GLOWSHROOM.get());
		if (isSapling) {
			return SAPLING_SHAPE;
		} else if (isMushroom) {
			return MUSHROOM_SHAPE;
		}
		return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	}
}
