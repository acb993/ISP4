package core;

import net.Constraint;
import net.Graph;
import net.Knot;
import net.UnaerConstraint;

import java.util.ArrayList;

public class AC_3 {

    public static ArrayList<Graph> solve(Graph graph){
        ArrayList<Graph> result = new ArrayList<>();
        graph = removeUnaerConstraint(graph);


        return result;
    }

    public static ArrayList<Graph> solveAlgo(Graph graph){
        ArrayList<Graph> result = new ArrayList<>();

        return result;
    }
    public static boolean ac_3(Knot veraendert, Graph graph){
        return false;
    }

    public static boolean revise(Knot Vi,Knot Vj, Graph graph){
        return false;
    }

    public static Graph removeUnaerConstraint(Graph graph){
        UnaerConstraint unaerConstraint = null;
        Knot knoten = null;
        for (Constraint constraint: graph.getConstraints()) {
                if (constraint instanceof UnaerConstraint){
                unaerConstraint = (UnaerConstraint) constraint;
                knoten = unaerConstraint.getKnoten();
                knoten.annahme(unaerConstraint.getWert());
                graph.removeConstraint(constraint);
                }
        }

        return graph;
    }
}
