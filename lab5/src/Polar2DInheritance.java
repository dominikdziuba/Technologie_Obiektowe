public class Polar2DInheritance extends Vector2D {
    public Polar2DInheritance(double x, double y){
        super(x,y);
    }

    public double getAngle(){
        double [] comp = super.getComponents();
        double angle = Math.atan2(comp[0],comp[1])*180/Math.PI;
        return angle;
    }

}
