package tests;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;

import org.junit.Test;

import NP_complete.k_coloring.Sequential3Coloring;
import data_structures.graph.Edge;
import data_structures.graph.Graph;
import data_structures.graph.Vertex;

public class SequentialTests {
    
    final Sequential3Coloring _3colSolver = new Sequential3Coloring();

    @Test
    public void noInstanceFullyConnected15Test() {
        final Vertex[] vertices = new Vertex[15];
        for (int i = 0; i < 15; i++) {
            vertices[i] = new Vertex(i);
        }
        final HashSet<Edge> edges = new HashSet<>();
        for (int i = 0; i < 14; i++) {
            for (int j = (i + 1); j < 15; j++) {
                edges.add(new Edge(vertices[i], vertices[j], 0));
            }
        }
        final Graph connect100 = new Graph(edges, false);
        this._3colSolver.setGraph(connect100);
        this._3colSolver.solve();
        assertFalse(_3colSolver.wasYesInstance());
    }
}
