package data_structures.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    public final boolean isDirected;
    public final Set<Vertex> V;
    public final Set<Edge> E;

    public Graph(final boolean isDirected) {
        this.isDirected = isDirected;
        this.V = new HashSet<>();
        this.E = new HashSet<>();
    }

    public Graph(final Set<Edge> edges, final boolean isDirected) {
        this.isDirected = isDirected;
        this.E = edges;
        this.V = new HashSet<>();
        this.fillVertexSet();
    }

    public Graph(final Set<Vertex> vertices, final Set<Edge> edges, final boolean isDirected) {
        this.isDirected = isDirected;
        this.V = vertices;
        this.E = edges;
        this.fillVertexSet();
    }

    private void fillVertexSet() {
        for (final Edge edge : this.E) {
            this.V.add(edge.from);
            this.V.add(edge.to);
        }
    }

    public HashSet<Vertex> getNeighboursOf(final Vertex vertex) {
        final HashSet<Vertex> res = new HashSet<>();
        for (final Edge edge : this.E) {
            if (edge.from.equals(vertex)) {
                res.add(edge.to);
            }
            if (!(this.isDirected) && edge.to.equals(vertex)) {
                res.add(edge.from);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "" 
            + "G(V, E):\n"
            + "V:\n";
        for (final Vertex vertex : this.V) {
            res += "\t" + vertex.toString() + "\n";
        }
        res += "E:\n";
        for (final Edge edge : this.E) {
            res += "\t" + edge.toString() + "\n";
        }
        return res;
    }
}
