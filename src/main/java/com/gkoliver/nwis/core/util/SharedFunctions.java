package com.gkoliver.nwis.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.advancements.Advancement;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class SharedFunctions {
	public static double inv(double i, int direction) {
		return 0.0D;
	}
	public static HashMap<Direction, VoxelShape> makeShapeList(double x1, double y1, double z1, double x2, double y2, double z2) {
		VoxelShape Voxel_UP = Block.makeCuboidShape(x1, y1, z1, x2, y2, z2);
		VoxelShape Voxel_DOWN = Block.makeCuboidShape(x1, y1+16.0, z1, x2, 16.0-y2, z2);
		//Z Axis: Use X for Y
		VoxelShape Voxel_NORTH = Block.makeCuboidShape(y1, x1, z1, y2, x2, z2);
		VoxelShape Voxel_SOUTH = Block.makeCuboidShape(y1+16.0, x1, z1, 16.0-y2, x2, z2);
		//X Axis: Use Z for Y
		VoxelShape Voxel_WEST = Block.makeCuboidShape(x1, z1, y1, x2, z2, y2);
		VoxelShape Voxel_EAST = Block.makeCuboidShape(x1, z1, y1+16.0, x2, z2, 16.0-y2);
		HashMap<Direction, VoxelShape> tbr = new HashMap<Direction, VoxelShape>();
		System.out.println("cHECKING THROUGH");
		tbr.put(Direction.UP, Voxel_UP);
		tbr.put(Direction.DOWN, Voxel_DOWN);
		
		tbr.put(Direction.EAST, Voxel_NORTH);
		tbr.put(Direction.WEST, Voxel_SOUTH);
		
		tbr.put(Direction.NORTH, Voxel_EAST);
		tbr.put(Direction.SOUTH, Voxel_WEST);
		return tbr;
	}

}
