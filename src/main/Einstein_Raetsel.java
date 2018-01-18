package main;

import core.AC_3;
import core.ConstType;
import net.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Einstein_Raetsel {
    private static ArrayList<Integer> list = new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
    }};
    private static ArrayList<String> names = new ArrayList<>() {{
        add("Rot");
        add("Gruen");
        add("Weiss");
        add("Blau");
        add("Gelb");
        add("Englisch");
        add("Norwegisch");
        add("Deutsch");
        add("Schwedisch");
        add("Daenisch");
        add("Katze");
        add("Pferd");
        add("Vogel");
        add("Fisch");
        add("Hund");
        add("Wasser");
        add("Tee");
        add("Milch");
        add("Kaffee");
        add("Bier");
        add("Dunhill");
        add("Malboro");
        add("PallMall");
        add("Rothmanns");
        add("Winfield");
    }};

    public static void main(String[] args) {
        Graph graph = new Graph();
        for (String name : names) {
            graph.addKnot(new Knot(new ArrayList<Integer>(list), name));
        }

        graph.addConstraint(new UnaerConstraint(graph.getKnot("Milch"), ConstType.GLEICH, 3));
        graph.addConstraint(new UnaerConstraint(graph.getKnot("Norwegisch"), ConstType.GLEICH, 1));
// Erst die Eine Richtung und dann die Andere
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Rot"), graph.getKnot("Englisch"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Schwedisch"), graph.getKnot("Hund"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Gruen"), graph.getKnot("Kaffee"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Daenisch"), graph.getKnot("Tee"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("PallMall"), graph.getKnot("Vogel"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Winfield"), graph.getKnot("Bier"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Deutsch"), graph.getKnot("Rothmanns"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Gelb"), graph.getKnot("Dunhill"), ConstType.GLEICH));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Malboro"), graph.getKnot("Katze"), ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Pferd"), graph.getKnot("Dunhill"), ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Malboro"), graph.getKnot("Wasser"), ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Norwegisch"), graph.getKnot("Blau"), ConstType.NACHBAR));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Gruen"), graph.getKnot("Weiss"), ConstType.LINKERNACHBAR));
//Hier die andere Richtung!
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Englisch"), graph.getKnot("Rot"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Hund"), graph.getKnot("Schwedisch"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Kaffee"), graph.getKnot("Gruen"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Tee"), graph.getKnot("Daenisch"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Vogel"), graph.getKnot("PallMall"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Bier"), graph.getKnot("Winfield"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Rothmanns"), graph.getKnot("Deutsch"), ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Dunhill"), graph.getKnot("Gelb"), ConstType.GLEICH));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Katze"), graph.getKnot("Malboro"), ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Dunhill"), graph.getKnot("Pferd"), ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Wasser"), graph.getKnot("Malboro"), ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Blau"), graph.getKnot("Norwegisch"), ConstType.NACHBAR));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Weiss"), graph.getKnot("Gruen"), ConstType.RECHTERNACHBAR));


        ArrayList<Knot> constr = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            constr.add(graph.getKnot(names.get(i)));
            if (constr.size() == 5) {
                graph.getConstraints().addAll(BinaerConstraint.makeAllDifferent(constr));
                constr.clear();
            }
        }

        ArrayList<Graph> ergebnisse = AC_3.solve(graph);
        for (Graph loesung : ergebnisse) {
                System.out.println(loesung);
        }
        System.out.println(ergebnisse.size());

    }
}
