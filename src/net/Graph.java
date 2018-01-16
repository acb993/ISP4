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
            if (kante instanceof UnaerConstraint) {
                UnaerConstraint constraint = (UnaerConstraint) kante;
                nameLinks = constraint.getKnoten().getName();
                knotenLinks = result.getKnot(nameLinks);
                result.addConstraint(new UnaerConstraint(knotenLinks, constraint.getType(), constraint.getWert()));
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

    public ArrayList<Knot> getKnoten() {
        return knoten;
    }

    public void removeConstraint(Constraint constraint) {
        kanten.remove(constraint);
    }

    public ArrayList<Constraint> getKantenFromKnot(Knot knot) {
        ArrayList<Constraint> result = new ArrayList<>();
        for (Constraint con : kanten) {
            if (con instanceof BinaerConstraint) {
                if (((BinaerConstraint) con).getRechterWert().equals(knot)) {
                    result.add(con);
                }
            }
        }
        return result;
    }

    public boolean consistent(Knot vi, Integer x, Knot vj, Integer y){
        ArrayList<Constraint> constraints = getAllConstraintBetween(vi,vj);
        BinaerConstraint bincon = null;
        Boolean consistent = true;
        for (Constraint cons : constraints){
            bincon = (BinaerConstraint) cons;
            if(!bincon.check(x,y)){
                return false;
            }
        }

        return consistent;
    }

    public ArrayList<Constraint> getAllConstraintBetween(Knot vi, Knot vj) {
        ArrayList<Constraint> between = new ArrayList<>();
        BinaerConstraint con2 = null;
        for (Constraint con : kanten) {
            con2 = (BinaerConstraint) con;
            if (con2.getLinkerWert().equals(vi) && con2.getRechterWert().equals(vj)) {
                between.add(con);
            }
        }
        return between;
    }
}
