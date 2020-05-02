package com.gkoliver.nwis.core.register;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.common.tile.VoidTileEntity;
import com.google.common.collect.Sets;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, NotWhatItSeems.MODID);
	public static final RegistryObject<TileEntityType<VoidTileEntity>> VOID_BLOCK = TILE_ENTITIES.register("tile_void", () -> new TileEntityType<>(VoidTileEntity::new, Sets.newHashSet(BlockRegistry.VOID_BLOCK.get()), null));

}
