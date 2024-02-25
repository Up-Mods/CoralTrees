package dev.upcraft.coraltrees;

import dev.upcraft.coraltrees.init.CoralTreeBlocks;
import dev.upcraft.coraltrees.init.CoralTreeConfiguredFeatures;
import dev.upcraft.coraltrees.init.CoralTreeFeatures;
import dev.upcraft.coraltrees.init.CoralTreeStructureProcessorTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CoralTreesMod.MODID)
public class CoralTreesMod {

    public static final String MODID = "coraltrees";

    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CoralTreeBlocks.COROAK_SAPLING.get());
        }
    };

    public CoralTreesMod() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        CoralTreeBlocks.BLOCKS.register(bus);
        CoralTreeBlocks.ITEMS.register(bus);
        CoralTreeFeatures.FEATURES.register(bus);
        CoralTreeConfiguredFeatures.FEATURES.register(bus);
        CoralTreeStructureProcessorTypes.PROCESSOR_TYPES.register(bus);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
}
