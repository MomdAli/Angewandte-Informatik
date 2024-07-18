package expressiontree;

import java.util.Map;

public class Sum extends CompoundExpression {

    public Sum(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval(Map<String, Double> map) {
        return left.eval(map) + right.eval(map);
    }

    @Override
    public String toString() {
        return String.format("(%s + %s)", left.toString(), right.toString());
    }
}
