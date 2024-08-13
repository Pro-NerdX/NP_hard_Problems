package NP_complete.k_coloring;

import java.util.ArrayList;
import java.util.HashMap;

import data_structures.graph.*;
import utils.Pair;

public class _3Coloring {
    
    protected final Graph G;

    private Boolean result = null;
    private ArrayList<Pair<Vertex, Integer>> _3coloring = null;

    public _3Coloring(final Graph graph) {
        this.G = graph;
    }

    public boolean solve() {
        final ArrayList<Pair<Vertex, Integer>> initialAssignment = new ArrayList<>();
        for (final Vertex vertex : this.G.V) {
            initialAssignment.add(new Pair<Vertex,Integer>(vertex, null));
        }
        final int numVertices = this.G.V.size();
        final _3ColThread thread = new _3ColThread(this, 0, initialAssignment, numVertices);
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

    public boolean verifyInPolytime(final ArrayList<Pair<Vertex, Integer>> certificate) {
        final HashMap<Vertex, Integer> map = new HashMap<>();
        for (final Pair<Vertex, Integer> pair : certificate) {
            map.put(pair.first, pair.second);
        }
        for (final Edge edge : this.G.E) {
            if (map.get(edge.from) == map.get(edge.to)) {
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

    synchronized public ArrayList<Pair<Vertex, Integer>> get3Coloring() {
        return this._3coloring;
    }

    synchronized public void set3Coloring(final ArrayList<Pair<Vertex, Integer>> _3col) {
        this._3coloring = _3col;
    }
}
