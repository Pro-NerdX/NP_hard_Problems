package data_structures.boolean_formula.cnf;

import java.util.ArrayList;

public class Disjunction {
    
    public final ArrayList<Literal> literals;

    public Disjunction(final ArrayList<Literal> literals) {
        this.literals = literals;
    }

    public boolean isFullyAssigned() {
        for (final Literal literal : this.literals) {
            if (!literal.variable.hasBeenAssigned()) {
                return false;
            }
        }
        return true;
    }

    public Boolean evaluate() {
        if (!this.isFullyAssigned()) {
            return null;
        }
        boolean res = false;
        for (final Literal literal : this.literals) {
            res = res || literal.evaluate();
        }
        return res;
    }

    public int size() {
        return this.literals.size();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Disjunction)) {
            return false;
        }
        final Disjunction disjunction = (Disjunction) object;
        return this.literals.equals(disjunction.literals);
    }
}
