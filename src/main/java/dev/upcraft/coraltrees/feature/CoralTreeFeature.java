package dev.upcraft.coraltrees.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import dev.upcraft.coraltrees.structure.TreeGrowthProcessor;
import dev.upcraft.coraltrees.structure.TreeStructureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.slf4j.Logger;

public class CoralTreeFeature extends Feature<CoralTreeConfiguration> {

    private static final Logger LOGGER = LogUtils.getLogger();

    // copied from TreeFeature
    private static final int BLOCK_UPDATE_FLAGS = 19;

    public CoralTreeFeature(Codec<CoralTreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CoralTreeConfiguration> ctx) {
        var treeType = ctx.config().getTreeType();

        var structures = TreeStructureManager.getInstance().getStructures(treeType);
        if (structures.isEmpty()) {
            LOGGER.warn("No structures found for tree type {}!", treeType);
            return false;
        }

        var randomId = structures.get(ctx.random().nextInt(structures.size()));
        var template = ctx.level().getLevel().getStructureManager().getOrCreate(randomId);

        var size = template.getSize();
        // integer division is fine here because it is 0-indexed and the templates are usually odd-sized
        var centerRelative = new BlockPos(size.getX() / 2, 0, size.getZ() / 2);

        var placementSettings = new StructurePlaceSettings()
                .setRandom(ctx.random())
                .setKeepLiquids(true)
                .setRotationPivot(centerRelative)
                .setRotation(Rotation.getRandom(ctx.random()))
                .addProcessor(new TreeGrowthProcessor());

        var templateOrigin = ctx.origin().offset(-centerRelative.getX(), 0, -centerRelative.getZ());

        var result = template.placeInWorld(ctx.level(), templateOrigin, templateOrigin, placementSettings, ctx.random(), BLOCK_UPDATE_FLAGS);
        if(!result) {
            LOGGER.error("Failed to place structure at [{}, {}, {}]", ctx.origin().getX(), ctx.origin().getY(), ctx.origin().getZ());
        }
        else {
            ctx.level().playSound(null, ctx.origin(), SoundEvents.CORAL_BLOCK_PLACE, SoundSource.BLOCKS, 0.8F + (float) (ctx.level().getRandom().nextGaussian() * 0.5D), 0.7F + (float) (ctx.level().getRandom().nextGaussian() * 0.5D));
        }
        return result;
    }
}
