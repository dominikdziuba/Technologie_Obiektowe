

public class Main {
    static Vector2D vec2D = new Vector2D(3, 0 );
    static Vector2D vec2D2 = new Vector2D(2, -3);
    static Vector3DDecorator vec3D = new Vector3DDecorator(-1,3,4);
    static Polar2DAdapter adap = new Polar2DAdapter(vec2D);
    static Polar2DAdapter adap2 = new Polar2DAdapter(vec2D2);


    public static void main(String[] args) {
        show();
        scalar();
        cross();
    }

    public static void show(){
        double[] comp = adap.getComponents();
        double abs1 = adap.abs();
        double angle1 = adap.getAngle();

        double[] comp2 = adap2.getComponents();
        double abs2 = adap2.abs();
        double angle2 = adap2.getAngle();

        double[] comp3 = vec3D.getComponents();

        System.out.println("Wektor 1 \nWspółrzędne kartezjańskie = ["+comp[0]+','+comp[1]+"], Współrzędne biegunowe = ["+Math.round(abs1*100)/100+','+Math.round(angle1*100)/100 + ']');
        System.out.println("Wektor 2 \nWspółrzędne kartezjańskie = ["+comp2[0]+','+comp2[1]+"], Współrzędne biegunowe = ["+Math.round(abs2*100)/100 + ',' + Math.round(angle2*100)/100 + ']');
        System.out.println("Wektor 3 \nWspółrzędne kartezjańskie = ["+comp3[0]+','+comp3[1]+','+comp3[2]+']');
    }

    public static void scalar(){
        double scalar12 = vec2D.cdot(vec2D2);
        double scalar31 = vec3D.cdot(vec2D);
        double scalar32 = vec3D.cdot(vec2D2);

        System.out.println("\nIloczyn skalarny: \nwektory 1 i 2 = " + scalar12+"\nwektory 3 i 1 = "+scalar31+"\nwektory 3 i 2 = "+scalar32);

    }
    public static void cross(){
        Vector3DDecorator cross31 = vec3D.cross(vec2D);
        double[] comp31 = cross31.getComponents();
        Vector3DDecorator cross32 = vec3D.cross(vec2D2);
        double[] comp32 = cross32.getComponents();

        System.out.println("\nIloczyn wektorowy: \nwektory 3 i 1 = ["+ comp31[0] +','+ comp31[1] +','+ comp31[2]+']');
        System.out.println("wektory 3 i 2 = [" + comp32[0] + ',' +  comp32[1] +','  +comp32[2]+']');
    }
}
