package expressiontree;

import java.util.Map;
import java.util.Set;

public class Constant  implements Expression {

    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> map) {
        return value;
    }

    @Override
    public Set<String> getVars() {
        return Set.of();
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
