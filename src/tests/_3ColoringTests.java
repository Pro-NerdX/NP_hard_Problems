package tests;

import static org.junit.Assert.assertFalse;

// import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import NP_complete.k_coloring._3Coloring;
import data_structures.graph.*;

import org.junit.Test;

public class _3ColoringTests {
    
    @Test
    public void yesInstanceWithLinearGraphTest() {
        final List<Vertex> vertexList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            vertexList.add(new Vertex(i));
        }
        final HashSet<Edge> edges = new HashSet<>();
        for (int i = 0; i < 11; i++) {
            edges.add(new Edge(vertexList.get(i), vertexList.get(i + 1), i));
        }
        final Graph graph = new Graph(new HashSet<>(vertexList), edges, false);
        final _3Coloring _3coloringSolver = new _3Coloring(graph);
        assert (_3coloringSolver.solve());
    }

    @Test
    public void noInstanceWithFullyConnected4Test() {
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
        final _3Coloring _3coloringSolver = new _3Coloring(graph);
        assertFalse(_3coloringSolver.solve());
    }

    @Test
    public void yesInstanceComplex1Test() {
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
        final _3Coloring _3coloringSolver = new _3Coloring(graph);
        assert (_3coloringSolver.solve());
    }

    @Test
    public void noInstanceComplex1Test() {
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
        edges.add(new Edge(V.get(6), V.get(3), 12));
        edges.add(new Edge(V.get(6), V.get(5), 13));
        edges.add(new Edge(V.get(6), V.get(7), 14));
        final Graph graph = new Graph(new HashSet<>(V), edges, false);
        // System.out.println(graph.toString());
        final _3Coloring _3coloringSolver = new _3Coloring(graph);
        assertFalse(_3coloringSolver.solve());
    }
}
