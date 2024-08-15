package NP_complete.k_sat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import NP_complete.k_coloring.Sequential3Coloring;
import NP_complete.k_coloring._3Coloring;
import data_structures.boolean_formula.Variable;
import data_structures.boolean_formula.cnf.Disjunction;
import data_structures.boolean_formula.cnf.Literal;
import data_structures.boolean_formula.cnf._3CNF;
import data_structures.graph.Edge;
import data_structures.graph.Graph;
import data_structures.graph.Vertex;

public class _3SatImplementation {

    private final _3CNF cnf;

    public _3SatImplementation(final _3CNF cnf) {
        this.cnf = cnf;
    }

    /*
     * TODO: Reduction generates too large arrays
     */
    public Graph reduceTo3Coloring() {
        // Add 1 copy of G_1
        final Vertex bottomNode = new Vertex(-1);
        final Vertex topNode = new Vertex(1);
        final Vertex zNode = new Vertex(0);
        
        final HashSet<Vertex> vertices = new HashSet<>();
        //vertices.add(bottomNode);
        //vertices.add(topNode);
        //vertices.add(zNode);

        final HashSet<Edge> edges = new HashSet<>();
        edges.add(new Edge(bottomNode, topNode, 1));
        edges.add(new Edge(topNode, zNode, 1));
        edges.add(new Edge(zNode, bottomNode, 1));

        // Add 1 copy of G_2 for each variable x_i
        final HashMap<Variable, Integer> varToVertexIdMap = new HashMap<>();
        int idCounter = 2;
        for (final Variable variable : this.cnf.getAllVariables()) {
            varToVertexIdMap.put(variable, idCounter);
            final Vertex positive = new Vertex(idCounter);
            final Vertex negative = new Vertex(-idCounter);
            vertices.add(positive);
            vertices.add(negative);
            idCounter++;

            edges.add(new Edge(zNode, positive, 2));
            edges.add(new Edge(zNode, negative, 2));
            edges.add(new Edge(positive, negative, 2));
        }

        // Add 1 copy of G_3 for each clause
        for (final Disjunction clause : this.cnf.getAllClauses()) {
            int sizeOfClause = clause.size();
            if (sizeOfClause == 0) {
                throw new RuntimeException("Empty clause found!");
            }

            // getting l_1, l_2, and l_3
            final ArrayList<Vertex> ls = new ArrayList<>();
            for (final Literal literal : clause.literals) {
                final Variable var = literal.variable;
                final Vertex l_i = this.getVertexById(
                    vertices,
                    literal.withoutUnaryNOT
                        ? varToVertexIdMap.get(var)
                        : (varToVertexIdMap.get(var) * (-1))
                );
                ls.add(l_i);
            }
            while (sizeOfClause < 3) {
                ls.add(ls.get(0));
            }
            final Vertex l1, l2, l3;
            l1 = ls.get(0);
            l2 = ls.get(1);
            l3 = ls.get(2);

            // introduce dummy vertices
            final ArrayList<Vertex> dummies = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                final Vertex dummy = new Vertex(idCounter + i);
                dummies.add(dummy);
                vertices.add(dummy);
            }
            idCounter += 6;

            // introduce edges for G_3
            edges.add(new Edge(l1, dummies.get(0), 3));
            edges.add(new Edge(l2, dummies.get(1), 3));
            edges.add(new Edge(l3, dummies.get(4), 3));
            edges.add(new Edge(dummies.get(0), dummies.get(1), 3));
            edges.add(new Edge(dummies.get(0), dummies.get(2), 3));
            edges.add(new Edge(dummies.get(1), dummies.get(2), 3));
            edges.add(new Edge(dummies.get(2), dummies.get(3), 3));
            edges.add(new Edge(dummies.get(3), dummies.get(4), 3));
            edges.add(new Edge(dummies.get(3), dummies.get(5), 3));
            edges.add(new Edge(dummies.get(4), dummies.get(5), 3));
            edges.add(new Edge(dummies.get(5), bottomNode, 3));
        }

        // generate the final graph and return
        final Graph graph = new Graph(edges, false);
        return graph;
    }

    private Vertex getVertexById(final HashSet<Vertex> vertices, final int id) {
        for (final Vertex vertex : vertices) {
            if (vertex.id == id) {
                return vertex;
            }
        }
        return null;
    }

    /**
     * @see reduceTo3Coloring
     * @see _3Coloring
     *
     * @return
     */
    public boolean solve() {
        final Graph graph = this.reduceTo3Coloring();
        final Sequential3Coloring _3colSolver = new Sequential3Coloring(graph);
        _3colSolver.solve();
        return _3colSolver.wasYesInstance();
    }
}
