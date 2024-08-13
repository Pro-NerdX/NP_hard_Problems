package data_structures.boolean_formula;

public class Variable {
    
    public final String name;

    private Boolean assignment = null;

    public Variable (final String name) {
        this.name = name;
    }

    public void assign(final boolean assignment) {
        this.assignment = assignment;
    }

    public boolean hasBeenAssigned() {
        return this.assignment != null;
    }

    public Boolean getAssignmentOrNull() {
        return this.assignment;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Variable)) {
            return false;
        }
        final Variable variable = (Variable) object;
        return this.name == variable.name;
    }
}
