public class Polar2DAdapter implements IVector, IPolar2D{

    private Vector2D srcVector;
    public Polar2DAdapter(Vector2D srcVector){
        this.srcVector = srcVector;
    }
    @Override
    public double abs(){
        return this.srcVector.abs();
    }

    public double getAngle(){
        double [] comp = srcVector.getComponents();
        double angle = Math.atan2(comp[0],comp[1])*180/Math.PI;
        return angle;
    }

    public double cdot (IVector iVector){
        return this.srcVector.cdot(iVector);
    }
    public double[] getComponents(){
        return this.srcVector.getComponents();
    }

}
