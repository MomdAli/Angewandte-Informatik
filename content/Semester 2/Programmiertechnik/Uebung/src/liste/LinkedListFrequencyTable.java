package liste;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private Node<T> begin;
    private Node<T> end;
    private int size;

    public LinkedListFrequencyTable() {
        clear();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public final void clear() {
        begin = new Node<T>(new Element<T>(null, 0), null, null);
        end = new Node<T>(new Element<T>(null, 0), null, begin);
        begin.next = end;
        size = 0;
    }

    @Override
    public void add(T w, int f) {
        //if List is Empty
        if (size == 0) {
            Node<T> r = new Node<T>(new Element<T>(w, f), end, begin);
            begin.next = r;
            end.prev = r;
            size++;
            return;
        }

        // if Element exists
        Node<T> p = begin.next;
        while (p != end) {
            if (p.element.getElement().equals(w)) {
                p.element.addFrequency(f);
                moveToLeft(p);
                return;
            }
            p = p.next;
        }

        //create new Node and sort it
        p = begin;
        while (p.next != end && p.next.element.getFrequency() > f) {
            p = p.next;
        }

        Node<T> r = new Node<T>(new Element<T>(w, f), p.next, p);
        r.next.prev = r;
        p.next = r;
        size++;
    }

    private void moveToLeft(Node<T> r) {

        //Disconnect Nodes
        r.prev.next = r.next;
        r.next.prev = r.prev;

        Node<T> p = r.prev;
        while (p != begin && p.element.getFrequency() < r.element.getFrequency()) {
            p = p.prev;
        }

        r.next = p.next;
        r.prev = p;
        r.next.prev = r;
        p.next = r;
    }

    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();

        Node<T> p;
        if (pos <= (size / 2)) {
            p = begin;
            for (int i = 0; i <= pos; i++) {
                p = p.next;
            }
        } else {
            p = end;
            for (int i = 0; i < (size - pos); i++) {
                p = p.prev;
            }
        }

        return p.element;
    }

    @Override
    public int get(T w) {
        Node<T> p = begin.next;
        while (p.next != null) {
            if (p.element.getElement().equals(w)) {
                return p.element.getFrequency();
            }
            p = p.next;
        }

        return 0;
    }

    private static class Node<T> {
        Element<T> element;
        Node<T> next;
        Node<T> prev;

        Node(Element<T> w, Node<T> n, Node<T> p) {
            element = w;
            next = n;
            prev = p;
        }
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return new LinkedListFrequencyTableIterator();
    }

    private class LinkedListFrequencyTableIterator implements Iterator<Element<T>> {

        Node<T> current = begin;

        @Override
        public boolean hasNext() {
            return current.next != end && current.next != null;
        }

        @Override
        public Element<T> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            current = current.next;
            return current.element;
        }
    }
}
