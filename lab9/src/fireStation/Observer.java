package fireStation;

import incidents.Incident;

public interface Observer {
    public void update(Incident event, int counterOfCars);

}
