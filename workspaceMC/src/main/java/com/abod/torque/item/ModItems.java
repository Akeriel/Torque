package com.abod.torque.item;

import com.abod.torque.Torque;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Torque.MOD_ID);

    public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench",() -> new Item(new Item.Properties().tab(Torque.TORQUE_TAB).stacksTo(1)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
