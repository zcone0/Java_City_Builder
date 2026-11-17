package domainpackage.engine;

import java.util.Random;

public class NoiseGenerator {
    private static final int DEFAULT_WIDTH = 128;
    private static final int DEFAULT_HEIGHT = 128;
    private static final double DEFAULT_WORLD_SCALE = 3.0;

    private long seed;
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private int octaves = 3;
    private double frequency = 1.0;
    private double worldScale = DEFAULT_WORLD_SCALE;
    private double lacunarity = 2.0;
    private double persistence = 0.2;

    private final int[] perm = new int[512];

    public NoiseGenerator(long seed) {
        setSeed(seed);
    }

    public double[][] generate() {
        double[][] values = new double[height][width];

        for (int y = 0; y < height; y++) {
            double ny = (y / (double) height) * worldScale;
            for (int x = 0; x < width; x++) {
                double nx = (x / (double) width) * worldScale;
                values[y][x] = fbm(nx, ny, octaves, frequency, lacunarity, persistence);
            }
        }

        return values;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
        int[] p = new int[256];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }

        Random random = new Random(seed);
        for (int i = p.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temporary = p[i];
            p[i] = p[j];
            p[j] = temporary;
        }
        for (int i = 0; i < perm.length; i++) {
            perm[i] = p[i & 255];
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    public int getOctaves() {
        return octaves;
    }

    public void setOctaves(int octaves) {
        if (octaves <= 0) {
            throw new IllegalArgumentException("Octaves must be positive");
        }
        this.octaves = octaves;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        if (frequency <= 0.0) {
            throw new IllegalArgumentException("Frequency must be positive");
        }
        this.frequency = frequency;
    }

    public double getWorldScale() {
        return worldScale;
    }

    public void setWorldScale(double worldScale) {
        if (worldScale <= 0.0) {
            throw new IllegalArgumentException("World scale must be positive");
        }
        this.worldScale = worldScale;
    }

    public double getLacunarity() {
        return lacunarity;
    }

    public void setLacunarity(double lacunarity) {
        if (lacunarity <= 0.0) {
            throw new IllegalArgumentException("Lacunarity must be positive");
        }
        this.lacunarity = lacunarity;
    }

    public double getPersistence() {
        return persistence;
    }

    public void setPersistence(double persistence) {
        if (persistence < 0.0) {
            throw new IllegalArgumentException("Persistence cannot be negative");
        }
        this.persistence = persistence;
    }

    public double fbm(double x, double y, int octaves, double frequency,
                      double lacunarity, double persistence) {
        double total = 0.0;
        double amplitude = 1.0;
        double maxAmplitude = 0.0;

        for (int i = 0; i < octaves; i++) {
            total += noise(x * frequency, y * frequency) * amplitude;
            maxAmplitude += amplitude;
            frequency *= lacunarity;
            amplitude *= persistence;
        }

        return (total + maxAmplitude) / (2.0 * maxAmplitude);
    }

    public double noise(double x, double y) {
        int xi = (int) Math.floor(x);
        int yi = (int) Math.floor(y);
        int X = xi & 255;
        int Y = yi & 255;
        double xf = x - xi;
        double yf = y - yi;

        double n00 = grad(perm[perm[X] + Y], xf, yf);
        double n10 = grad(perm[perm[X + 1] + Y], xf - 1, yf);
        double n01 = grad(perm[perm[X] + Y + 1], xf, yf - 1);
        double n11 = grad(perm[perm[X + 1] + Y + 1], xf - 1, yf - 1);

        double u = fade(xf);
        double v = fade(yf);
        double bottom = lerp(n00, n10, u);
        double top = lerp(n01, n11, u);
        return lerp(bottom, top, v);
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private double grad(int hash, double dx, double dy) {
        return switch (hash & 7) {
            case 0 -> dx + dy;
            case 1 -> -dx + dy;
            case 2 -> dx - dy;
            case 3 -> -dx - dy;
            case 4 -> dx;
            case 5 -> -dx;
            case 6 -> dy;
            default -> -dy;
        };
    }
}
