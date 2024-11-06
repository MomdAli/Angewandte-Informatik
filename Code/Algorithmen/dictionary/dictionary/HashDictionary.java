package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashDictionary<K, V> implements Dictionary<K, V> {

    private int size;
    private Node<K, V>[] data;
    private static final int DEF_CAPACITY = 31;

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Dictionary.Entry<K, V> getEntry() {
            return new Dictionary.Entry<K, V>(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    public HashDictionary() {
        data = new Node[DEF_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashDictionary(int capacity) {
        data = new Node[capacity];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldData = data;
        data = new Node[Helper.nextPrime(data.length * 2)];
        size = 0;
        for (Node<K, V> e : oldData) {
            while (e != null) {
                insert(e.key, e.value);
                e = e.next;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V search(K key) {
        int i = searchKey(key);
        if (i == -1) {
            return null;
        }

        Node<K, V> e = data[i];
        while (e != null) {
            if (e.key.equals(key)) {
                return e.value;
            }
            e = e.next;
        }
        return null; // should never reach this point
    }

    private int searchKey(K key) {
        int h = hash(key);
        Node<K, V> e = data[h];
        while (e != null) {
            if (e.key.equals(key)) {
                return h;
            }
            e = e.next;
        }
        return -1;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % data.length;
    }

    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);
        if (i >= 0) {
            V r = data[i].value;
            data[i].value = value;
            return r;
        }

        if (size / data.length > 2) {
            resize();
        }

        size++;
        int h = hash(key);

        // insert if empty
        if (data[h] == null) {
            data[h] = new Node<>(key, value, null);
            return null;
        }

        // insert if list is not empty at the beginning
        Node<K, V> e = new Node<>(key, value, data[h]);
        data[h] = e;
        return null;
    }

    @Override
    public V remove(K key) {
        int i = searchKey(key);
        if (i == -1) {
            return null;
        }

        size--;

        if (data[i].next == null) {
            V r = data[i].value;
            data[i] = null;
            return r;
        }

        Node<K, V> p = data[i];
        Node<K, V> r = p;
        while (!p.key.equals(key)) {
            r = p;
            p = p.next;
        }

        r.next = p.next;
        p.next = null;
        return p.value;
    }

    @Override
    public Iterator<Dictionary.Entry<K, V>> iterator() {
        return new Iterator<Dictionary.Entry<K, V>>() {
            private int pos = 0;
            private Node<K, V> current = null;

            @Override
            public boolean hasNext() {
                if (current != null && current.next != null) {
                    return true;
                }
                for (int i = pos; i < data.length; i++) {
                    if (data[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Dictionary.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (current != null && current.next != null) {
                    current = current.next;
                    return current.getEntry();
                }

                // Move to the next non-null bucket
                while (pos < data.length && data[pos] == null) {
                    pos++;
                }
                current = data[pos];
                pos++; // Increment pos only after moving to the next bucket
                return current.getEntry();
            }
        };
    }
}
