package bibliothek;

import java.util.LinkedList;
import java.util.List;

public abstract class ZusammengesetzteTaetigkeit implements Taetigkeit {

    private int anzahl = 0;
    protected List<Taetigkeit> meineTaetigkeiten = new LinkedList<Taetigkeit>();

    @Override
    public abstract double getTime();

    @Override
    public void add(Taetigkeit tk) {
        if (!meineTaetigkeiten.contains(tk)) {
            meineTaetigkeiten.add(tk);
            anzahl += tk.getAnzahl();
        }
    }

    @Override
    public void remove(Taetigkeit tk) {
        if (!meineTaetigkeiten.contains(tk)) {
            meineTaetigkeiten.remove(meineTaetigkeiten.indexOf(tk));
            anzahl -= tk.getAnzahl();
        }
    }

    @Override
    public int getAnzahl() {
        return anzahl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < meineTaetigkeiten.size(); i++) {
            sb.append(meineTaetigkeiten.get(i).toString());

            if (i < meineTaetigkeiten.size() - 1)
                sb.append(", ");
        }
        sb.append("}");

        return sb.toString();
    }
}