package general;

import states.IState;
import vector.IVector;
import vector.Vector;


public class Single {
    private double x;
    private double y;
    private IState status;


    public Single( double x, double y,IState status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setStatus(IState status) {
        this.status = status;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public IState getStatus() {
        return status;
    }
    public double getDistance(Single person){
        IVector vector = new Vector(this.x, this.y);
        return vector.abs(person.getX(), person.getY());
    }

    private IVector randomizeMovement(){
        double variableX = (-0.1) + Math.random() * (0.1 - (-0.1));
        double variableY = (-0.1) + Math.random() * (0.1 - (-0.1));
        double newX = this.x + variableX;
        double newY = this.y + variableY;
        return new Vector(newX, newY);
    }
    public void createNewCords(){
        IVector vector = this.randomizeMovement();
        if(vector.abs(this.x, this.y) <= 0.1){
            this.x = vector.getComponents()[0];
            this.y = vector.getComponents()[1];
        }else{
            this.createNewCords();
        }
    }
}
