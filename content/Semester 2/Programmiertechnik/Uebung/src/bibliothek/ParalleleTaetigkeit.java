package bibliothek;

public class ParalleleTaetigkeit extends ZusammengesetzteTaetigkeit {

    @Override
    public double getTime() {
        double t = 0;
        for (int i = 0; i < meineTaetigkeiten.size(); i++) {
            if (t < meineTaetigkeiten.get(i).getTime())
                t = meineTaetigkeiten.get(i).getTime();
        }
        return t;
    }
}
