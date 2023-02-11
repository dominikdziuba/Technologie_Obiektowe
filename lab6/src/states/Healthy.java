package states;

import general.Single;

public class Healthy implements INotResistant {
    private int numberOf = 0;
    private boolean symptoms = false;

    public void setSymptoms(boolean symptoms) {
        this.symptoms = symptoms;
    }

    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }

    public int getNumberOf() {
        return numberOf;
    }
    @Override
    public void handle(Single person) {
        if(this.getNumberOf() >= 75) {
            if (this.symptoms) {
                this.changestatus(person);
            }
            else{
                if (Math.random() > 0.5) {
                    this.changestatus(person);
                }
            }
        }
        this.numberOf++;
    }

    private void changestatus(Single person){
        if (Math.random() > 0.5) person.setStatus(new SickWithSymptoms());
        else person.setStatus(new SickWIthNoSymptoms());
    }
}
