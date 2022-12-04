public class Vector2D implements IVector {
    private double x;
    private double y;


    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double[] getComponents(){
        double [] components = new double[3];
        components[0] = x;
        components[1] = y;
        components[2] = 0;
        return components;
    }

    @Override
    public double abs() {
        double mod = Math.sqrt(x * x + y * y);
        return mod;
    }

    @Override
    public double cdot(IVector iVector){
        double [] vec = iVector.getComponents();
        double scalar = x*vec[0]+ y*vec[1];
        return scalar;
    }


}
