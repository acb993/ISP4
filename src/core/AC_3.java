package core;

import net.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AC_3 {

    public static ArrayList<Graph> solve(Graph graph) {
        ArrayList<Graph> result = new ArrayList<>();
        graph = removeUnaerConstraint(graph);
        System.out.println(graph);
        if (ac_3(graph.getConstraints(), graph)) {
            result.addAll(solveAlgo(graph,""));
        }
        return result;
    }

    // rekursive methodee
    public static ArrayList<Graph> solveAlgo(Graph graph,String weg) {
        ArrayList<Graph> result = new ArrayList<>();

        Knot nextKnot = getNextKnot(graph);
        if (nextKnot==null) {
            result.add(graph);
            System.out.println("CONSISTEN ->" + weg);
        } else {
            for (Integer annahme : nextKnot.getWertebereich()) {
                Graph copyGraph = graph.kopiere();
                Knot cv = copyGraph.getKnot(nextKnot.getName());
                cv.annahme(annahme);
                if (ac_3(createQ(cv, copyGraph), copyGraph)) {
                    result.addAll(solveAlgo(copyGraph, weg+ "->" + cv ));
                }else {
 //                   System.out.println("NONCONSISTEN ->" + weg + "->" +cv);
                }

            }
        }
        return result;
    }

    public static boolean ac_3(ArrayList<Constraint> constlist, Graph graph) {
        //       ArrayList<Constraint>
        BinaerConstraint constraint = null;
        ArrayList<Constraint> q = new ArrayList<>(constlist);
        Boolean consistent = true;
        while (!q.isEmpty() && consistent) {
            constraint = (BinaerConstraint) q.remove(0);
            Knot vk = constraint.getLinkerWert();
            Knot vm = constraint.getRechterWert();
            if (revise(vk, vm, graph)) {
                ArrayList<Constraint> unionGraph = union(graph.getKantenFromKnot(vk), q);
                ArrayList<Constraint> cleanList = new ArrayList<>();
                for (Constraint con : unionGraph) {
                    BinaerConstraint con2 = (BinaerConstraint) con;
                    if (!(con2.getLinkerWert().equals(vk) || con2.getLinkerWert().equals(vm))) {
                        cleanList.add(con);
                    }
                }
                for (Constraint constr: cleanList) {
                    if (!q.contains(constr)) {
                        q.add(constr);
                    }
                }
                consistent = vk.getWertebereich().size() > 0;
            }

        }
        return consistent;
    }

    public static boolean revise(Knot vi, Knot vj, Graph graph) {
        ArrayList<Integer> toBeDeleted = new ArrayList<>();
        Boolean delete = false;
        Boolean consistent = false;
        for (Integer x : vi.getWertebereich()) {
            consistent = false;
            for (Integer y : vj.getWertebereich()) {
                if (graph.consistent(vi, x, vj, y)) {
                    consistent = true;
                }
            }
            if (!consistent) {
                toBeDeleted.add(x);
                delete = true;
            }
        }

        for (Integer x : toBeDeleted) {
            vi.removeValue(x);
        }

        return delete;
    }

    public static Graph removeUnaerConstraint(Graph graph) {
        ArrayList<Constraint> toBeRemoved = new ArrayList<>();
        UnaerConstraint unaerConstraint = null;
        Knot knoten = null;

        for (Constraint constraint : graph.getConstraints()) {
            if (constraint instanceof UnaerConstraint) {
                unaerConstraint = (UnaerConstraint) constraint;
                knoten = unaerConstraint.getKnoten();
                knoten.annahme(unaerConstraint.getWert());
                toBeRemoved.add(constraint);
            }
        }
        for (Constraint constraint : toBeRemoved) {
            graph.removeConstraint(constraint);
        }
        return graph;
    }

    private static ArrayList<Constraint> createQ(Knot cv, Graph graph) {
        ArrayList<Constraint> result = new ArrayList<>();
        BinaerConstraint constr = null;
        for (Constraint constraint : graph.getConstraints()) {
            if (constraint instanceof BinaerConstraint) {
                constr = (BinaerConstraint) constraint;
                if (constr.getRechterWert().equals(cv)) {
                    result.add(constr);
                }
            }
        }
        return result;
    }

    private static Knot getNextKnot(Graph graph) {
        Knot result = null;
        ArrayList<Knot> knoten = graph.getKnoten();
        if (knoten.isEmpty())
            return null;

        for (Knot knot : knoten) {
            if (knot.getWertebereich().size() > 1) {
                if (result == null) {
                    result = knot;
                } else if (knot.getWertebereich().size() < result.getWertebereich().size()) {
                    result = knot;
                } else if (knot.getWertebereich().size() == result.getWertebereich().size() && graph.getKantenFromKnot(knot).size() > graph.getKantenFromKnot(result).size()) {
                    result = knot;
                }
            }
        }

        return result;
    }

    private static <T> ArrayList<T> union(ArrayList<T> liste1, ArrayList<T> liste2) {
        Set<T> set = new HashSet<T>();
        set.addAll(liste1);
        set.addAll(liste2);

        return new ArrayList<>(set);
    }
}
