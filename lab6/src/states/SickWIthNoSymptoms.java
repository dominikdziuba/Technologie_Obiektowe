package states;

import general.Single;

public class SickWIthNoSymptoms implements ISick {
    private int numberOf = 0;

    public int getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }

    @Override
    public void handle(Single person) {
        if(this.numberOf >= 500){
            if(Math.random() > 0.5 || this.numberOf >=750) person.setStatus(new Resistant());
        }
        this.numberOf++;
    }


}
