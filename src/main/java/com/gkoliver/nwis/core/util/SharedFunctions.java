package com.gkoliver.nwis.core.util;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class SharedFunctions {
	public static VoxelShape getShape(double x1, double y1, double z1, double x2, double y2, double z2) {
		VoxelShape Voxel_UP = VoxelShapes.create(x1 / 16.0D, y1 / 16.0D, z1 / 16.0D, x2 / 16.0D, y2 / 16.0D, z2 / 16.0D);
		VoxelShape Voxel_DOWN = VoxelShapes.create(x1 / 16.0D, (y1+16.0D / 16.0D), z1 / 16.0D, x2 / 16.0D, (16.0D-y2 / 16.0D), z2 / 16.0D);
		VoxelShape Voxel_NORTH;
		VoxelShape Voxel_SOUTH;
		VoxelShape Voxel_EAST;
		VoxelShape Voxel_WEST;
		return null;
	}

}
