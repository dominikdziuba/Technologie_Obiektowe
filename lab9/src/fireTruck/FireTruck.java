package fireTruck;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FireTruck {
    private int idTruck;
    private boolean status;

    public FireTruck(){
        idFireTrucks id = new idFireTrucks();
        this.idTruck = id.getId();
        this.status=true;
    }

    public int getIdTruck(){
        return idTruck;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean state){
        status = state;
    }

    public void make(boolean authAlarm, int idEvent){
        this.setStatus(false);
        Random approach = new Random();
        try{
            TimeUnit.SECONDS.sleep(approach.nextInt(3));
            if(authAlarm){
                System.out.println("Jednostka " + getIdTruck() + " potwierdziała prawdziwosć zdarzenia: "+ idEvent +'.');
                TimeUnit.SECONDS.sleep(approach.nextInt(5,25) );
            }
            else
                System.out.println("Jednostka " + getIdTruck() + " stwierdza, że zdarzenie "+ idEvent +" jest fałszywe.");

            TimeUnit.SECONDS.sleep(approach.nextInt(3));

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        this.setStatus(true);
    }
}
