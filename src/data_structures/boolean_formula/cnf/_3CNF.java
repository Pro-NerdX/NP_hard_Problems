package data_structures.boolean_formula.cnf;

import java.util.ArrayList;

public class _3CNF {
    
    final ArrayList<Disjunction> disjunctions;

    public _3CNF(final ArrayList<Disjunction> disjunctions) {
        this.disjunctions = disjunctions;
        this.validate();
    }

    private void validate() {
        for (final Disjunction disjunction : this.disjunctions) {
            final int length = disjunction.size();
            assert (length > 0);
            assert (length <= 3);
        }
    }

    public boolean isFullyAssigned() {
        for (final Disjunction disjunction : this.disjunctions) {
            if (!disjunction.isFullyAssigned()) {
                return false;
            }
        }
        return true;
    }

    public Boolean evaluate() {
        if (!this.isFullyAssigned()) {
            return null;
        }
        boolean res = true;
        for (final Disjunction disjunction : this.disjunctions) {
            res = res && disjunction.evaluate();
        }
        return res;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof _3CNF)) {
            return false;
        }
        final _3CNF _3cnf = (_3CNF) object;
        return this.disjunctions.equals(_3cnf.disjunctions);
    }
}
