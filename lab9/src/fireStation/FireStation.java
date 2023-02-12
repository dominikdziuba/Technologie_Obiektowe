package fireStation;

import java.util.ArrayList;
import java.util.List;

import incidents.Incident;
import fireTruck.FireTruck;

public class FireStation implements Observer {

    private int id;
    private double height;
    private  double width;
    private List<FireTruck> fireTrucks;


    public FireStation(double h, double w, int id){
        this.id=id;
        this.height = h;
        this.width = w;
        this.fireTrucks = new ArrayList<>();
        for(int i=0; i<5; i++){
            fireTrucks.add(new FireTruck());
        }
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
    public int freeTruckCounter(){
        int counter = 0;
        for (FireTruck fireTruck : fireTrucks){
            if(fireTruck.getStatus())
                counter+=1;
        }
        return counter;
    }

    @Override
    public void update(Incident event, int counter){
        boolean auth = event.getAlarmState();
        for (FireTruck fireTruck:fireTrucks) {
            if (counter == 0)
                break;
            if(fireTruck.getStatus()) {
                fireTruck.setStatus(false);
                counter -= 1;
                System.out.println("Jednostka "+ fireTruck.getIdTruck() + " została oddelegowana do zgłoszenia " + event.getId() + '.');
                new Thread(() -> fireTruck.make(auth, event.getId())).start();

            }
        }
    }
}
