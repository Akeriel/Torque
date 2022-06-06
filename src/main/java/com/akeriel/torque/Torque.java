package com.akeriel.torque;

import com.akeriel.torque.block.ModBlocks;
import com.akeriel.torque.item.ModItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Torque.MOD_ID)
public class Torque {
	
    public static final String MOD_ID = "torque";
    
    public static final CreativeModeTab TORQUE_TAB = new CreativeModeTab("torquetab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.WOOD_GEAR.get());
        }
    };
	    
	 // Directly reference a slf4j logger
	    //private static final Logger LOGGER = LogUtils.getLogger();

    public Torque() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	//stuff
    }

}
