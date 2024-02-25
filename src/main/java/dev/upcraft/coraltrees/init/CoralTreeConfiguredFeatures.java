package dev.upcraft.coraltrees.init;

import dev.upcraft.coraltrees.CoralTreesMod;
import dev.upcraft.coraltrees.feature.CoralTreeConfiguration;
import dev.upcraft.coraltrees.structure.TreeType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;

public class CoralTreeConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, CoralTreesMod.MODID);

    static {
        for (var type : TreeType.values()) {
            FEATURES.register("tree/" + type.getSerializedName(), () -> new ConfiguredFeature<>(CoralTreeFeatures.CORAL_TREE.get(), new CoralTreeConfiguration(type)));
        }
    }
}
