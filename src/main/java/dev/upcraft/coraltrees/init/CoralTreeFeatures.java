package dev.upcraft.coraltrees.init;

import dev.upcraft.coraltrees.CoralTreesMod;
import dev.upcraft.coraltrees.feature.CoralTreeConfiguration;
import dev.upcraft.coraltrees.feature.CoralTreeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CoralTreeFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, CoralTreesMod.MODID);

    public static final RegistryObject<CoralTreeFeature> CORAL_TREE = FEATURES.register("coral_tree", () -> new CoralTreeFeature(CoralTreeConfiguration.CODEC));
}
