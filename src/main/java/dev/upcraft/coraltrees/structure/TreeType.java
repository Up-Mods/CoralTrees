package dev.upcraft.coraltrees.structure;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum TreeType implements StringRepresentable {
    COROAK("coroak");

    private final String name;

    TreeType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public static final Codec<TreeType> CODEC = StringRepresentable.fromEnum(TreeType::values);
}
