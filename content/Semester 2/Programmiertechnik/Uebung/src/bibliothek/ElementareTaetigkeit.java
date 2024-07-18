package bibliothek;

public class ElementareTaetigkeit implements Taetigkeit {

    private double time;
    private String beschr;

    public ElementareTaetigkeit(String beschr, double time) {
        this.time = time;
        this.beschr = beschr;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public void add(Taetigkeit tk) {
    }

    @Override
    public void remove(Taetigkeit tk) {
    }

    @Override
    public int getAnzahl() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", beschr, time);
    }
}
