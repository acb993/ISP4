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
        for (String name: names) {
            graph.addKnot(new Knot(new ArrayList<Integer>(list),name));
        }

        graph.addConstraint(new UnaerConstraint(graph.getKnot("Milch"), ConstType.GLEICH,3));
        graph.addConstraint(new UnaerConstraint(graph.getKnot("Norwegisch"), ConstType.GLEICH,1));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Rot"),graph.getKnot("Englisch"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Schwede"),graph.getKnot("Hund"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Gruen"),graph.getKnot("Kaffee"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Daenisch"),graph.getKnot("Tee"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("PallMall"),graph.getKnot("Vogel"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Winfield"),graph.getKnot("Bier"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Norwegisch"),graph.getKnot("Blau"),ConstType.GLEICH));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Deutsch"),graph.getKnot("Rothmanns"),ConstType.GLEICH));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Malboro"),graph.getKnot("Katze"),ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Pferd"),graph.getKnot("Dunhill"),ConstType.NACHBAR));
        graph.addConstraint(new BinaerConstraint(graph.getKnot("Malboro"),graph.getKnot("Wasser"),ConstType.NACHBAR));

        graph.addConstraint(new BinaerConstraint(graph.getKnot("Gruen"),graph.getKnot("Weiss"),ConstType.LINKERNACHBAR));


        ArrayList<Knot> constr  = new ArrayList<>();
        for(int i=0; i<names.size();i++){
            constr.add(graph.getKnot(names.get(i)));
            if(constr.size()==5){
                graph.getConstraints().addAll(BinaerConstraint.makeAllDifferent(constr));
                constr.clear();
            }

        }

        System.out.println(Arrays.toString(AC_3.solve(graph).toArray()));

    }
}
