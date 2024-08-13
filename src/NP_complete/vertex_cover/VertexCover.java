package NP_complete.vertex_cover;

import java.util.HashSet;

import data_structures.graph.*;

public class VertexCover {

    protected final int k;
    protected final Graph G;

    private Boolean result = null;
    private HashSet<Vertex> vertexCover = null;

    public VertexCover(final int k, final Graph G) {
        assert(k >= 0);
        this.k = k;
        this.G = G;
    }

    public boolean solve() {
        final VCThread runnable = new VCThread(this, new HashSet<>(), new HashSet<>());
        final Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.result == null) {
            this.result = false;
        }
        return this.result;
    }

    public boolean verifyInPolyTime(final HashSet<Vertex> certificate) {
        if (certificate.size() > k) {
            return false;
        }
        for (final Edge edge : this.G.E) {
            if ((!certificate.contains(edge.to)) && (!certificate.contains(edge.from))) {
                return false;
            }
        }
        return true;
    }

    synchronized public Boolean getResult() {
        return this.result;
    }

    synchronized public void setResult(final boolean res) {
        this.result = res;
    }

    synchronized public HashSet<Vertex> getVertexCover() {
        return this.vertexCover;
    }

    synchronized public void setVertexCover(final HashSet<Vertex> vc) {
        this.vertexCover = vc;
    }
}
