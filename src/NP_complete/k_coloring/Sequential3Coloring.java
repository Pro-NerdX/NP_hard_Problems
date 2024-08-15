package NP_complete.k_coloring;

import java.util.HashMap;

import data_structures.graph.Edge;
import data_structures.graph.Graph;
import data_structures.graph.Vertex;

public class Sequential3Coloring {
    
    private Graph graph;

    private Boolean wasYesInstance = null;
    private Vertex[] vertices;
    private Integer[] colors;
    private Integer numberOfVertices;

    public Sequential3Coloring() {
        this.graph = null;
        this.vertices = null;
        this.numberOfVertices = null;
        this.colors = null;
    }

    public Sequential3Coloring(final Graph G) {
        this.setGraph(G);
    }

    public void setGraph(final Graph G) {
        this.graph = G;
        this.numberOfVertices = this.graph.V.size();
        this.vertices = new Vertex[this.numberOfVertices];
        this.colors = new Integer[this.numberOfVertices];
        int index = 0;
        for (final Vertex vertex : this.graph.V) {
            this.vertices[index] = vertex;
        }
    }

    private boolean checkIfGraphIsNull() {
        return this.graph == null;
    }

    public void solve() {
        if (this.checkIfGraphIsNull()) {
            throw new RuntimeException("No Graph has been initialized!");
        }
        this.solveActually(0);
        if (this.wasYesInstance == null) {
            this.wasYesInstance = false;
        }
    }

    private HashMap<Vertex, Integer> createCertificate() {
        final HashMap<Vertex, Integer> certificate = new HashMap<>();
        for (int i = 0; i < this.numberOfVertices; i++) {
            certificate.put(this.vertices[i], this.colors[i]);
        }
        return certificate;
    }

    private void solveActually(final int currentVertexIndex) {
        if (currentVertexIndex == this.numberOfVertices) {
            if (this.verifyInPolytime(this.createCertificate())) {
                this.wasYesInstance = true;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (this.wasYesInstance != null) {
                return;
            }
            this.colors[currentVertexIndex] = i;
            this.solveActually(currentVertexIndex + 1);
        }
    }

    public boolean verifyInPolytime(final HashMap<Vertex, Integer> certificate) {
        if (certificate.keySet().size() != this.numberOfVertices) {
            return false;
        }
        for (final Edge edge : this.graph.E) {
            if (certificate.get(edge.from) == certificate.get(edge.to)) {
                return false;
            }
        }
        return true;
    }

    public boolean wasYesInstance() {
        return this.wasYesInstance == null ? false : this.wasYesInstance;
    }

    public HashMap<Vertex, Integer> getCertificateOrNull() {
        return this.wasYesInstance() ? this.createCertificate() : null;
    }
}
