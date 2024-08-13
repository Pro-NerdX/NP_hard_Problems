package data_structures.graph;

public class Vertex {
    
    public final int id;

    public Vertex(final int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vertex)) {
            return false;
        }
        final Vertex vertex = (Vertex) object;
        return vertex.id == this.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return "(" + this.id + ")";
    }
}
