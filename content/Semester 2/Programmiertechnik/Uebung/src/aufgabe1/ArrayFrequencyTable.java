package aufgabe1;

import java.util.Arrays;

/**
 * Realisiert eine Häufigkeitstabelle als Feld.
 * @author Mohammed Ali Al-Saiaf
 */
public class ArrayFrequencyTable extends AbstractFrequencyTable {

    private int size = 0;
    private Word[] fqTable;
    private final int DEFAULT_SIZE = 100;

    public ArrayFrequencyTable() {
        clear();
    }

    private void moveToLeft(int pos) {
        Word w = fqTable[pos];
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

    @Override
    public final void clear() {
        fqTable = new Word[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(String w, int f) {
        if (size >= fqTable.length)
            fqTable = Arrays.copyOf(fqTable, size * 2);

        for (int i = 0; i < size; i++) {
            if (fqTable[i].getWord().equals(w)) {
                fqTable[i].addFrequency(f);
                moveToLeft(i);
                return;
            }
        }

        fqTable[size] = new Word(w, f);
        moveToLeft(size);
        size++;
    }

    @Override
    public Word get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        return fqTable[pos];
    }

    @Override
    public int get(String w) {
        for (int i = 0; i < size; i++) {
            if (fqTable[i].getWord().equals(w))
                return fqTable[i].getFrequency();
        }
        return 0;
    }
}