package dev.upcraft.coraltrees.init;

import dev.upcraft.coraltrees.CoralTreesMod;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CoralTreeTags {

    public static class Blocks {

        public static final TagKey<Block> COROAK_PLANTABLE = TagKey.create(Registry.BLOCK_REGISTRY, CoralTreesMod.id("coroak_plantable"));
    }
}
