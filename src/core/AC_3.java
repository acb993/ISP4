package core;

import net.*;

import java.util.ArrayList;

public class AC_3 {

    public static ArrayList<Graph> solve(Graph graph){
        ArrayList<Graph> result = new ArrayList<>();
        graph = removeUnaerConstraint(graph);

        if(ac_3(graph.getConstraints(),graph)){
            result.addAll(solveAlgo(graph));
        }
        return result;
    }
// rekursive methodee
    public static ArrayList<Graph> solveAlgo(Graph graph){
        ArrayList<Graph> result = new ArrayList<>();
        Graph copyGraph = graph.kopiere();
        Knot cv = null;
        //Hier ist ein Knoten schon veraender(annahme getroffen)
        if(ac_3(createQ(cv,copyGraph),copyGraph))
        return result;
    }
    public static boolean ac_3(ArrayList<Constraint> q, Graph graph){
 //       ArrayList<Constraint>
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

    private static ArrayList<Constraint> createQ(Knot cv,Graph graph){
        ArrayList<Constraint> result = new ArrayList<>();
        BinaerConstraint constr = null;
        for (Constraint constraint: graph.getConstraints()) {
            if(constraint instanceof BinaerConstraint){
                constr = (BinaerConstraint) constraint;
                if(constr.getRechterWert().equals(cv)){
                    result.add(constr);
                }
            }
        }
        return result;
    }
}
