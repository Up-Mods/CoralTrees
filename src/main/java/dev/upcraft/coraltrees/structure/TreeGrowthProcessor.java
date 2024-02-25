package dev.upcraft.coraltrees.structure;

import com.mojang.serialization.Codec;
import dev.upcraft.coraltrees.init.CoralTreeStructureProcessorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class TreeGrowthProcessor extends StructureProcessor {

    public static Codec<TreeGrowthProcessor> CODEC = Codec.unit(TreeGrowthProcessor::new);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos p_74141_, BlockPos p_74142_, StructureTemplate.StructureBlockInfo p_74143_, StructureTemplate.StructureBlockInfo absoluteBlockInfo, StructurePlaceSettings placeSettings, @Nullable StructureTemplate template) {
        var existingState = level.getBlockState(absoluteBlockInfo.pos);

        if (existingState.isAir()) {
            return absoluteBlockInfo;
        }

        var material = existingState.getMaterial();

        if (material.isReplaceable() || material == Material.AIR || material == Material.REPLACEABLE_PLANT || material == Material.REPLACEABLE_WATER_PLANT || material == Material.REPLACEABLE_FIREPROOF_PLANT) {
            return absoluteBlockInfo;
        }

        return null;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return CoralTreeStructureProcessorTypes.TREE_GROWTH_PROCESSOR.get();
    }
}
