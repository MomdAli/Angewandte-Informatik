package bibliothek;

public class SerielleTaetigkeit extends ZusammengesetzteTaetigkeit {

    @Override
    public double getTime() {
        double t = 0;
        for (int i = 0; i < meineTaetigkeiten.size(); i++) {
            t += meineTaetigkeiten.get(i).getTime();
        }
        return t;
    }
}
