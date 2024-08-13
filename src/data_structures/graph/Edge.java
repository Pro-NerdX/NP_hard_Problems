package data_structures.graph;

public class Edge {

    public final int id;

    public final Vertex from;
    public final Vertex to;

    public Edge(final Vertex from, final Vertex to, final int id) {
        this.from = from;
        this.to = to;
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Edge)) {
            return false;
        }
        final Edge edge = (Edge) object;
        return this.from.equals(edge.from) && this.to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return "{" + this.from.toString() + ", " + this.to.toString() + "} (id=" + this.id + ")";
    }
}
