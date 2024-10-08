package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import NP_complete.k_coloring.Sequential3Coloring;
import NP_complete.k_sat._3SAT;
import NP_complete.k_sat._3SatImplementation;
import data_structures.boolean_formula.Variable;
import data_structures.boolean_formula.cnf.Disjunction;
import data_structures.boolean_formula.cnf.Literal;
import data_structures.boolean_formula.cnf._3CNF;
import data_structures.graph.Edge;
import data_structures.graph.Graph;
import data_structures.graph.Vertex;

public class SequentialTests {

    final Sequential3Coloring _3colSolver = new Sequential3Coloring();

    private Variable[] variables = {
        new Variable("A"),
        new Variable("B"),
        new Variable("C"),
        new Variable("D"),
        new Variable("E"),
        new Variable("F"),
        new Variable("G"),
        new Variable("H"),
        new Variable("I"),
        new Variable("J"),
        new Variable("K"),
        new Variable("L"),
        new Variable("M"),
        new Variable("N"),
        new Variable("O"),
        new Variable("P"),
        new Variable("Q"),
        new Variable("R"),
        new Variable("S"),
        new Variable("T"),
        new Variable("U"),
        new Variable("V"),
        new Variable("W"),
        new Variable("X"),
        new Variable("Y"),
        new Variable("Z"),

        new Variable("A'"),
        new Variable("B'"),
        new Variable("C'"),
        new Variable("D'"),
        new Variable("E'"),
        new Variable("F'"),
        new Variable("G'"),
        new Variable("H'"),
        new Variable("I'"),
        new Variable("J'"),
        new Variable("K'"),
        new Variable("L'"),
        new Variable("M'"),
        new Variable("N'"),
        new Variable("O'"),
        new Variable("P'"),
        new Variable("Q'"),
        new Variable("R'"),
        new Variable("S'"),
        new Variable("T'"),
        new Variable("U'"),
        new Variable("V'"),
        new Variable("W'"),
        new Variable("X'"),
        new Variable("Y'"),
        new Variable("Z'")
    };

    private Literal[] positiveLiterals = {
        new Literal(true, this.variables[0]),
        new Literal(true, this.variables[1]),
        new Literal(true, this.variables[2]),
        new Literal(true, this.variables[3]),
        new Literal(true, this.variables[4]),
        new Literal(true, this.variables[5]),
        new Literal(true, this.variables[6]),
        new Literal(true, this.variables[7]),
        new Literal(true, this.variables[8]),
        new Literal(true, this.variables[9]),
        new Literal(true, this.variables[10]),
        new Literal(true, this.variables[11]),
        new Literal(true, this.variables[12]),
        new Literal(true, this.variables[13]),
        new Literal(true, this.variables[14]),
        new Literal(true, this.variables[15]),
        new Literal(true, this.variables[16]),
        new Literal(true, this.variables[17]),
        new Literal(true, this.variables[18]),
        new Literal(true, this.variables[19]),
        new Literal(true, this.variables[20]),
        new Literal(true, this.variables[21]),
        new Literal(true, this.variables[22]),
        new Literal(true, this.variables[23]),
        new Literal(true, this.variables[24]),
        new Literal(true, this.variables[25]),
        
        new Literal(true, this.variables[26]),
        new Literal(true, this.variables[27]),
        new Literal(true, this.variables[28]),
        new Literal(true, this.variables[29]),
        new Literal(true, this.variables[30]),
        new Literal(true, this.variables[31]),
        new Literal(true, this.variables[32]),
        new Literal(true, this.variables[33]),
        new Literal(true, this.variables[34]),
        new Literal(true, this.variables[35]),
        new Literal(true, this.variables[36]),
        new Literal(true, this.variables[37]),
        new Literal(true, this.variables[38]),
        new Literal(true, this.variables[39]),
        new Literal(true, this.variables[40]),
        new Literal(true, this.variables[41]),
        new Literal(true, this.variables[42]),
        new Literal(true, this.variables[43]),
        new Literal(true, this.variables[44]),
        new Literal(true, this.variables[45]),
        new Literal(true, this.variables[46]),
        new Literal(true, this.variables[47]),
        new Literal(true, this.variables[48]),
        new Literal(true, this.variables[49]),
        new Literal(true, this.variables[50]),
        new Literal(true, this.variables[51])
    };

    private Literal[] negativeLiterals = {
        new Literal(false, this.variables[1]),
        new Literal(false, this.variables[2]),
        new Literal(false, this.variables[3]),
        new Literal(false, this.variables[0]),
        new Literal(false, this.variables[4]),
        new Literal(false, this.variables[5]),
        new Literal(false, this.variables[6]),
        new Literal(false, this.variables[7]),
        new Literal(false, this.variables[8]),
        new Literal(false, this.variables[9]),
        new Literal(false, this.variables[10]),
        new Literal(false, this.variables[11]),
        new Literal(false, this.variables[12]),
        new Literal(false, this.variables[13]),
        new Literal(false, this.variables[14]),
        new Literal(false, this.variables[15]),
        new Literal(false, this.variables[16]),
        new Literal(false, this.variables[17]),
        new Literal(false, this.variables[18]),
        new Literal(false, this.variables[19]),
        new Literal(false, this.variables[20]),
        new Literal(false, this.variables[21]),
        new Literal(false, this.variables[22]),
        new Literal(false, this.variables[23]),
        new Literal(false, this.variables[24]),
        new Literal(false, this.variables[25]),
        
        new Literal(false, this.variables[26]),
        new Literal(false, this.variables[27]),
        new Literal(false, this.variables[28]),
        new Literal(false, this.variables[29]),
        new Literal(false, this.variables[30]),
        new Literal(false, this.variables[31]),
        new Literal(false, this.variables[32]),
        new Literal(false, this.variables[33]),
        new Literal(false, this.variables[34]),
        new Literal(false, this.variables[35]),
        new Literal(false, this.variables[36]),
        new Literal(false, this.variables[37]),
        new Literal(false, this.variables[38]),
        new Literal(false, this.variables[39]),
        new Literal(false, this.variables[40]),
        new Literal(false, this.variables[41]),
        new Literal(false, this.variables[42]),
        new Literal(false, this.variables[43]),
        new Literal(false, this.variables[44]),
        new Literal(false, this.variables[45]),
        new Literal(false, this.variables[46]),
        new Literal(false, this.variables[47]),
        new Literal(false, this.variables[48]),
        new Literal(false, this.variables[49]),
        new Literal(false, this.variables[50]),
        new Literal(false, this.variables[51])
    };

    // 3-Col tests
    @Test
    public void noInstanceFullyConnected15Test() {
        final Vertex[] vertices = new Vertex[18];
        for (int i = 0; i < 18; i++) {
            vertices[i] = new Vertex(i);
        }
        final HashSet<Edge> edges = new HashSet<>();
        for (int i = 0; i < 17; i++) {
            for (int j = (i + 1); j < 18; j++) {
                edges.add(new Edge(vertices[i], vertices[j], 0));
            }
        }
        final Graph connect100 = new Graph(edges, false);
        this._3colSolver.setGraph(connect100);
        this._3colSolver.solve();
        assertFalse(_3colSolver.wasYesInstance());
    }

    // 3-Sat tests
    @Test
    public void trivialYesInstanceSmall() {
        final ArrayList<Disjunction> cnf = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            final ArrayList<Literal> literals = new ArrayList<>();
            literals.add(this.positiveLiterals[i]);
            literals.add(this.negativeLiterals[i]);
            cnf.add(new Disjunction(literals));
        }
        final _3CNF actualCNF = new _3CNF(cnf);
        final _3SatImplementation _3sat = new _3SatImplementation(actualCNF);
        final Graph graphAfterReduction = _3sat.reduceTo3Coloring();
        assertEquals(21, graphAfterReduction.V.size()); // the reduction is supposed to have 21 nodes
        assert (_3sat.solve());
    }
}

    /*@Test
    public void satisfyingLargeTest() {
        final ArrayList<Disjunction> cnf = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final ArrayList<Literal> literals = new ArrayList<>();
            literals.add(this.positiveLiterals[i]);
            literals.add(this.negativeLiterals[i]);
            cnf.add(new Disjunction(literals));
        }
        for (int i = 0; i < 5; i += 2) {
            final ArrayList<Literal> literalsPositive = new ArrayList<>();
            final ArrayList<Literal> literalsNegative = new ArrayList<>();
            for (int j = i; j < (i + 3); j++) {
                literalsPositive.add(this.positiveLiterals[j]);
                literalsNegative.add(this.negativeLiterals[j]);
            }
            cnf.add(new Disjunction(literalsPositive));
            cnf.add(new Disjunction(literalsNegative));
        }
        final _3CNF actualCNF = new _3CNF(cnf);
        final _3SatImplementation _3sat = new _3SatImplementation(actualCNF);
        assert (_3sat.solve());
    }*/
