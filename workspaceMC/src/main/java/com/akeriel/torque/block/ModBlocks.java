package com.akeriel.torque.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import com.akeriel.torque.block.gear.GearBlockEntity;
import com.akeriel.torque.block.gear.GearBlock;
import com.akeriel.torque.item.ModItems;
import com.akeriel.torque.Torque;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Torque.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITIES, Torque.MOD_ID);

    // Block Registries
    public static final RegistryObject<Block> WOOD_GEAR =
            registerBlock("wood_gear",() -> new GearBlock(BlockBehaviour.Properties
                    .of(Material.WOOD).strength(9f).noDrops().noOcclusion()),
                    Torque.TORQUE_TAB);
    public static final RegistryObject<Block> IRON_GEAR =
            registerBlock("iron_gear",() -> new GearBlock(BlockBehaviour.Properties
                    .of(Material.METAL).strength(9f).noDrops().noOcclusion()),
                    Torque.TORQUE_TAB);
    public static final RegistryObject<Block> OBSIDIAN_GEAR =
            registerBlock("obsidian_gear",() -> new GearBlock(BlockBehaviour.Properties
                    .of(Material.STONE).strength(9f).noDrops().noOcclusion()),
                    Torque.TORQUE_TAB);
    public static final RegistryObject<BlockEntityType<GearBlockEntity>> GEAR_ENTITY = BLOCK_ENTITIES.register("gear_entity",
            () -> BlockEntityType.Builder.of(GearBlockEntity::new, ModBlocks.WOOD_GEAR.get(), ModBlocks.IRON_GEAR.get(), ModBlocks.OBSIDIAN_GEAR.get()).build(null));
    
    

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }

    private static <T extends Block> RegistryObject<Item>
    registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T>
    registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
}

