package domainpackage.engine;

public enum Biome {
    OCEAN(false, 0.0f, 0x2E86C1),
    BEACH(true,  1.5f, 0xF4D03F),
    PLAINS(true, 1.0f, 0x58D68D),
    FOREST(true, 2.0f, 0x196F3D);

    private final boolean buildable;
    private final float roadCostMultiplier;
    private final int debugColorHex;

    Biome(boolean buildable, float roadCostMultiplier, int debugColorHex) {
        this.buildable = buildable;
        this.roadCostMultiplier = roadCostMultiplier;
        this.debugColorHex = debugColorHex;
    }

    public boolean isBuildable() { return buildable; }
    public float getRoadCostMultiplier() { return roadCostMultiplier; }
    public int getDebugColorHex() { return debugColorHex; }

    public static Biome fromHeight(float height) {
        if (height < 0.42f) return OCEAN;
        if (height < 0.45f) return BEACH;
        if (height < 0.60f) return PLAINS;
        return FOREST;
    }
}