package main;

import core.AC_3;
import core.ConstType;
import net.BinaerConstraint;
import net.Graph;
import net.Knot;

import java.util.ArrayList;
import java.util.Arrays;

public class easyRaetsel {

    public static void main(String[] args) {
        Graph einfach = new Graph();
        ArrayList<Integer> werte = new ArrayList<>();
        werte.add(1);
        werte.add(2);
        ArrayList<Integer> werte2 = new ArrayList<>();
        werte2.add(3);
        werte2.add(4);
        einfach.addKnot(new Knot(new ArrayList<>(werte),"X"));
        einfach.addKnot(new Knot(new ArrayList<>(werte2),"Y"));

        einfach.addConstraint(new BinaerConstraint(einfach.getKnot("X"),einfach.getKnot("Y"), ConstType.GLEICH));
        einfach.addConstraint(new BinaerConstraint(einfach.getKnot("Y"),einfach.getKnot("X"), ConstType.GLEICH));

        System.out.println(Arrays.toString(AC_3.solve(einfach).toArray()));
    }
}
