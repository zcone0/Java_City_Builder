package domainpackage.engine;

public class Chunk {
    public static final int SIZE = 16;

    private final int chunkX;
    private final int chunkY;

    // index i in both arrays describes the same tile
    private final float[] heights = new float[SIZE * SIZE];
    private final Biome[] biomes  = new Biome[SIZE * SIZE];

    public Chunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }

    private int toIndex(int localX, int localY) {
        return localY * SIZE + localX;
    }

    public void setTileFromHeight(int localX, int localY, float height) {
        int idx = toIndex(localX, localY);
        heights[idx] = height;
        biomes[idx]  = Biome.fromHeight(height);
    }

    public float getHeight(int localX, int localY) {
        return heights[toIndex(localX, localY)];
    }

    public Biome getBiome(int localX, int localY) {
        return biomes[toIndex(localX, localY)];
    }

    public boolean canBuildAt(int localX, int localY) {
        return getBiome(localX, localY).isBuildable();
    }

    public int getChunkX() { return chunkX; }
    public int getChunkY() { return chunkY; }
}
