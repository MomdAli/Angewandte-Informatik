package expressiontree;

import java.util.Set;
import java.util.TreeSet;

public abstract class CompoundExpression implements Expression {

    protected Expression left;
    protected Expression right;

    @Override
    public Set<String> getVars() {
        Set<String> set = new TreeSet<String>(left.getVars());
        set.addAll(right.getVars());
        return set;
    }
}
