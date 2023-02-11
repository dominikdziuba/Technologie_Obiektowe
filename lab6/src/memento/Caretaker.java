package memento;

import java.util.ArrayList;
import java.util.List;
import general.Population;

public class Caretaker {
    private List<Memento> mementos = new ArrayList<>();
    public Caretaker() {
    }

    public List<Memento> getMementos() {
        return mementos;
    }

    public void addMemento(Population pop){
        mementos.add(new Memento(pop));
    }

    public Memento getMemento(int index){
        return mementos.get(index);
    }
}
