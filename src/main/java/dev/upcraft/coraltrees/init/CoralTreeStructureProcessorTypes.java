package dev.upcraft.coraltrees.init;

import dev.upcraft.coraltrees.CoralTreesMod;
import dev.upcraft.coraltrees.structure.TreeGrowthProcessor;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CoralTreeStructureProcessorTypes {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSOR_TYPES = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, CoralTreesMod.MODID);

    public static final RegistryObject<StructureProcessorType<TreeGrowthProcessor>> TREE_GROWTH_PROCESSOR = PROCESSOR_TYPES.register("tree_growth_processor", () -> () -> TreeGrowthProcessor.CODEC);
}
