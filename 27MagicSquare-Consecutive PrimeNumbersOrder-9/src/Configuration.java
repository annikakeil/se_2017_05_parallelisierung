public enum Configuration {
    instance;

    public int maxIterationsEachThread = 1000000;
    public int matrixSize = 9;
    public int maxPrimeArea = 1000;
    public int threadCount = 4;

    // Sum of all propabilities: 1 !
    public double probShake = 0.5;
    public double probColumn = 0.17;
    public double probLine = 0.17;
    // -> Proability of SwapDiagonal = 0.16

}