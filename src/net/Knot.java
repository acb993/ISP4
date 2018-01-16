package net;

import java.util.ArrayList;
import java.util.List;

public class Knot {
    private ArrayList<Integer> wertebereich;
    private String name;

    public Knot(ArrayList<Integer> werte, String name)
    {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasAnnahme(){
        return wertebereich.size()==1;
    }
}
