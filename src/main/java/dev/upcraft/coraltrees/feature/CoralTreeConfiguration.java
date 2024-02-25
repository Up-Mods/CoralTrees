package dev.upcraft.coraltrees.feature;

import com.mojang.serialization.Codec;
import dev.upcraft.coraltrees.structure.TreeType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class CoralTreeConfiguration implements FeatureConfiguration {

    private final TreeType type;

    public static final Codec<CoralTreeConfiguration> CODEC = TreeType.CODEC.fieldOf("type").xmap(CoralTreeConfiguration::new, CoralTreeConfiguration::getTreeType).codec();

    public CoralTreeConfiguration(TreeType type) {
        this.type = type;
    }

    public TreeType getTreeType() {
        return type;
    }
}
