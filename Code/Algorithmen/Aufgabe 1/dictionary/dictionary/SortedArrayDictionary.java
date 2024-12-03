package dictionary;

import java.util.Arrays;
import java.util.Iterator;

public class SortedArrayDictionary<K extends Comparable<? super K>, V>
        implements Dictionary<K, V> {

    private static final int DEF_CAPACITY = 100;
    private int size;
    private Entry<K, V>[] data;

    @SuppressWarnings("unchecked")
    public SortedArrayDictionary() {
        size = 0;
        data = new Entry[DEF_CAPACITY];
    }

    @Override
    public V search(K key) {
        int i = searchKey(key);
        if (i < 0) {
            return null; // key not found
        } else {
            return data[i].getValue();
        }
    }

    private int searchKey(K key) {
        int li = 0;
        int re = size - 1;

        while (re >= li) {
            int m = (li + re) / 2;
            if (key.compareTo(data[m].getKey()) < 0) {
                re = m - 1;
            } else if (key.compareTo(data[m].getKey()) > 0) {
                li = m + 1;
            } else {
                return m; // key found
            }
        }

        return -1; // key not found
    }

    @Override
    public V remove(K key) {
        int i = searchKey(key);
        if (i < 0) {
            return null; // key not found
        } else {
            V r = data[i].getValue();
            for (int j = i; j < size - 1; j++) {
                data[j] = data[j + 1];
            }
            size--;
            return r;
        }
    }

    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);

        // replace value if key already exists
        if (i != -1) {
            V r = data[i].getValue();
            data[i].setValue(value);
            return r;
        }

        // increase array size if necessary
        if (data.length == size) {
            data = Arrays.copyOf(data, size * 2);
        }

        // insert new Entry
        int j = size - 1;
        while (j >= 0 && key.compareTo(data[j].getKey()) < 0) {
            data[j + 1] = data[j];
            j--;
        }
        data[j + 1] = new Entry<K, V>(key, value);
        size++;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Dictionary.Entry<K, V>> iterator() {
        return new Iterator<Dictionary.Entry<K, V>>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public Dictionary.Entry<K, V> next() {
                return data[pos++];
            }
        };
    }
}
