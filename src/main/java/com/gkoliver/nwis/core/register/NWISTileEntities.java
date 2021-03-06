package com.gkoliver.nwis.core.register;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.common.gui.ImposterContainer;
import com.gkoliver.nwis.common.tile.VoidTileEntity;
import com.gkoliver.nwis.common.tile.RestrainedVoidTileEntity;
import com.google.common.collect.Sets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.ContainerType.IFactory;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NWISTileEntities {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NotWhatItSeems.MODID);
	public static final RegistryObject<TileEntityType<VoidTileEntity>> VOID_BLOCK = TILE_ENTITIES.register("tile_void", () -> new TileEntityType<>(VoidTileEntity::new, Sets.newHashSet(NWISBlocks.VOID_BLOCK.get(), NWISBlocks.VOID_BLOCK_SEMISOLID.get()), null));
	public static final RegistryObject<TileEntityType<RestrainedVoidTileEntity>> RESTAINED_VOID_BLOCK = TILE_ENTITIES.register("tile_restrained_void", () -> new TileEntityType<>(RestrainedVoidTileEntity::new, Sets.newHashSet(NWISBlocks.RESTRAINED_VOID_BLOCK.get(), NWISBlocks.RESTRAINED_VOID_BLOCK_SEMI.get()), null));
	//public static final RegistryObject<TileEntityType<VoidTileEntity>> VOID_BLOCK_SEMI = TILE_ENTITIES.register("tile_void_2", () -> new TileEntityType<>(VoidTileEntity::new, Sets.newHashSet(BlockRegistry.VOID_BLOCK_SEMISOLID.get()), null));
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NotWhatItSeems.MODID);
	
	
	static IFactory<ImposterContainer> i = new ContainerType.IFactory<ImposterContainer>() {

		@Override
		public ImposterContainer create(int id, PlayerInventory inv) {
			return new ImposterContainer(id, inv, IWorldPosCallable.DUMMY);
		}
	};
	
	public static final RegistryObject<ContainerType<ImposterContainer>> COPIER = CONTAINERS.register("co_copier", ()->new ContainerType<>(i));
}
