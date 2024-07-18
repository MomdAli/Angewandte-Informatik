package aufgabe1;

public class LinkedListFrequencyTable extends AbstractFrequencyTable {

    private Node begin;
    private Node end;
    private int size;

    public LinkedListFrequencyTable() {
        clear();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        begin = new Node(new Word("", 0), end, null);
        end = new Node(new Word("", 0), null, begin);
        size = 0;
    }

    @Override
    public void add(String w, int f) {
        //if List is Empty
        if (size == 0) {
            Node r = new Node(new Word(w, f), end, begin);
            begin.next = r;
            end.prev = r;
            size++;
            return;
        }

        // if word exists
        Node p = begin.next;
        while (p != end) {
            if (p.wort.getWord().equals(w)) {
                p.wort.addFrequency(f);
                moveToLeft(p);
                return;
            }
            p = p.next;
        }

        //create new Node and sort it
        p = begin;
        while (p.next != end && p.next.wort.getFrequency() > f) {
            p = p.next;
        }

        Node r = new Node(new Word(w, f), p.next, p);
        r.next.prev = r;
        p.next = r;
        size++;
    }

    private void moveToLeft(Node r) {

        //Disconnect Nodes
        r.prev.next = r.next;
        r.next.prev = r.prev;

        Node p = r.prev;
        while (p != begin && p.wort.getFrequency() < r.wort.getFrequency()) {
            p = p.prev;
        }

        r.next = p.next;
        r.prev = p;
        r.next.prev = r;
        p.next = r;
    }

    @Override
    public Word get(int pos) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();

        Node p;
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

        return p.wort;
    }

    @Override
    public int get(String w) {
        Node p = begin;
        while (p.next != null) {
            if (p.wort.getWord().equals(w)) {
                return p.wort.getFrequency();
            }
            p = p.next;
        }

        return 0;
    }

    private static class Node {
        Word wort;
        Node next;
        Node prev;

        Node(Word w, Node n, Node p) {
            wort = w;
            next = n;
            prev = p;
        }
    }
}
