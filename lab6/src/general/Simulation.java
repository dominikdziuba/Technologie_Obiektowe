package general;

import XMLHandler.XML;
import view.*;
import states.*;
import memento.*;
public class Simulation {
    private int n;
    private int m;
    private int initialPeopleNumber;
    private final Box box;
    private final Chart chart;
    private static Population people;
    private final static Caretaker caretaker = new Caretaker();
    private final static XML xml = new XML();

    public Simulation(int n, int m, int initialPeopleNumber) {
        this.n = n;
        this.m = m;
        this.initialPeopleNumber = initialPeopleNumber;
        this.box = new Box(n , m);
        this.chart = new Chart(m, n);
    }

    public Simulation(int n, int m, String path){
        this.n = n;
        this.m = m;
        this.box = new Box(n , m);
        this.chart = new Chart(m, n);
        this.people = new Population(chart);
        new XML().openFile(path);

    }

    public static Population getPeople() {
        return people;
    }

    public void beginSimulation(boolean checkResistance){

        if(people == null) {
            people = new Population(chart);
            for (int i = 0; i < initialPeopleNumber; i++) {
                double x = Math.random() * (m * 0.01);
                double y = Math.random() * (n * 0.01);
                IState status = new Healthy();
                if (checkResistance == true) {
                    if (Math.random() > 0.5) status = new Resistant();
                }
                people.addPerson(new Single(x, y, status));
            }
        }

        while(true) {
            box.displayPopulation(people);
            try {
                Thread.sleep( 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Single personToDelete = people.createNewCordsPop();
            if(personToDelete != null) {
                people.deletePerson(personToDelete);
                people.runPopulation(checkResistance);
            }

            people.handleImmunity();
            people.handleDisease();

        }
    }

    public static void saveToFile(){
        caretaker.addMemento(people);
        xml.saveFile(caretaker.getMemento(caretaker.getMementos().size() - 1));
    }

}
