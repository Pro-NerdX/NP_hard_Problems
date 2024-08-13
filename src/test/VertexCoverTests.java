package test;

// import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import NP_complete.vertex_cover.VertexCover;
import data_structures.graph.*;

import org.junit.Test;

public class VertexCoverTests {
    
    @Test
    public void noInstanceForFullyConnectedTest() {
        final List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            vertices.add(new Vertex(i));
        }
        final HashSet<Edge> edges = new HashSet<>();
        int idCounter = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = i + 1; j < 12; j++) {
                edges.add(new Edge(vertices.get(i), vertices.get(j), idCounter));
                idCounter++;
            }
        }
        final Graph graph = new Graph(edges, false);
        // System.out.println(graph.toString());
        final int k = 1;
        final VertexCover vertexCoverSolver = new VertexCover(k, graph);
        final boolean wasNoInstance = !vertexCoverSolver.solve();
        assert (wasNoInstance);
    }

    @Test
    public void yesInstanceForFullyConnectedTest() {
        final List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            vertices.add(new Vertex(i));
        }
        final HashSet<Edge> edges = new HashSet<>();
        int idCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                edges.add(new Edge(vertices.get(i), vertices.get(j), idCounter));
                idCounter++;
            }
        }
        final Graph graph = new Graph(edges, false);
        // System.out.println(graph.toString());
        final int k = 3;
        final VertexCover vertexCoverSolver = new VertexCover(k, graph);
        assert (vertexCoverSolver.solve());
    }

    @Test
    public void yesInstanceNotFullyConnectedTest() {
        final List<Vertex> V = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            V.add(new Vertex(i));
        }
        final HashSet<Edge> edges = new HashSet<>();
        edges.add(new Edge(V.get(0), V.get(1), 0));
        edges.add(new Edge(V.get(0), V.get(4), 1));
        edges.add(new Edge(V.get(0), V.get(3), 2));
        edges.add(new Edge(V.get(1), V.get(4), 3));
        edges.add(new Edge(V.get(1), V.get(5), 4));
        edges.add(new Edge(V.get(3), V.get(4), 5));
        edges.add(new Edge(V.get(3), V.get(7), 6));
        edges.add(new Edge(V.get(4), V.get(5), 7));
        edges.add(new Edge(V.get(5), V.get(2), 8));
        edges.add(new Edge(V.get(5), V.get(7), 9));
        edges.add(new Edge(V.get(5), V.get(8), 10));
        edges.add(new Edge(V.get(7), V.get(8), 11));
        final Graph graph = new Graph(new HashSet<>(V), edges, false);
        // System.out.println(graph.toString());
        final VertexCover vertexCoverSolver = new VertexCover(4, graph);
        assert (vertexCoverSolver.solve());
        final VertexCover vertexCoverSolver2 = new VertexCover(5, graph);
        assert (vertexCoverSolver2.solve());
    }
}
