package com.akeriel.torque.block.gear;

import com.akeriel.torque.block.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

public class GearRenderer implements BlockEntityRenderer<GearBlockEntity>{
    private final BlockEntityRendererProvider.Context context;
    
    public GearRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }
    
	@Override
	public void render(GearBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource,
			int pPackedLight, int pPackedOverlay) {

        final BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        
        pPoseStack.pushPose();
        
        pPoseStack.translate(0.5, 0, 0.5);
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(11.25F * pBlockEntity.getBlockState().getValue(GearBlock.SHIFT)));
        pPoseStack.translate(-0.5, 0, -0.5);
        
        
        BlockState defState = pBlockEntity.getBlockState().getBlock().defaultBlockState();
        BakedModel bakedmodel = dispatcher.getBlockModel(defState);
        int i = pBlockEntity.getBlockState().getBlock().defaultMaterialColor().col;
        float f = (float)(i >> 16 & 255) / 255.0F;
        float f1 = (float)(i >> 8 & 255) / 255.0F;
        float f2 = (float)(i & 255) / 255.0F;
        dispatcher.getModelRenderer().renderModel(pPoseStack.last(), pBufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(defState, false)), defState, bakedmodel, f, f1, f2, pPackedLight, pPackedOverlay, EmptyModelData.INSTANCE);
        
        pPoseStack.popPose();
		
	}
    
    
}
