package com.gkoliver.nwis.common.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.core.register.NWISBlocks;
import com.gkoliver.nwis.core.register.NWISTileEntities;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ImposterContainer extends Container {
	public static ArrayList<ArrayList<String>> vanilla_corals = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> ua_corals = new ArrayList<ArrayList<String>>();
	public static final HashMap<Item, Item> ITEMS = new HashMap<Item, Item>() {
		private static final long serialVersionUID = 1L;
	};
	public static void addModdedRecipe(String modid, String id, RegistryObject<Block> object) {
		ITEMS.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, id)), Item.getItemFromBlock(object.get()));
	}
	public static void putRecipe(Item item, RegistryObject<Block> object) {
		ITEMS.put(item, Item.getItemFromBlock(object.get()));
	}
	public static boolean isDragonEgg;
	@SuppressWarnings("deprecation")
	public void addBlocks() {
		putRecipe(Items.CARROT, NWISBlocks.FAKE_CARROTS);
		putRecipe(Items.POTATO, NWISBlocks.FAKE_POTATO);
		putRecipe(Items.WHEAT_SEEDS, NWISBlocks.FAKE_WHEAT);
		putRecipe(Items.BEETROOT_SEEDS, NWISBlocks.FAKE_BEETROOT);
		putRecipe(Items.MELON_SEEDS, NWISBlocks.FAKE_MELON_STEM);
		putRecipe(Items.PUMPKIN_SEEDS, NWISBlocks.FAKE_PUMPKIN_STEM);
		putRecipe(Items.NETHER_WART, NWISBlocks.FAKE_NETHER_WART);
		
		//Saplings
		putRecipe(Items.OAK_SAPLING, NWISBlocks.FAKE_OAK_SAPLING);
		putRecipe(Items.BIRCH_SAPLING, NWISBlocks.FAKE_BIRCH_SAPLING);
		putRecipe(Items.SPRUCE_SAPLING, NWISBlocks.FAKE_SPRUCE_SAPLING);
		putRecipe(Items.JUNGLE_SAPLING, NWISBlocks.FAKE_JUNGLE_SAPLING);
		putRecipe(Items.DARK_OAK_SAPLING, NWISBlocks.FAKE_DARK_OAK_SAPLING);
		putRecipe(Items.ACACIA_SAPLING, NWISBlocks.FAKE_ACACIA_SAPLING);
		
		putRecipe(Items.VINE, NWISBlocks.FAKE_VINE);
		putRecipe(Items.GRASS_BLOCK, NWISBlocks.STATIC_GRASS);
		putRecipe(Items.PODZOL, NWISBlocks.STATIC_PODZOL);
		putRecipe(Items.MYCELIUM, NWISBlocks.STATIC_MYCELIUM);
		
		putRecipe(Items.BROWN_MUSHROOM, NWISBlocks.FAKE_MUSHOOM_2);
		putRecipe(Items.BROWN_MUSHROOM_BLOCK, NWISBlocks.SPECIAL_MUSHROOM);
		
		putRecipe(Items.RED_MUSHROOM, NWISBlocks.FAKE_MUSHOOM_1);
		putRecipe(Items.RED_MUSHROOM_BLOCK, NWISBlocks.SPECIAL_MUSHROOM_R);
		
		putRecipe(Items.MUSHROOM_STEM, NWISBlocks.FAKE_MUSHROOM_STEM);
		if (isDragonEgg) {
			putRecipe(Items.DRAGON_EGG, NWISBlocks.FAKE_DRAGON_EGG);
		}
		putRecipe(Items.CHORUS_FRUIT, NWISBlocks.FAKE_CHORUS);
		putRecipe(Items.CHORUS_PLANT, NWISBlocks.FAKE_CHORUS);
		putRecipe(Items.CHORUS_FLOWER, NWISBlocks.FAKE_CHORUS_FLOWER);
		putRecipe(Items.SEAGRASS, NWISBlocks.SEAGRASS_SMOL);
		putRecipe(Items.NETHER_WART, NWISBlocks.FAKE_NETHER_WART);
		putRecipe(Items.COCOA_BEANS, NWISBlocks.FAKE_COCOA_BEAN);
		putRecipe(Items.GRASS_PATH, NWISBlocks.STATIC_PATH);
		
		putRecipe(Items.ALLIUM, NWISBlocks.FAKE_ALLIUM);
		putRecipe(Items.AZURE_BLUET, NWISBlocks.FAKE_AZURE_BLUET);
		putRecipe(Items.BLUE_ORCHID, NWISBlocks.FAKE_BLUE_ORCHID);
		putRecipe(Items.CORNFLOWER, NWISBlocks.FAKE_CORNFLOWER);
		putRecipe(Items.DANDELION, NWISBlocks.FAKE_DANDELION);
		putRecipe(Items.DEAD_BUSH, NWISBlocks.FAKE_DEAD_BUSH);
		putRecipe(Items.FERN, NWISBlocks.FAKE_FERN);
		putRecipe(Items.LARGE_FERN, NWISBlocks.FAKE_TALL_FERN);
		putRecipe(Items.GRASS, NWISBlocks.FAKE_GRASS);
		putRecipe(Items.TALL_GRASS, NWISBlocks.FAKE_TALL_GRASS);
		putRecipe(Items.SUNFLOWER, NWISBlocks.FAKE_SUNFLOWER);
		putRecipe(Items.LILAC, NWISBlocks.FAKE_LILAC);
		putRecipe(Items.ROSE_BUSH, NWISBlocks.FAKE_ROSEBUSH);
		putRecipe(Items.PEONY, NWISBlocks.FAKE_PEONY);
		
		
		putRecipe(Items.WITHER_ROSE, NWISBlocks.FAKE_WITHER_ROSE);
		putRecipe(Items.LILY_OF_THE_VALLEY, NWISBlocks.FAKE_LILY_OF_THE_VALLEY);
		putRecipe(Items.OXEYE_DAISY, NWISBlocks.FAKE_OXEYE_DAISY);
		putRecipe(Items.ORANGE_TULIP, NWISBlocks.FAKE_ORANGE_TULIP);
		putRecipe(Items.PINK_TULIP, NWISBlocks.FAKE_PINK_TULIP);
		putRecipe(Items.RED_TULIP, NWISBlocks.FAKE_RED_TULIP);
		putRecipe(Items.WHITE_TULIP, NWISBlocks.FAKE_WHITE_TULIP);

		putRecipe(Items.CRIMSON_NYLIUM, NWISBlocks.STATIC_NYLIUM_CRIMSON);
		putRecipe(Items.WARPED_NYLIUM, NWISBlocks.STATIC_NYLIUM_WARPED);

		putRecipe(Items.WEEPING_VINES, NWISBlocks.FAKE_WEEPING_VINE);
		putRecipe(Items.TWISTING_VINES, NWISBlocks.FAKE_TWISTING_VINE);
		putRecipe(Items.CRIMSON_ROOTS, NWISBlocks.FAKE_CRIMSON_ROOT);
		putRecipe(Items.WARPED_ROOTS, NWISBlocks.FAKE_WARPED_ROOT);
		putRecipe(Items.WARPED_FUNGUS, NWISBlocks.FAKE_WARPED_FUNGUS);
		putRecipe(Items.CRIMSON_FUNGUS, NWISBlocks.FAKE_CRIMSON_FUNGUS);

		putRecipe(Items.NETHER_SPROUTS, NWISBlocks.FAKE_NETHER_SPROUT);
		putRecipe(Items.CHAIN, NWISBlocks.FAKE_CHAIN);
		putRecipe(Items.LANTERN, NWISBlocks.FAKE_LANTERN);
		putRecipe(Items.SOUL_LANTERN, NWISBlocks.FAKE_SOUL_LANTERN);
		//
		if (NotWhatItSeems.atmospheric) {
			addModdedRecipe("atmospheric", "rosewood_sapling", NWISBlocks.ROSEWOOD_SAPLING);
			addModdedRecipe("atmospheric", "yucca_sapling", NWISBlocks.YUCCA_SAPLING);
			addModdedRecipe("atmospheric", "aspen_sapling", NWISBlocks.ASPEN_SAPLING);
			addModdedRecipe("atmospheric", "kousa_sapling", NWISBlocks.KOUSA_SAPLING);
			addModdedRecipe("atmospheric", "passion_vine", NWISBlocks.PASSION_VINE);
		}
		if (NotWhatItSeems.atumwhatever) {
			addModdedRecipe("autumnity", "maple_sapling", NWISBlocks.MAPLE_SAPLING);
			addModdedRecipe("autumnity", "red_maple_sapling", NWISBlocks.MAPLE_SAPLING_RED);
			addModdedRecipe("autumnity", "orange_maple_sapling", NWISBlocks.MAPLE_SAPLING_ORANGE);
			addModdedRecipe("autumnity", "yellow_maple_sapling", NWISBlocks.MAPLE_SAPLING_YELLOW);
		}
		if (NotWhatItSeems.environmental) {
			addModdedRecipe("environmental", "blue_wisteria_sapling", NWISBlocks.WISTERIA_BLUE);
			addModdedRecipe("environmental", "pink_wisteria_sapling", NWISBlocks.WISTERIA_PINK);
			addModdedRecipe("environmental", "purple_wisteria_sapling", NWISBlocks.WISTERIA_PURPLE);
			addModdedRecipe("environmental", "white_wisteria_sapling", NWISBlocks.WISTERIA_WHITE);
		}
		if (NotWhatItSeems.endergetic) {
			addModdedRecipe("endergetic", "poise_grass_block", NWISBlocks.STATIC_POSIMOSS);
			addModdedRecipe("endergetic", "poismoss_eumus", NWISBlocks.STATIC_POSIMOSS_EUMUS);
			addModdedRecipe("endergetic", "poise_cluster", NWISBlocks.STATIC_POISE_CLUSTER);
		}
		if (NotWhatItSeems.quark) {
			addModdedRecipe("quark", "glowcelium", NWISBlocks.FAKE_GLOWCELIUM);
			addModdedRecipe("quark", "glowshroom", NWISBlocks.FAKE_GLOWSHROOM);
			addModdedRecipe("quark", "glowshroom_stem", NWISBlocks.FAKE_BIG_GLOWSHROOM_STEM);
			addModdedRecipe("quark", "glowshroom_block", NWISBlocks.FAKE_BIG_GLOWSHROOM);
			
			addModdedRecipe("quark", "blue_blossom_sapling", NWISBlocks.FROSTY_SAPLING);
			addModdedRecipe("quark", "lavender_blossom_sapling", NWISBlocks.SERENE_SAPLING);
			addModdedRecipe("quark", "orange_blossom_sapling", NWISBlocks.WARM_SAPLING);
			addModdedRecipe("quark", "pink_blossom_sapling", NWISBlocks.SWEET_SAPLING);
			addModdedRecipe("quark", "yellow_blossom_sapling", NWISBlocks.SUNNY_SAPLING);
			
			
		}
		if (NotWhatItSeems.environmental) {
			addModdedRecipe("environmental", "rice", NWISBlocks.RICE);
			addModdedRecipe("environmental", "cattail", NWISBlocks.CATTAIL_SPROUT);
			addModdedRecipe("environmental", "willow_sapling", NWISBlocks.WILLOW_SAPLING);
		}
		if (NotWhatItSeems.ua) {
			addModdedRecipe("upgrade_aquatic", "pickerel_weed_blue", NWISBlocks.PICKELREED_BLUE);
			addModdedRecipe("upgrade_aquatic", "pickerel_weed_purple", NWISBlocks.PICKELREED_PURPLE);
			addModdedRecipe("upgrade_aquatic", "beachgrass", NWISBlocks.BEACHGRASS);
			addModdedRecipe("upgrade_aquatic", "tall_beachgrass", NWISBlocks.BEACHGRASS_SMOL);
			addModdedRecipe("upgrade_aquatic", "elder_prismarine_coral_shower", NWISBlocks.ELDER_PRISMARINE_SHOWER);
			addModdedRecipe("upgrade_aquatic", "prismarine_coral_shower", NWISBlocks.PRISMARINE_SHOWER);
			
			addModdedRecipe("upgrade_aquatic", "tongue_kelp", NWISBlocks.KELP_TONGUE);
			addModdedRecipe("upgrade_aquatic", "ochre_kelp", NWISBlocks.KELP_OCHRE);
			addModdedRecipe("upgrade_aquatic", "polar_kelp", NWISBlocks.KELP_POLAR);
			addModdedRecipe("upgrade_aquatic", "thorny_kelp", NWISBlocks.KELP_THORNY);

		}
		if (NotWhatItSeems.buzzierbees) {
			addModdedRecipe("buzzierbees", "magenta_hibiscus", NWISBlocks.FAKE_HIBISCUS_MAGENTA);
			addModdedRecipe("buzzierbees", "orange_hibiscus", NWISBlocks.FAKE_HIBISCUS_ORANGE);
			addModdedRecipe("buzzierbees", "pink_hibiscus", NWISBlocks.FAKE_HIBISCUS_PINK);
			addModdedRecipe("buzzierbees", "purple_hibiscus", NWISBlocks.FAKE_HIBISCUS_PURPLE);
			addModdedRecipe("buzzierbees", "red_hibiscus", NWISBlocks.FAKE_HIBISCUS_RED);
			addModdedRecipe("buzzierbees", "daybloom", NWISBlocks.FAKE_HIBISCUS_YELLOW);
			
			addModdedRecipe("buzzierbees", "jolyce", NWISBlocks.FAKE_DIANTHUS);
			addModdedRecipe("buzzierbees", "cartwheel", NWISBlocks.FAKE_CARTWHEEL);
			addModdedRecipe("buzzierbees", "bluebell", NWISBlocks.FAKE_BLUEBELL);
			addModdedRecipe("buzzierbees", "columbine", NWISBlocks.FAKE_COLUMBINE);
			addModdedRecipe("buzzierbees", "pink_clover", NWISBlocks.FAKE_PINK_CLOVER);
			addModdedRecipe("buzzierbees", "white_clover", NWISBlocks.FAKE_WHITE_CLOVER);
			addModdedRecipe("buzzierbees", "violet", NWISBlocks.FAKE_VIOLET);
			addModdedRecipe("buzzierbees", "bird_of_paradise", NWISBlocks.FAKE_BIRD_OF_PARADISE);
			
		}
		if (NotWhatItSeems.ua) {
			Iterator<ArrayList<String>> iter_1 = ua_corals.iterator();
			while (iter_1.hasNext()) {
				ArrayList<String> e = iter_1.next();
				Iterator<String> iter_2 = e.iterator();
				while (iter_2.hasNext()) {
					String id = iter_2.next();
					String id2 = id;
					if (id.contains("prismarine")) {
						id2.replace("dead", "elder");
					}
					ITEMS.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation("upgrade_aquatic", id2)), ForgeRegistries.ITEMS.getValue(new ResourceLocation("notwhatitseems", id)));
				}
			}
		}
		Iterator<ArrayList<String>> iter_1 = vanilla_corals.iterator();
		while (iter_1.hasNext()) {
			ArrayList<String> e = iter_1.next();
			Iterator<String> iter_2 = e.iterator();
			while (iter_2.hasNext()) {
				String id = iter_2.next();
				ITEMS.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", id)), ForgeRegistries.ITEMS.getValue(new ResourceLocation("notwhatitseems", id)));
			}
		}
		
		
	}
	private final IInventory outputInventory = new CraftResultInventory();
	private final IInventory inputInventory = new Inventory(2) {
		public void markDirty() {
         	super.markDirty();
         	ImposterContainer.this.onCraftMatrixChanged(this);
		}
	};
	private IWorldPosCallable callable;
	public ImposterContainer(ContainerType<?> type, int id) {
		super(type, id);
	}
	
	public ImposterContainer(int windowIdIn, PlayerInventory playerInv, final IWorldPosCallable worldPosCallableIn) {
		super(NWISTileEntities.COPIER.get(), windowIdIn);
		addBlocks();
		this.callable = worldPosCallableIn;
		this.addSlot(new Slot(this.inputInventory, 0, 44, 20) {
	         public boolean isItemValid(ItemStack stack) {
	        	boolean stacker = stack.getItem() == Items.PAPER;
	            return stacker;
	         }
	      });
		this.addSlot(new Slot(this.inputInventory, 1, 62, 20) {
	         public boolean isItemValid(ItemStack stack) {
	            return ITEMS.keySet().contains(stack.getItem());
	         }
	      });
		this.addSlot(new Slot(this.outputInventory, 0, 116, 20) {
	         public boolean isItemValid(ItemStack stack) {
	            return false;
	         }
	         
	         @Override
	        public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
	        	stack = inputInventory.getStackInSlot(0);
	        	stack.shrink(1);
	        	inputInventory.setInventorySlotContents(0, stack);
	        	return stack;
	        }
	      });
		for(int l = 0; l < 3; ++l) {
			for(int k = 0; k < 9; ++k) {
	    		this.addSlot(new Slot(playerInv, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
			}
	    }

	    for(int i1 = 0; i1 < 9; ++i1) {
	    	this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 109));
	    }
	    
	}
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.updateRecipeOutput();
	}
	private void updateRecipeOutput() {
		ItemStack paperStack = inputInventory.getStackInSlot(0);
		ItemStack copyStack = inputInventory.getStackInSlot(1);
		if (paperStack != ItemStack.EMPTY && paperStack.getItem() == Items.PAPER && copyStack != ItemStack.EMPTY) {
			this.outputInventory.setInventorySlotContents(0, new ItemStack(ITEMS.get(copyStack.getItem())));
		} 
		else if (paperStack.getItem() == Items.ELYTRA) {
			
		}
		else {
			this.outputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
		}
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
	    if (slot != null && slot.getHasStack()) {
	    	ItemStack itemstack1 = slot.getStack();
	    	ItemStack itemstack2 = this.inputInventory.getStackInSlot(0);
	        ItemStack itemstack3 = this.inputInventory.getStackInSlot(1);
	    	if (index == 2) {
	    		itemstack1.setCount(itemstack2.getCount());
	    		if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
	    			return ItemStack.EMPTY;
	 	        }
    			inputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
    			this.onCraftMatrixChanged(outputInventory);
	    		
	    		
	    	} else if (index != 0 && index != 1) {
	            if (!itemstack2.isEmpty() && !itemstack3.isEmpty()) {
	                if (index >= 3 && index < 30) {
	                   if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
	                      return ItemStack.EMPTY;
	                   }
	                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
	                   return ItemStack.EMPTY;
	                }
	             } else if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
	                return ItemStack.EMPTY;
	             }
	          } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
	             return ItemStack.EMPTY;
	          }

	          if (itemstack1.isEmpty()) {
	             slot.putStack(ItemStack.EMPTY);
	          } else {
	             slot.onSlotChanged();
	          }

	          if (itemstack1.getCount() == itemstack.getCount()) {
	             return ItemStack.EMPTY;
	          }

	          slot.onTake(playerIn, itemstack1);
	    	
	    	slot.onSlotChange(itemstack1, itemstack);
	    }
		return itemstack;
	}
	public void onContainerClosed(PlayerEntity playerIn) {
	      super.onContainerClosed(playerIn);
	      this.callable.consume((p_217068_2_, p_217068_3_) -> {
	         this.clearContainer(playerIn, p_217068_2_, this.inputInventory);
	      });
	   }

}
