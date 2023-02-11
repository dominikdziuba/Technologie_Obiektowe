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
    private final List<Observer> observers;

    public Dispatcher() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int observerIndex = observers.indexOf(observer);
        if (observerIndex >= 0) {
            observers.remove(observerIndex);
        }
    }

    @Override
    public void notifyObserver(Observer o, Incident event) {

    }

    @Override
    public boolean notifyObservers(Incident incident) {
        HashMap<FireStation, Double> distance = new HashMap<>();

        for(Observer observer : observers){
            distance.put((FireStation) observer, getDist(observer, incident));
        }

        sortByValue(distance);
        int neededCars = doOperation(incident);

        System.out.println(incident.action() ?"Dostaliśmy zgłoszenie PZ. Id zgłoszenia: " + incident.getId() + " wysyłamy " + neededCars + " wozy." : "Dostaliśmy zgłoszenie MZ. Id zgłoszenia: " + incident.getId() + " wysyłamy" + neededCars + " wozy.");

        for(FireStation jrg : distance.keySet()){
            if(jrg.freeTruckCounter()>= neededCars){
                jrg.update(incident,neededCars);
                neededCars = 0;
                break;
            }else{
                neededCars -= jrg.freeTruckCounter();
                jrg.update(incident,jrg.freeTruckCounter());
            }
        }

        return neededCars == 0;
    }

    private double getDist(Observer observer, Incident event){
        FireStation jrg = (FireStation) observer;

        return sqrt(pow((jrg.getHeight() - event.getH()),2) + pow((jrg.getWidth() - event.getW()),2));
    }

    public void sortByValue(HashMap<FireStation, Double> hm)
    {
        List<Map.Entry<FireStation, Double> > list
                = new LinkedList<Map.Entry<FireStation, Double> >(
                hm.entrySet());

        Collections.sort(list, (i1, i2) -> i1.getValue().compareTo(i2.getValue()));

        HashMap<FireStation, Double> temp
                = new LinkedHashMap<FireStation, Double>();
        for (Map.Entry<FireStation, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
    }

    public int doOperation(Incident event) {
        if(event.getType().action()){
            return new FireStrategy().operation();
        }else{
            return new LocalDangerStrategy().operation();
        }
    }
}
