package liste;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Realisiert eine Häufigkeitstabelle als Feld.
 * @author Mohammed Ali Al-Saiaf
 */
public class ArrayFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private int size = 0;
    private Element<T>[] fqTable;
    private final int DEFAULT_SIZE = 100;

    public ArrayFrequencyTable() {
        clear();
    }

    private void moveToLeft(int pos) {
        Element<T> w = fqTable[pos];
        int i = pos - 1;
        while (i >= 0 && fqTable[i].getFrequency() < w.getFrequency()) {
            fqTable[i + 1] = fqTable[i];
            i--;
        }
        fqTable[i + 1] = w;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void clear() {
        fqTable = new Element[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T w, int f) {
        if (size >= fqTable.length)
            fqTable = Arrays.copyOf(fqTable, size * 2);

        for (int i = 0; i < size; i++) {
            if (fqTable[i].getElement().equals(w)) {
                fqTable[i].addFrequency(f);
                moveToLeft(i);
                return;
            }
        }

        fqTable[size] = new Element<T>(w, f);
        moveToLeft(size);
        size++;
    }

    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        return fqTable[pos];
    }

    @Override
    public int get(Object w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getElement().equals(w))
                return fqTable[i].getFrequency();
        }
        return 0;
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return new ArrayFrequencyTableIterator();
    }

    private class ArrayFrequencyTableIterator implements Iterator<Element<T>> {

        int pos = -1;

        @Override
        public boolean hasNext() {
            if (pos + 1 >= size)
                return false;
            return fqTable[pos + 1] != null;
        }

        @Override
        public Element<T> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return fqTable[++pos];
        }
    }
}