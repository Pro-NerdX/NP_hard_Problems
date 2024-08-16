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
            final int sizeOfClause = clause.size();
            if (sizeOfClause == 0) {
                throw new RuntimeException("Empty clause found!");
            }

            // getting l_1, l_2, and l_3
            final Vertex[] ls = new Vertex[3];
            int index = 0;
            for (final Literal literal : clause.literals) {
                final Variable var = literal.variable;
                final Vertex l_i = this.getVertexById(
                    vertices,
                    literal.withoutUnaryNOT
                        ? varToVertexIdMap.get(var)
                        : (varToVertexIdMap.get(var) * (-1))
                );
                ls[index] = l_i;
                index++;
            }
            for (int i = 1; i < 3; i++) {
                if (ls[i] == null) {
                    ls[i] = ls[0];
                }
            }
            final Vertex l1, l2, l3;
            l1 = ls[0];
            l2 = ls[1];
            l3 = ls[2];

            // introduce dummy vertices
            final Vertex[] dummies = new Vertex[6];
            for (int i = 0; i < 6; i++) {
                final Vertex dummy = new Vertex(idCounter + i);
                dummies[i] = dummy;
                vertices.add(dummy);
            }
            idCounter += 6;

            // introduce edges for G_3
            edges.add(new Edge(l1, dummies[0], 3));
            edges.add(new Edge(l2, dummies[1], 3));
            edges.add(new Edge(l3, dummies[4], 3));
            edges.add(new Edge(dummies[0], dummies[1], 3));
            edges.add(new Edge(dummies[0], dummies[2], 3));
            edges.add(new Edge(dummies[1], dummies[2], 3));
            edges.add(new Edge(dummies[2], dummies[3], 3));
            edges.add(new Edge(dummies[3], dummies[4], 3));
            edges.add(new Edge(dummies[3], dummies[5], 3));
            edges.add(new Edge(dummies[4], dummies[5], 3));
            edges.add(new Edge(dummies[5], bottomNode, 3));
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
