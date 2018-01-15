package net;


import java.util.ArrayList;


public class Graph {
    private ArrayList<Constraint> kanten;
    private ArrayList<Knot> knoten;

    public Graph() {
        kanten = new ArrayList<>();
        knoten = new ArrayList<>();
    }


    public Graph kopiere() {
        Graph result = new Graph();
        for (Knot knot : knoten) {
            result.addKnot(new Knot(new ArrayList<>(knot.getWertebereich()), knot.getName()));
        }
        String nameLinks = "";
        String nameRechts = "";
        Knot knotenRechts = null;
        Knot knotenLinks = null;
        for (Constraint kante : kanten) {
            if (kante instanceof BinaerConstraint) {
                BinaerConstraint constraint = (BinaerConstraint) kante;
                nameLinks = constraint.getLinkerWert().getName();
                nameRechts = constraint.getRechterWert().getName();
                knotenLinks = result.getKnot(nameLinks);
                knotenRechts = result.getKnot(nameRechts);
                result.addConstraint(new BinaerConstraint(knotenLinks, knotenRechts, constraint.getType()));
            }
            if (kante instanceof UnaerConstraint){
                UnaerConstraint constraint = (UnaerConstraint) kante;
                nameLinks = constraint.getKnoten().getName();
                knotenLinks = result.getKnot(nameLinks);
                result.addConstraint(new UnaerConstraint(knotenLinks,constraint.getType(),constraint.getWert()));
            }
        }
        return result;
    }

    public void addKnot(Knot newKnoten) {
        knoten.add(newKnoten);
    }

    public void addConstraint(Constraint constraint) {
        kanten.add(constraint);
    }

    public Knot getKnot(String name) {
        for (Knot knote : knoten) {
            if (knote.getName().equals(name)) {
                return knote;
            }
        }
        return null;
    }

    public ArrayList<Constraint> getConstraints() {
        return kanten;
    }

    public void removeConstraint(Constraint constraint){
        kanten.remove(constraint);
    }
}
