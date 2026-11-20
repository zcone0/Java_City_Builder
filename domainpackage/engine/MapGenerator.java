package domainpackage.engine;

import java.security.SecureRandom;

public class MapGenerator {
    private static final int DEFAULT_CHUNKS_WIDE = 8; // 8 * 16 = 128 tiles
    private static final int DEFAULT_CHUNKS_HIGH = 8;

    private final int chunksWide;
    private final int chunksHigh;
    private final NoiseGenerator noise;

    public MapGenerator() {
        this(new SecureRandom().nextLong(), DEFAULT_CHUNKS_WIDE, DEFAULT_CHUNKS_HIGH);
    }

    public MapGenerator(long seed, int chunksWide, int chunksHigh) {
        if (chunksWide <= 0 || chunksHigh <= 0) {
            throw new IllegalArgumentException("Chunk grid dimensions must be positive");
        }
        this.chunksWide = chunksWide;
        this.chunksHigh = chunksHigh;

        this.noise = new NoiseGenerator(seed);
        // Sync NoiseGenerator's total tile dimensions so worldScale spans the whole chunk grid
        this.noise.setWidth(chunksWide * Chunk.SIZE);
        this.noise.setHeight(chunksHigh * Chunk.SIZE);
    }

    public Chunk[][] generateChunks() {
        Chunk[][] chunks = new Chunk[chunksHigh][chunksWide];

        for (int chunkY = 0; chunkY < chunksHigh; chunkY++) {
            for (int chunkX = 0; chunkX < chunksWide; chunkX++) {
                chunks[chunkY][chunkX] = buildChunk(chunkX, chunkY);
            }
        }

        return chunks;
    }

    private Chunk buildChunk(int chunkX, int chunkY) {
        Chunk chunk = new Chunk(chunkX, chunkY);

        for (int localY = 0; localY < Chunk.SIZE; localY++) {
            int worldY = chunkY * Chunk.SIZE + localY;

            for (int localX = 0; localX < Chunk.SIZE; localX++) {
                int worldX = chunkX * Chunk.SIZE + localX;

                float height = noise.sampleTile(worldX, worldY);
                chunk.setTileFromHeight(localX, localY, height);
            }
        }

        return chunk;
    }

    public NoiseGenerator getNoiseGenerator() {
        return noise;
    }
}