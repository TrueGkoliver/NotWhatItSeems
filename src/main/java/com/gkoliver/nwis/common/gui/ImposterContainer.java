package com.gkoliver.nwis.common.gui;

import java.util.HashMap;

import com.gkoliver.nwis.core.register.BlockRegistry;
import com.gkoliver.nwis.core.register.TileEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class ImposterContainer extends Container {
	public static final HashMap<Item, Item> ITEMS = new HashMap<Item, Item>() {
		private static final long serialVersionUID = 1L;
		{
			put(Items.CARROT, Item.getItemFromBlock(BlockRegistry.FAKE_CARROTS.get()));
			put(Items.POTATO, Item.getItemFromBlock(BlockRegistry.FAKE_POTATO.get()));
			put(Items.WHEAT_SEEDS, Item.getItemFromBlock(BlockRegistry.FAKE_WHEAT.get()));
			put(Items.CARROT, Item.getItemFromBlock(BlockRegistry.FAKE_CARROTS.get()));
			put(Items.BEETROOT, Item.getItemFromBlock(BlockRegistry.DILLUTED_VOID_BLOCK.get()));
		}
		
	};
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
