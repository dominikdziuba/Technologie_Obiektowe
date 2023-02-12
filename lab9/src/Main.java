import fireStation.FireStation;
import incidents.Incident;
import dispatcher.Dispatcher;

import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        FireStation fr1 = new FireStation(50.06002, 19.94291, 1);
        FireStation fr2 = new FireStation(50.03381, 19.93737, 2);
        FireStation fr3 = new FireStation(50.08496, 19.86345, 3);
        FireStation fr4 = new FireStation(50.03785, 20.00575, 4);
        FireStation fr5 = new FireStation(50.09234, 19.92238, 5);
        FireStation fr6 = new FireStation(50.01645, 20.01701, 6);
        FireStation fr7 = new FireStation(50.09411, 19.97754, 7);
        FireStation fr8 = new FireStation(50.07841, 20.03662, 8);
        FireStation fr9 = new FireStation(49.97218, 19.79603, 9);
        FireStation fr10 = new FireStation(50.08267, 19.81388, 10);

        Dispatcher Dispatcher = new Dispatcher();
        Dispatcher.addObserver(fr1);
        Dispatcher.addObserver(fr2);
        Dispatcher.addObserver(fr3);
        Dispatcher.addObserver(fr4);
        Dispatcher.addObserver(fr5);
        Dispatcher.addObserver(fr6);
        Dispatcher.addObserver(fr7);
        Dispatcher.addObserver(fr8);
        Dispatcher.addObserver(fr9);
        Dispatcher.addObserver(fr10);

        int timer = 0;

        while (true) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("\n");
            timer += 5;
            if (!Dispatcher.notifyAll(new Incident())) {
                System.out.println("WEZWAĆ WSPARCIE. ZABRAKŁO POJAZDÓW W JEDNOSTKACH.");
                break;
            }
            if (timer >= 60) {
                System.out.println("\nKONIEC SYMULACJI");
                break;
            }
        }
    }
}
