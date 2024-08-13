package NP_complete.k_sat;

import data_structures.boolean_formula.cnf._3CNF;
import data_structures.graph.Graph;

public class _3SAT {
    
    private final _3CNF cnf;

    public _3SAT(final _3CNF cnf) {
        this.cnf = cnf;
    }

    public Graph reduceTo3Coloring() {
        // TODO: Implement the reduction function 3-Sat <= 3-Col
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * @see reduceTo3Coloring
     * @see _3Coloring
     *
     * @return
     */
    public boolean solve() {
        // TODO: Implement a 3-Sat-Solver using the reduction function and the 3-Col-Solver
        throw new RuntimeException("Not implemented yet!");
    }
}
