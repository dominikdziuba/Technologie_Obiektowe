package memento;

import general.*;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private List<Single> people;

    public Memento(Population pop) {
        this.people = new ArrayList<>(pop.getPeople());
    }

    public List<Single> getPeople() {
        return people;
    }
}
