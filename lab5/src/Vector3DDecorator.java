public class Vector3DDecorator implements IVector{
    private IVector srcVector;
    private double z;

    public Vector3DDecorator(double x, double y, double z){
        srcVector = new Vector2D(x, y);
        this.z = z;
    }

    @Override
    public double abs(){
        double[] comp = srcVector.getComponents();
        double mod = Math.sqrt(Math.pow(comp[0], 2)+ Math.pow(comp[1], 2)+ Math.pow(z, 2));
        return mod;
    }

    @Override
    public double cdot (IVector iVector){
        double[] comp1 = getComponents();
        double[] comp2 = iVector.getComponents();
        double scalar = comp1[0] * comp2[0] + comp1[1] * comp2[1] + comp1[2] * comp2[2];
        return scalar;

    }

    @Override
    public double[] getComponents() {
        double[] temp = srcVector.getComponents();
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

    public  IVector getSrcV(){
        return srcVector;
    }

    }
