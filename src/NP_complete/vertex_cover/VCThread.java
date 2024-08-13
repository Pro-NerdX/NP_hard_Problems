package NP_complete.vertex_cover;

import java.util.HashSet;

import data_structures.graph.*;

public class VCThread extends Thread {
    
    private final VertexCover vertexCover;
    private final HashSet<Vertex> selected;
    private final HashSet<Vertex> deselected;

    public VCThread(final VertexCover vertexCover, final HashSet<Vertex> selected, final HashSet<Vertex> deselected) {
        this.vertexCover = vertexCover;
        this.selected = selected;
        this.deselected = deselected;
    }

    @Override
    public void run() {
        // System.out.println(this.toString() + " started.");
        if (this.vertexCover.getResult() != null) {
            return;
        }
        if (this.vertexCover.verifyInPolyTime(this.selected)) {
            this.vertexCover.setResult(true);
            this.vertexCover.setVertexCover(this.selected);
            return;
        }
        final int selectedSize = this.selected.size();
        final int verticesVisited = selectedSize + this.deselected.size();
        if (selectedSize == this.vertexCover.k || verticesVisited == this.vertexCover.G.V.size()) {
            return;
        }
        Vertex newVertex = null;
        for (final Vertex vertex : this.vertexCover.G.V) {
            if (!(this.selected.contains(vertex)) && !(this.deselected.contains(vertex))) {
                newVertex = vertex;
                break;
            }
        }
        final HashSet<Vertex> newSelected = new HashSet<>(this.selected);
        newSelected.add(newVertex);
        final HashSet<Vertex> newDeselected = new HashSet<>(this.deselected);
        newDeselected.add(newVertex);
        final VCThread selectThread = new VCThread(this.vertexCover, newSelected, this.deselected);
        final VCThread deselectThread = new VCThread(this.vertexCover, this.selected, newDeselected);
        selectThread.start();
        deselectThread.start();
        try {
            selectThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            deselectThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
