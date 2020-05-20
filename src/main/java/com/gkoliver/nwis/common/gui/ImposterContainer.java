package com.gkoliver.nwis.common.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.gkoliver.nwis.NotWhatItSeems;
import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.register.TileEntityRegistry;
import com.gkoliver.nwis.core.util.SharedFunctions;

import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.GrindstoneContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
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
	@SuppressWarnings("deprecation")
	public void addBlocks() {
		putRecipe(Items.CARROT, BlockRegistry.FAKE_CARROTS);
		putRecipe(Items.POTATO, BlockRegistry.FAKE_POTATO);
		putRecipe(Items.WHEAT_SEEDS, BlockRegistry.FAKE_WHEAT);
		putRecipe(Items.BEETROOT_SEEDS, BlockRegistry.FAKE_BEETROOT);
		putRecipe(Items.MELON_SEEDS, BlockRegistry.FAKE_MELON_STEM);
		putRecipe(Items.PUMPKIN_SEEDS, BlockRegistry.FAKE_PUMPKIN_STEM);
		putRecipe(Items.NETHER_WART, BlockRegistry.FAKE_NETHER_WART);
		
		//Saplings
		putRecipe(Items.OAK_SAPLING, BlockRegistry.FAKE_OAK_SAPLING);
		putRecipe(Items.BIRCH_SAPLING, BlockRegistry.FAKE_BIRCH_SAPLING);
		putRecipe(Items.SPRUCE_SAPLING, BlockRegistry.FAKE_SPRUCE_SAPLING);
		putRecipe(Items.JUNGLE_SAPLING, BlockRegistry.FAKE_JUNGLE_SAPLING);
		putRecipe(Items.DARK_OAK_SAPLING, BlockRegistry.FAKE_DARK_OAK_SAPLING);
		putRecipe(Items.ACACIA_SAPLING, BlockRegistry.FAKE_ACACIA_SAPLING);
		
		putRecipe(Items.VINE, BlockRegistry.FAKE_VINE);
		putRecipe(Items.GRASS_BLOCK, BlockRegistry.STATIC_GRASS);
		putRecipe(Items.PODZOL, BlockRegistry.STATIC_PODZOL);
		putRecipe(Items.MYCELIUM, BlockRegistry.STATIC_MYCELIUM);
		
		putRecipe(Items.BROWN_MUSHROOM, BlockRegistry.FAKE_MUSHOOM_2);
		putRecipe(Items.BROWN_MUSHROOM_BLOCK, BlockRegistry.SPECIAL_MUSHROOM);
		
		putRecipe(Items.RED_MUSHROOM, BlockRegistry.FAKE_MUSHOOM_1);
		putRecipe(Items.RED_MUSHROOM_BLOCK, BlockRegistry.SPECIAL_MUSHROOM_R);
		
		putRecipe(Items.MUSHROOM_STEM, BlockRegistry.FAKE_MUSHROOM_STEM);
		putRecipe(Items.DRAGON_EGG, BlockRegistry.FAKE_DRAGON_EGG);
		
		putRecipe(Items.CHORUS_FRUIT, BlockRegistry.FAKE_CHORUS);
		putRecipe(Items.CHORUS_PLANT, BlockRegistry.FAKE_CHORUS);
		putRecipe(Items.CHORUS_FLOWER, BlockRegistry.FAKE_CHORUS_FLOWER);
		putRecipe(Items.SEAGRASS, BlockRegistry.SEAGRASS_SMOL);
		putRecipe(Items.NETHER_WART, BlockRegistry.FAKE_NETHER_WART);
		putRecipe(Items.COCOA_BEANS, BlockRegistry.FAKE_COCOA_BEAN);
		//
		if (NotWhatItSeems.atmospheric) {
			addModdedRecipe("atmospheric", "rosewood_sapling", BlockRegistry.ROSEWOOD_SAPLING);
			addModdedRecipe("atmospheric", "yucca_sapling", BlockRegistry.YUCCA_SAPLING);
			addModdedRecipe("atmospheric", "aspen_sapling", BlockRegistry.ASPEN_SAPLING);
			addModdedRecipe("atmospheric", "kousa_sapling", BlockRegistry.KOUSA_SAPLING);
			addModdedRecipe("atmospheric", "passion_vine", BlockRegistry.PASSION_VINE);
		}
		if (NotWhatItSeems.atumwhatever) {
			addModdedRecipe("autumnity", "maple_sapling", BlockRegistry.MAPLE_SAPLING);
			addModdedRecipe("autumnity", "red_maple_sapling", BlockRegistry.MAPLE_SAPLING_RED);
			addModdedRecipe("autumnity", "orange_maple_sapling", BlockRegistry.MAPLE_SAPLING_ORANGE);
			addModdedRecipe("autumnity", "yellow_maple_sapling", BlockRegistry.MAPLE_SAPLING_YELLOW);
		}
		if (NotWhatItSeems.bloomful) {
			addModdedRecipe("bloomful", "blue_wisteria_sapling", BlockRegistry.WISTERIA_BLUE);
			addModdedRecipe("bloomful", "pink_wisteria_sapling", BlockRegistry.WISTERIA_PINK);
			addModdedRecipe("bloomful", "purple_wisteria_sapling", BlockRegistry.WISTERIA_PURPLE);
			addModdedRecipe("bloomful", "white_wisteria_sapling", BlockRegistry.WISTERIA_WHITE);
		}
		if (NotWhatItSeems.endergetic) {
			addModdedRecipe("endergetic", "poise_grass_block", BlockRegistry.STATIC_POSIMOSS);
			addModdedRecipe("endergetic", "poismoss_eumus", BlockRegistry.STATIC_POSIMOSS_EUMUS);
			addModdedRecipe("endergetic", "poise_cluster", BlockRegistry.STATIC_POISE_CLUSTER);
		}
		if (NotWhatItSeems.quark) {
			addModdedRecipe("quark", "glowcelium", BlockRegistry.FAKE_GLOWCELIUM);
			addModdedRecipe("quark", "glowshroom", BlockRegistry.FAKE_GLOWSHROOM);
			addModdedRecipe("quark", "glowshroom_stem", BlockRegistry.FAKE_BIG_GLOWSHROOM_STEM);
			addModdedRecipe("quark", "glowshroom_block", BlockRegistry.FAKE_BIG_GLOWSHROOM);
			
			addModdedRecipe("quark", "blue_blossom_sapling", BlockRegistry.FROSTY_SAPLING);
			addModdedRecipe("quark", "lavender_blossom_sapling", BlockRegistry.SERENE_SAPLING);
			addModdedRecipe("quark", "orange_blossom_sapling", BlockRegistry.WARM_SAPLING);
			addModdedRecipe("quark", "pink_blossom_sapling", BlockRegistry.SWEET_SAPLING);
			addModdedRecipe("quark", "yellow_blossom_sapling", BlockRegistry.SUNNY_SAPLING);
			
			
		}
		if (NotWhatItSeems.swampexpansion) {
			addModdedRecipe("swampexpansion", "rice", BlockRegistry.RICE);
			addModdedRecipe("swampexpansion", "cattail", BlockRegistry.CATTAIL_SPROUT);
			addModdedRecipe("swampexpansion", "willow_sapling", BlockRegistry.WILLOW_SAPLING);
		}
		if (NotWhatItSeems.ua) {
			addModdedRecipe("upgrade_aquatic", "pickerel_weed_blue", BlockRegistry.PICKELREED_BLUE);
			addModdedRecipe("upgrade_aquatic", "pickerel_weed_purple", BlockRegistry.PICKELREED_PURPLE);
			addModdedRecipe("upgrade_aquatic", "beachgrass", BlockRegistry.BEACHGRASS);
			addModdedRecipe("upgrade_aquatic", "tall_beachgrass", BlockRegistry.BEACHGRASS_SMOL);
			addModdedRecipe("upgrade_aquatic", "elder_prismarine_coral_shower", BlockRegistry.ELDER_PRISMARINE_SHOWER);
			addModdedRecipe("upgrade_aquatic", "prismarine_coral_shower", BlockRegistry.PRISMARINE_SHOWER);
			
			addModdedRecipe("upgrade_aquatic", "tongue_kelp", BlockRegistry.KELP_TONGUE);
			addModdedRecipe("upgrade_aquatic", "ochre_kelp", BlockRegistry.KELP_OCHRE);
			addModdedRecipe("upgrade_aquatic", "polar_kelp", BlockRegistry.KELP_POLAR);
			addModdedRecipe("upgrade_aquatic", "thorny_kelp", BlockRegistry.KELP_THORNY);
		}
		
		if (NotWhatItSeems.ua) {
			Iterator<ArrayList<String>> iter_1 = ua_corals.iterator();
			while (iter_1.hasNext()) {
				ArrayList<String> e = iter_1.next();
				Iterator<String> iter_2 = e.iterator();
				while (iter_2.hasNext()) {
					String id = iter_2.next();
					String id2 = iter_2.next();
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
		super(TileEntityRegistry.COPIER.get(), windowIdIn);
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
	            return ITEMS.keySet().contains(stack.getItem()) || stack.getItem()==Items.OBSIDIAN;
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
