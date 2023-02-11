package dispatcher;

import fireStation.Observer;
import incidents.Incident;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver(Observer o, Incident incident);
    public boolean notifyObservers(Incident incident);
}
