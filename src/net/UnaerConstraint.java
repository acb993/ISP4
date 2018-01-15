package net;

import com.sun.jdi.Value;
import core.ConstType;

public class UnaerConstraint extends Constraint{
    private Knot knoten;
    private ConstType type;
    private Integer wert;

    public UnaerConstraint(Knot knoten, ConstType type, Integer wert){
    this.knoten=knoten;
    this.type=type;
    this.wert = wert;
    }

    public Knot getKnoten() {
        return knoten;
    }

    public ConstType getType() {
        return type;
    }

    public boolean check(Integer wert) {
        boolean result = false;
        switch (type) {
            case GLEICH:
                result = gleich(wert);
        }
        return result;
    }

    private boolean gleich(Integer wert) {
        return this.wert.equals(wert);
    }

    public Integer getWert() {
        return wert;
    }
}
