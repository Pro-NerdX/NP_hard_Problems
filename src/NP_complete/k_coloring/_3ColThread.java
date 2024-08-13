package NP_complete.k_coloring;

import java.util.ArrayList;
import java.util.HashSet;

import data_structures.graph.Vertex;
import utils.Pair;

public class _3ColThread extends Thread {
    
    private final _3Coloring _3coloring;

    private final int lastAssignedAtIndex;
    private final ArrayList<Pair<Vertex, Integer>> assignment;
    private final int numVertices;

    public _3ColThread(
        final _3Coloring _3coloring,
        final int lastAssignedAtIndex,
        final ArrayList<Pair<Vertex, Integer>> assignment,
        final int numVertices
    ) {
        this._3coloring = _3coloring;
        this.lastAssignedAtIndex = lastAssignedAtIndex;
        this.assignment = assignment;
        this.numVertices = numVertices;
    }

    @Override
    public void run() {
        if (this._3coloring.getResult() != null) {
            return;
        }
        if (!((this.lastAssignedAtIndex + 1) == this.numVertices)) {
            this.subRun();
            return;
        }
        // everything is assigned, so run verifier
        if (this._3coloring.verifyInPolytime(this.assignment)) {
            this._3coloring.setResult(true);
            this._3coloring.set3Coloring(this.assignment);
        }
    }

    /**
     * @brief Not every vertex has been assigned to a possible color yet, so we must start new threads to keep assigning vertices.
     */
    private void subRun() {
        final int nextIndextoAssign = this.lastAssignedAtIndex + 1;
        final HashSet<_3ColThread> newThreads = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            final ArrayList<Pair<Vertex, Integer>> newAssignment = new ArrayList<>(this.assignment);
            final Vertex vertex2assign = this.assignment.remove(nextIndextoAssign).first;
            this.assignment.add(new Pair<Vertex, Integer>(vertex2assign, i));
            final _3ColThread thread = new _3ColThread(this._3coloring, nextIndextoAssign, newAssignment, this.numVertices);
            thread.start();
            newThreads.add(thread);
        }
        for (final _3ColThread thread : newThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
