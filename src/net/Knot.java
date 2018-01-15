package net;

import java.util.ArrayList;
import java.util.List;

public class Knot {
    private ArrayList<Integer> wertebereich;

    public Knot(ArrayList<Integer> werte){
        wertebereich = werte;
    }

    public List<Integer> getWertebereich() {
        return wertebereich;
    }

    public void removeValue(Integer value){
        wertebereich.remove(value);
    }
    public void annahme(Integer value){
        wertebereich.clear();
        wertebereich.add(value);
    }
}
