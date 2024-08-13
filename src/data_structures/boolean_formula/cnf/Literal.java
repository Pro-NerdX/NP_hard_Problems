package data_structures.boolean_formula.cnf;

import data_structures.boolean_formula.Variable;

public class Literal {
    
    public final boolean withoutUnaryNOT;
    public final Variable variable;

    public Literal(final boolean withoutUnaryNOT, final Variable variable) {
        this.withoutUnaryNOT = withoutUnaryNOT;
        this.variable = variable;
    }

    public Boolean evaluate() {
        final Boolean assignment = this.variable.getAssignmentOrNull();
        if (assignment == null) {
            return null;
        }
        return this.withoutUnaryNOT ? assignment : !assignment;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Literal)) {
            return false;
        }
        final Literal literal = (Literal) object;
        return this.variable.equals(literal.variable)
            && (this.withoutUnaryNOT == literal.withoutUnaryNOT);
    }
}
