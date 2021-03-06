package net;

import core.ConstType;

import java.util.ArrayList;

public class BinaerConstraint extends Constraint {
    private Knot linkerWert;
    private Knot rechterWert;

    private ConstType type;

    public BinaerConstraint(Knot linkerWert, Knot rechterWert, ConstType type) {
        this.linkerWert = linkerWert;
        this.rechterWert = rechterWert;
        this.type = type;
    }

    public Boolean check(int firstValue, int secondValue) {
        Boolean result = false;

        switch (type) {
            case GLEICH: result = gleich(firstValue,secondValue);
                break;
            case NACHBAR: result = nachbar(firstValue,secondValue);
                break;
            case LINKERNACHBAR: result = linkerNachbar(firstValue,secondValue);
                break;
            case RECHTERNACHBAR: result = rechterNachbar(firstValue,secondValue);
                break;
            case UNGLEICH: result = ungleich(firstValue,secondValue);
        }

        return result;
    }

    private Boolean ungleich(int firstValue, int secondValue) {
        Boolean result = false;
        result = (firstValue-secondValue)!= 0;
        return result;
    }

    private Boolean rechterNachbar(int firstValue, int secondValue) {
        Boolean result = false;
        result = (firstValue-secondValue)==1;
        return result;
    }

    private Boolean linkerNachbar(int firstValue, int secondValue) {
        Boolean result = false;
        result = (firstValue-secondValue)==-1;
        return result;
    }

    private Boolean nachbar(int firstValue, int secondValue) {
        Boolean result = false;
        result = linkerNachbar(firstValue,secondValue)||rechterNachbar(firstValue,secondValue);
        return result;
    }

    private Boolean unaer(int firstValue, int secondValue) {
        Boolean result = false;
        return result;
    }

    private Boolean gleich(int firstValue, int secondValue) {
        Boolean result = false;
        result= firstValue==secondValue;
        return result;
    }

    public static ArrayList<Constraint> makeAllDifferent(ArrayList<Knot> knoten) {
        ArrayList<Constraint> result = new ArrayList<>();
        for (Knot knot1 : knoten) {
            for (Knot knot2 : knoten) {
                if (!(knot1.equals(knot2))) {
                    result.add(new BinaerConstraint(knot1, knot2, ConstType.UNGLEICH));
                }
            }
        }
      return result;
    }

    public Knot getLinkerWert() {
        return linkerWert;
    }
    public Knot getRechterWert(){
        return rechterWert;
    }
    public ConstType getType() {
        return type;
    }

    public String toString(){
        return linkerWert+"->"+rechterWert+":"+type;
    }
}
