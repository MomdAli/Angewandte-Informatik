package expressiontree;

import java.util.Map;
import java.util.Set;

public class Var implements Expression {

    private String name;

    public Var(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public double eval(Map<String, Double> map) {
       if (map.containsKey(name)) {
           return map.get(name);
       }

       return 0.0;
    }

    @Override
    public Set<String> getVars() {
        return Set.of(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
