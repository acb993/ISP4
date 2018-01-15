package net;

import core.ConstType;

public class Constraint {
    private Knot linkerWert;
    private Knot rechterWert;

    private ConstType type;

    public Constraint(Knot linkerWert, Knot rechterWert, ConstType type) {
        this.linkerWert = linkerWert;
        this.rechterWert = rechterWert;
        this.type = type;
    }

    public Boolean check(int firstValue, int secondValue) {
        Boolean result = false;

        switch (type) {
            case GLEICH: result = gleich(firstValue,secondValue);
                break;
            case UNAER: result = unaer(firstValue,secondValue);
                break;
            case NACHBAR: result = nachbar(firstValue,secondValue);
                break;
            case LINKERNACHBAR: result = linkerNachbar(firstValue,secondValue);
                break;
            case RECHTERNACHBAR: result = rechterNachbar(firstValue,secondValue);
                break;
        }

        return result;
    }

    private Boolean rechterNachbar(int firstValue, int secondValue) {
        Boolean result = false;
        return result;
    }

    private Boolean linkerNachbar(int firstValue, int secondValue) {
        Boolean result = false;
        return result;
    }

    private Boolean nachbar(int firstValue, int secondValue) {
        Boolean result = false;
        return result;
    }

    private Boolean unaer(int firstValue, int secondValue) {
        Boolean result = false;
        return result;
    }

    private Boolean gleich(int firstValue, int secondValue) {
        Boolean result = false;
        return result;
    }


}
