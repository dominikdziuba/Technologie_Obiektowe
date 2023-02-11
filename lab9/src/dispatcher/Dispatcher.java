package dispatcher;

import fireStation.FireStation;
import strategy.LocalDangerStrategy;
import strategy.FireStrategy;
import incidents.Incident;
import fireStation.Observer;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Dispatcher implements Subject{
    private final List<Observer> observatorsCollection;

    public Dispatcher() {
        this.observatorsCollection = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observatorsCollection.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int observerIndex = observatorsCollection.indexOf(observer);
        if (observerIndex >= 0) {
            observatorsCollection.remove(observerIndex);
        }
    }

    @Override
    public void notifyObserver(Observer observer, Incident event) {

    }

    @Override
    public boolean notifyAll(Incident incident) {
        HashMap<FireStation, Double> distance = new HashMap<>();

        for(Observer observer : observatorsCollection){
            distance.put((FireStation) observer, getDistance(observer, incident));
        }

        sorter(distance);
        int carsNeeded = operation(incident);
        System.out.println(incident.action() ?"Dostaliśmy zgłoszenie PZ. Id zgłoszenia: " + incident.getId() + " wysyłamy " + carsNeeded + " wozy." : "Dostaliśmy zgłoszenie MZ. Id zgłoszenia: " + incident.getId() + " wysyłamy " + carsNeeded + " wozy.");

        for(FireStation fireStation : distance.keySet()){
            if(fireStation.freeTruckCounter()>= carsNeeded){
                fireStation.update(incident,carsNeeded);
                carsNeeded = 0;
                break;
            }else{
                carsNeeded -= fireStation.freeTruckCounter();
                fireStation.update(incident,fireStation.freeTruckCounter());
            }
        }

        return carsNeeded == 0;
    }

    public int operation(Incident event) {
        if(event.getType().action()){
            return new FireStrategy().operation();
        }else{
            return new LocalDangerStrategy().operation();
        }
    }

    private double getDistance(Observer observer, Incident event){
        FireStation fireStation = (FireStation) observer;

        return sqrt(pow((fireStation.getHeight() - event.getH()),2) + pow((fireStation.getWidth() - event.getW()),2));
    }

    public void sorter(HashMap<FireStation, Double> map)
    {
        List<Map.Entry<FireStation, Double> > list = new LinkedList<Map.Entry<FireStation, Double> >(map.entrySet());

        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

        HashMap<FireStation, Double> tempMap = new LinkedHashMap<FireStation, Double>();
        for (Map.Entry<FireStation, Double> aa : list) {
            tempMap.put(aa.getKey(), aa.getValue());
        }
    }


}
