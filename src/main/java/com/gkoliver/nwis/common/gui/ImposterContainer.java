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
import net.minecraftforge.registries.ForgeRegistries;

public class ImposterContainer extends Container {
	public static ArrayList<ArrayList<String>> vanilla_corals = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> ua_corals = new ArrayList<ArrayList<String>>();
	public static final HashMap<Item, Item> ITEMS = new HashMap<Item, Item>() {
		private static final long serialVersionUID = 1L;
	};
	@SuppressWarnings("deprecation")
	public void addBlocks() {
		ITEMS.put(Items.CARROT, Item.getItemFromBlock(BlockRegistry.FAKE_CARROTS.get()));
		ITEMS.put(Items.POTATO, Item.getItemFromBlock(BlockRegistry.FAKE_POTATO.get()));
		ITEMS.put(Items.WHEAT_SEEDS, Item.getItemFromBlock(BlockRegistry.FAKE_WHEAT.get()));
		ITEMS.put(Items.BEETROOT_SEEDS, Item.getItemFromBlock(BlockRegistry.FAKE_BEETROOT.get()));
		ITEMS.put(Items.CARROT, Item.getItemFromBlock(BlockRegistry.FAKE_CARROTS.get()));
		ITEMS.put(Items.BEETROOT, Item.getItemFromBlock(BlockRegistry.DILLUTED_VOID_BLOCK.get()));
		
		//Saplings
		ITEMS.put(Items.OAK_SAPLING, Item.getItemFromBlock(BlockRegistry.FAKE_OAK_SAPLING.get()));
		ITEMS.put(Items.BIRCH_SAPLING, Item.getItemFromBlock(BlockRegistry.FAKE_BIRCH_SAPLING.get()));
		ITEMS.put(Items.SPRUCE_SAPLING, Item.getItemFromBlock(BlockRegistry.FAKE_SPRUCE_SAPLING.get()));
		ITEMS.put(Items.JUNGLE_SAPLING, Item.getItemFromBlock(BlockRegistry.FAKE_JUNGLE_SAPLING.get()));
		ITEMS.put(Items.DARK_OAK_SAPLING, Item.getItemFromBlock(BlockRegistry.FAKE_DARK_OAK_SAPLING.get()));
		ITEMS.put(Items.ACACIA_SAPLING, Item.getItemFromBlock(BlockRegistry.FAKE_ACACIA_SAPLING.get()));
		
		//

		if (NotWhatItSeems.ua) {
			Iterator<ArrayList<String>> iter_1 = ua_corals.iterator();
			while (iter_1.hasNext()) {
				ArrayList<String> e = iter_1.next();
				Iterator<String> iter_2 = e.iterator();
				while (iter_2.hasNext()) {
					String id = iter_2.next();
					if (id.contains("prismarine")) {
						id.replace("dead", "elder");
					}
					ITEMS.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation("upgrade_aquatic", id)), ForgeRegistries.ITEMS.getValue(new ResourceLocation("notwhatitseems", id)));
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
	            return stack.getItem() == Items.PAPER;
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
	    if (!playerInv.player.world.isRemote()) {
	    	ServerPlayerEntity server = (ServerPlayerEntity) playerInv.player;
	    	if (SharedFunctions.checkHasNether(server)) {
	    		Slot slot = new Slot(inputInventory, 2, 44, 20) {
		   	         public boolean isItemValid(ItemStack stack) {
		   	            return stack.getItem() == Items.ELYTRA ||
		   	            		stack.getItem() == Items.ENDER_EYE;
		   	         }
	    		};
	    		this.addSlot(slot);
	    	}
	    }
	    
	}
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.updateRecipeOutput();
	}
	private void updateRecipeOutput() {
		ItemStack paperStack = inputInventory.getStackInSlot(0);
		ItemStack copyStack = inputInventory.getStackInSlot(1);
		if (paperStack != ItemStack.EMPTY && copyStack != ItemStack.EMPTY) {
			this.outputInventory.setInventorySlotContents(0, new ItemStack(ITEMS.get(copyStack.getItem())));
		} else {
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
