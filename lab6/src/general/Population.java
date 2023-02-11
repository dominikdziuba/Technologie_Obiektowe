package general;

import java.util.ArrayList;
import java.util.List;
import states.*;
import view.*;
public class Population {
    private final List<Single> people;
    private final Chart chart;

    public Population(Chart chart) {
        this.people = new ArrayList<>();
        this.chart = chart;
    }

    public List<Single> getPeople() {
        return people;
    }

    public void addPerson(Single person){
        this.people.add(person);
    }

    public void deletePerson(Single person){
        this.people.remove(person);
    }

    public void runPopulation(boolean checkResistance){
        double x;
        double y;
        double rand = Math.random();
        if(rand < 0.25){
            x = 0.0;
            y = Math.random() * (chart.getHeight() * 0.01);
        } else if(rand < 0.5){
            x = Math.random() * (chart.getWidth() * 0.01);
            y = 0.0;
        }else if (rand < 0.75){
            x = chart.getWidth() * 0.01;
            y = Math.random() * (chart.getHeight() * 0.01);
        } else{
            x = Math.random() * (chart.getWidth() * 0.01);
            y = chart.getHeight() * 0.01;
        }
        IState status = new Healthy();
        if(Math.random() < 0.1) {
            if(Math.random() < 0.5) status = new SickWithSymptoms();
            else status = new SickWIthNoSymptoms();
        }else if(checkResistance) if(Math.random() > 0.5) status = new Resistant();

        this.addPerson(new Single(x, y, status));
    }


    public void handleReturnOnChart(Single person){
        double x = person.getX();
        double y = person.getY();
        if(x > this.chart.getWidth() * 0.01) person.setX(x - 0.2);
        else person.setX(x + 0.2);

        if(y > this.chart.getHeight() *0.01) person.setY(y - 0.2);
        else person.setY(y + 0.2);

    }

    public Single createNewCordsPop(){
        for(Single person : people){
            person.createNewCords();
            if(!this.chart.atChart(person)){
                if(Math.random() < 0.5) {
                    return person;
                }
                this.handleReturnOnChart(person);
            }
        }
        return null;
    }

    public void handleDisease(){
        List<Single> sickPatients = new ArrayList<>();
        for(Single person : this.people){
            if(person.getStatus() instanceof ISick) {
                sickPatients.add(person);
            }
        }
        for(Single person: this.people){
            if(person.getStatus() instanceof Healthy){
                boolean nearness = false;
                for(Single sickPerson : sickPatients){
                    if(person.getDistance(sickPerson) <= 2.0) {
                        nearness = true;
                        if(sickPerson.getStatus() instanceof SickWithSymptoms) ((Healthy) person.getStatus()).setSymptoms(true);
                        person.getStatus().handle(person);
                        break;
                    }
                }
                if(!nearness){
                    ((Healthy) person.getStatus()).setNumberOf(0);
                }
            }
        }
    }

    public void handleImmunity(){
        for(Single person : this.people){
            if(person.getStatus() instanceof ISick) {
                person.getStatus().handle(person);
            }
        }
    }
}
