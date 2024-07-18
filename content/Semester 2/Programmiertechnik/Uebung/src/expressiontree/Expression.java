package expressiontree;

import java.util.Map;
import java.util.Set;

public interface Expression {

    /**
     * Evaluates the expression.
     * @param map
     * @return the value of the expression.
     */
    public double eval(Map<String, Double> map);

    /**
     * @return the set of variables
     */
    public Set<String> getVars();
}
