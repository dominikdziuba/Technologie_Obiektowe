package vector;

public class Vector implements IVector {
    private final double x;
    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double abs(double x1, double y1) {
        return Math.sqrt(Math.pow(x1 - this.x, 2) + Math.pow(y1 - this.y, 2));
    }

    @Override
    public double[] getComponents() {
        return new double[]{this.x, this.y};
    }
}
