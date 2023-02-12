package incidents;

import types.FireDanger;
import types.IType;
import types.LocalDanger;

import java.util.Random;

public class Incident implements IType {
    private final int id;
    private final IType type;
    private boolean authentication = true;
    private final double h;
    private final double w;


    public int getId() {
        return id;
    }

    public IType getType(){
        return type;
    }

    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }


    public Incident(){
        double x1 = 49.95855;
        double x2 = 50.15456;
        double y1 = 19.68829;
        double y2 = 20.02470;

        IdIncident id = new IdIncident();
        this.id = id.getId();

        Random typeDanger = new Random();

        if(typeDanger.nextInt(10) < 6)
            this.type = new LocalDanger();
        else
            this.type = new FireDanger();

        Random authAlarm = new Random();
        if (authAlarm.nextInt(100) <= 5)
            this.authentication = false;
        Random random = new Random();
        this.h = random.nextDouble(x1,x2);
        this.w = random.nextDouble(y1,y2);

    }
    @Override
    public boolean action() {
        return this.getType().action();
    }

    public boolean getAlarmState(){
        return authentication;
    }

}
