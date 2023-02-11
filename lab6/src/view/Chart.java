package view;

import general.Single;

public class Chart {
    private final int h;
    private final int w;
    public Chart(int height, int width) {
        this.h = height;
        this.w = width;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }


    public boolean atChart(Single person){
        int x = (int)(person.getX() * 100);
        int y = (int)(person.getY() * 100);

        if(x < this.w && x > 0 && y < this.h && y > 0) return true;

        return false;
    }


}
