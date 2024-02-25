package dev.upcraft.coraltrees.structure;

import dev.upcraft.coraltrees.CoralTreesMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CoralTreeGrower extends AbstractTreeGrower {

    private final TreeType type;

    public CoralTreeGrower(TreeType type) {
        this.type = type;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random, boolean hasFlowers) {
        var id = CoralTreesMod.id("tree/" + type.getSerializedName());
        var key = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, id);
        return level.registryAccess().registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY).getHolder(key).orElse(null);
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pLargeHive) {
        return null;
    }
}
