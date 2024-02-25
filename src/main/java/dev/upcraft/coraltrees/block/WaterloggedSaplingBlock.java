package dev.upcraft.coraltrees.block;

import dev.upcraft.coraltrees.init.CoralTreePlantTypes;
import dev.upcraft.coraltrees.init.CoralTreeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class WaterloggedSaplingBlock extends SaplingBlock implements SimpleWaterloggedBlock {

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public WaterloggedSaplingBlock(AbstractTreeGrower pTreeGrower, Properties pProperties) {
        super(pTreeGrower, pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0).setValue(WATERLOGGED, false));
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(CoralTreeTags.Blocks.COROAK_PLANTABLE);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        // only grow if waterlogged, but ignore light level
        if (pState.getValue(WATERLOGGED) && pRandom.nextFloat() < 0.14) {
            this.advanceTree(pLevel, pPos, pState, pRandom);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, Fluid pFluid) {
        return !ForgeRegistries.FLUIDS.tags().getTag(FluidTags.WATER).contains(pFluid);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(WATERLOGGED);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        return pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, Direction.UP);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return super.getStateForPlacement(pContext).setValue(WATERLOGGED, fluidstate.is(FluidTags.WATER));
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return CoralTreePlantTypes.CORAL;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(WATERLOGGED);
    }
}
