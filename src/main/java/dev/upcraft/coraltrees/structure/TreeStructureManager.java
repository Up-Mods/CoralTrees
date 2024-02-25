package dev.upcraft.coraltrees.structure;

import dev.upcraft.coraltrees.CoralTreesMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = CoralTreesMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TreeStructureManager extends SimplePreparableReloadListener<Map<TreeType, List<ResourceLocation>>> {

    private static final TreeStructureManager INSTANCE = new TreeStructureManager();
    private static final String RESOURCE_PATH = String.format("structures/%s/tree/", CoralTreesMod.MODID);

    private Map<TreeType, List<ResourceLocation>> treeStructures = Map.of();

    @Override
    protected Map<TreeType, List<ResourceLocation>> prepare(ResourceManager resourceManager, ProfilerFiller profiler) {
        var map = new EnumMap<TreeType, List<ResourceLocation>>(TreeType.class);

        for (TreeType treeType : TreeType.values()) {
            var treePath = RESOURCE_PATH + treeType.getSerializedName();
            var treeFiles = resourceManager.listResources(treePath, path -> path.getPath().endsWith(".nbt")).keySet();
                map.put(treeType, treeFiles.stream().map(loc -> new ResourceLocation(loc.getNamespace(), loc.getPath().substring("structures/".length(), loc.getPath().length() - ".nbt".length()))).toList());
        }

        return map;
    }

    @Override
    protected void apply(Map<TreeType, List<ResourceLocation>> data, ResourceManager resourceManager, ProfilerFiller profiler) {
        treeStructures = data;
    }

    public List<ResourceLocation> getStructures(TreeType treeType) {
        return treeStructures.get(treeType);
    }

    public static TreeStructureManager getInstance() {
        return INSTANCE;
    }

    @SubscribeEvent
    public static void onRegisterReloadListeners(AddReloadListenerEvent event) {
        event.addListener(INSTANCE);
    }
}
