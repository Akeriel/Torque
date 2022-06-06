package com.akeriel.torque.block.gear;

import java.util.HashSet;
import java.util.Set;

import com.akeriel.torque.block.ModBlocks;
import com.akeriel.torque.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GearBlock extends Block implements EntityBlock {
	
	public static final IntegerProperty SHIFT = IntegerProperty.create("shift", 0, 7);
    public GearBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(SHIFT, 0));
    }
    
    
    private static void getGearSets(BlockState pState, Level pLevel, BlockPos pPos, Set<BlockPos> front, Set<BlockPos> back) {
    	front.add(pPos);
		for (Direction direction : Direction.values()){ // For each direction
			if(!direction.equals(Direction.UP) && !direction.equals(Direction.DOWN)) {
				BlockPos neighbourPos = pPos.offset(direction.getStepX(), direction.getStepY(), direction.getStepZ()); // Offset the block's position by 1 block in the current direction
			    BlockState neighbourState = pLevel.getBlockState(neighbourPos);
			    Block neighbourBlock = neighbourState.getBlock(); 
			    if (neighbourBlock instanceof GearBlock){
			    	if(!back.contains(neighbourPos)) {
			    		getGearSets(neighbourState, pLevel, neighbourPos, back, front);
			    	}
			    }
			 }
		 }
    }
    
    private static void rotateSet(Set<BlockPos> positions, Level pLevel, int degree) {
    	for(BlockPos gear : positions) {
			BlockState gearState = pLevel.getBlockState(gear);
			int shift = gearState.getValue(SHIFT) + degree;
			if(shift > 7) {
				shift -= 8;
			} else if (shift < 0){
				shift += 8;	
			}
			pLevel.setBlockAndUpdate(gear, gearState.setValue(SHIFT, shift));
		}
    }
    
    
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
    		BlockHitResult pHit) {
    	
    	if(!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND && pPlayer.isHolding(ModItems.WRENCH.get())) {
    		Set<BlockPos> front = new HashSet<BlockPos>();
    		Set<BlockPos> back = new HashSet<BlockPos>();
    		getGearSets(pState, pLevel, pPos, front, back);
    		rotateSet(front, pLevel, 1);
    		rotateSet(back, pLevel, -1);
    	}
    	return InteractionResult.SUCCESS;
    }
    
    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 1, 15);
    
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    	return SHAPE;
    }
    
    @Override
    public RenderShape getRenderShape(BlockState pState) {
    		return RenderShape.INVISIBLE;
    }

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return ModBlocks.GEAR_ENTITY.get().create(pPos, pState);
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
		pBuilder.add(SHIFT);
	}

}

