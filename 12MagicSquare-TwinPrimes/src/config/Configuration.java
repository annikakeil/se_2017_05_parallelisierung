package config;

public enum Configuration {
    instance;

    public int maxPrimeNumber = 1000;

    public int matrixWidth = 4;
    public int matrixHeight = 4;
    private int threadsCount = 1000;
    private int threadIterationCount = 1000000;
}