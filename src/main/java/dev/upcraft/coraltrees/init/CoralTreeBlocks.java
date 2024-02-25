package dev.upcraft.coraltrees.init;

import dev.upcraft.coraltrees.CoralTreesMod;
import dev.upcraft.coraltrees.block.CoroakFanBlock;
import dev.upcraft.coraltrees.block.CoroakWallFanBlock;
import dev.upcraft.coraltrees.block.WaterloggedSaplingBlock;
import dev.upcraft.coraltrees.structure.CoralTreeGrower;
import dev.upcraft.coraltrees.structure.TreeType;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CoralTreeBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CoralTreesMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CoralTreesMod.MODID);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> factory) {
        return register(name, factory, true);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> factory, boolean registerItem) {
        var obj = BLOCKS.register(name, factory);
        if (registerItem) {
            ITEMS.register(name, () -> new BlockItem(obj.get(), new Item.Properties().tab(CoralTreesMod.CREATIVE_TAB)));
        }
        return obj;
    }

    public static final RegistryObject<Block> COROAK_SAPLING = register("coroak_sapling", () -> new WaterloggedSaplingBlock(new CoralTreeGrower(TreeType.COROAK), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> COROAK_LOG = register("coroak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.CORAL_BLOCK)));
    public static final RegistryObject<Block> COROAK_WOOD = register("coroak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.CORAL_BLOCK)), true);
    public static final RegistryObject<Block> COROAK_LEAVES = register("coroak", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN).strength(0.2F).isSuffocating((pState, pLevel, pPos) -> false).sound(SoundType.CORAL_BLOCK)));
    public static final RegistryObject<Block> COROAK_PLANKS = register("coroak_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.CORAL_BLOCK)));
    public static final RegistryObject<Block> COROAK_FAN = register("coroak_fan", () -> new CoroakFanBlock(Block.Properties.copy(Blocks.BUBBLE_CORAL_FAN)), false);
    public static final RegistryObject<Block> COROAK_WALL_FAN = register("coroak_wall_fan", () -> new CoroakWallFanBlock(Block.Properties.copy(Blocks.BUBBLE_CORAL_WALL_FAN).lootFrom(COROAK_FAN)), false);

    public static final RegistryObject<Item> COROAK_FAN_ITEM = ITEMS.register("coroak_fan", () -> new StandingAndWallBlockItem(COROAK_FAN.get(), COROAK_WALL_FAN.get(), new Item.Properties().tab(CoralTreesMod.CREATIVE_TAB)));
}
