public class Vector3DInheritance extends Vector2D {
    private double z;

    public Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z =z;
    }
    @Override
    public double abs(){
        double[] compontents = super.getComponents();
        double mod = Math.sqrt(Math.pow(compontents[0], 2)+ Math.pow(compontents[1], 2)+ Math.pow(z, 2));
        return mod;
    }
    @Override
    public double cdot (IVector iVector){
        double[] components1 = getComponents();
        double[] components2 = iVector.getComponents();
        double scalar = components1[0] * components2[0] + components1[1] * components2[1] + components1[2] * components2[2];
        return scalar;

    }
    @Override
    public double[] getComponents() {
        double[] temp = super.getComponents();
        double[] components = new double[]{temp[0], temp[1], z};
        return components;
    }

    public Vector3DDecorator cross(IVector iVector){
        double[] comp1 = getComponents();
        double[] comp2 = iVector.getComponents();

        double x = comp1[1] * comp2[2] - comp1[2] * comp2[1];
        double y = comp1[2] * comp2[0] - comp1[0] * comp2[2];
        double z = comp1[0] * comp2[1] - comp1[1] * comp2[0];
        Vector3DDecorator crossVector = new Vector3DDecorator(x,y,z);

        return crossVector;
    }
    public IVector getSrcV(){
        double[] components = getComponents();
        IVector srcVector = new Vector2D(components[0], components[1]);
        return srcVector;
    }


}
