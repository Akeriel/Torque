package com.akeriel.torque.events;

import com.akeriel.torque.block.ModBlocks;
import com.akeriel.torque.block.gear.GearRenderer;
import com.akeriel.torque.Torque;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = Torque.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    
    private ClientModEvents() {
    }
    
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEAR.get(), RenderType.cutout());
    }
    
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.GEAR_ENTITY.get(), GearRenderer::new);
    }
}
