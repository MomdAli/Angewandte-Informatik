
public class TelVerbindung {

    TelKnoten start;
    TelKnoten end;
    int cost;

    public TelVerbindung(TelKnoten start, TelKnoten end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return start + " -> " + end + " (" + cost + ")";
    }
}
