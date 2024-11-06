// O. Bittel
// 22.09.2022
package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the Dictionary interface as AVL tree.
 * <p>
 * The entries are ordered using their natural ordering on the keys,
 * or by a Comparator provided at set creation time, depending on which
 * constructor is used.
 * <p>
 * An iterator for this dictionary is implemented by using the parent node
 * reference.
 *
 * @param <K>
 *        Key.
 * @param <V>
 *        Value.
 */
public class BinaryTreeDictionary<K extends Comparable<? super K>, V>
        implements Dictionary<K, V> {

    static private class Node<K, V> {
        K key;
        V value;
        int height;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        Node(K k, V v) {
            key = k;
            value = v;
            height = 0;
            left = null;
            right = null;
            parent = null;
        }

        Dictionary.Entry<K, V> getEntry() {
            return new Dictionary.Entry<K, V>(key, value);
        }
    }


    private static class MinEntry<K, V> {
        K key;
        V value;
    }

    private Node<K, V> getRemMinR(Node<K, V> p, MinEntry<K, V> min) {
        if (p.left == null) {
            min.key = p.key;
            min.value = p.value;
            return p.right;
        } else {
            p.left = getRemMinR(p.left, min);
            p.height = 1 + Math.max(height(p.left), height(p.right));
            p = balance(p);
            return p;
        }
    }

    private Node<K, V> root = null;
    private int size = 0;
    private V oldValue = null;

    @Override
    public V search(K key) {
        return searchR(key, root);
    }

    private V searchR(K key, Node<K, V> p) {
        if (p == null)
            return null;
        else if (key.compareTo(p.key) < 0)
            return searchR(key, p.left);
        else if (key.compareTo(p.key) > 0)
            return searchR(key, p.right);
        else
            return p.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V insert(K key, V value) {
        root = insertR(key, value, root);
        return oldValue;
    }

    private Node<K, V> insertR(K key, V value, Node<K, V> p) {
        if (p == null) {
            p = new Node<>(key, value);
            size++;
            oldValue = p.value;
            return p;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = insertR(key, value, p.left);
            p.left.parent = p;
        } else if (cmp > 0) {
            p.right = insertR(key, value, p.right);
            p.right.parent = p;
        } else {
            oldValue = p.value;
            p.value = value;
        }

        p.height = 1 + Math.max(height(p.left), height(p.right));
        p = balance(p);
        return p;
    }

    @Override
    public V remove(K key) {
        root = removeR(key, root);
        return oldValue;
    }

    private Node<K, V> removeR(K key, Node<K, V> p) {
        if (p == null) {
            oldValue = null;
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = removeR(key, p.left);
            if (p.left != null)
                p.left.parent = p;
        } else if (cmp > 0) {
            p.right = removeR(key, p.right);
            if (p.right != null)
                p.right.parent = p;
        } else {
            oldValue = p.value;
            if (p.left == null) {
                size--;
                return p.right;
            } else if (p.right == null) {
                size--;
                return p.left;
            } else {
                MinEntry<K, V> min = new MinEntry<>();
                p.right = getRemMinR(p.right, min);
                p.key = min.key;
                p.value = min.value;
            }
        }

        p.height = 1 + Math.max(height(p.left), height(p.right));
        p = balance(p);
        return p;
    }

    private int height(Node<K, V> p) {
        return p == null ? -1 : p.height;
    }

    private Node<K, V> leftMostDescendant(Node<K, V> p) {
        assert p != null;
        while (p.left != null)
            p = p.left;
        return p;
    }

    private Node<K, V> parentOfLeftMostAncestor(Node<K, V> p) {
        assert p != null;
        while (p.parent != null && p.parent.right == p)
            p = p.parent;
        return p.parent; // null if p is root
    }

    private Node<K, V> balance(Node<K, V> p) {
        if (p == null)
            return null;
        p.height = 1 + Math.max(height(p.left), height(p.right));
        if (getBalance(p) == -2) {
            if (getBalance(p.left) <= 0) {
                p = rotateRight(p);
            } else {
                p = rotateLeftRight(p);
            }
        } else if (getBalance(p) == 2) {
            if (getBalance(p.right) >= 0) {
                p = rotateLeft(p);
            } else {
                p = rotateRightLeft(p);
            }
        }

        return p;
    }

    private Node<K, V> rotateRight(Node<K, V> p) {
        Node<K, V> q = p.left;
        p.left = q.right;
        if (q.right != null)
            q.right.parent = p;
        q.right = p;
        q.parent = p.parent;
        p.parent = q;
        p.height = 1 + Math.max(height(p.left), height(p.right));
        q.height = 1 + Math.max(height(q.left), height(q.right));
        return q;
    }

    private Node<K, V> rotateLeft(Node<K, V> p) {
        Node<K, V> q = p.right;
        p.right = q.left;
        if (q.left != null)
            q.left.parent = p;
        q.left = p;
        q.parent = p.parent;
        p.parent = q;
        p.height = 1 + Math.max(height(p.left), height(p.right));
        q.height = 1 + Math.max(height(q.left), height(q.right));
        return q;
    }

    private Node<K, V> rotateRightLeft(Node<K, V> p) {
        p.right = rotateRight(p.right);
        return rotateLeft(p);
    }

    private Node<K, V> rotateLeftRight(Node<K, V> p) {
        p.left = rotateLeft(p.left);
        return rotateRight(p);
    }

    private int getBalance(Node<K, V> p) {
        return (p == null) ? 0 : height(p.right) - height(p.left);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            Node<K, V> next = leftMostDescendant(root);

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Entry<K, V> next() {
                if (next == null)
                    throw new NoSuchElementException();
                Node<K, V> r = next;
                if (r.right != null)
                    next = leftMostDescendant(r.right);
                else
                    next = parentOfLeftMostAncestor(r);
                return r.getEntry();
            }
        };
    }

    /**
     * Pretty prints the tree
     */
    public void prettyPrint() {
        printR(0, root);
    }

    private void printR(int level, Node<K, V> p) {
        printLevel(level);
        if (p == null) {
            System.out.println("#");
        } else {
            System.out.println(p.key + " " + p.value + "^"
                    + ((p.parent == null) ? "null" : p.parent.key.toString()));
            if (p.left != null || p.right != null) {
                printR(level + 1, p.left);
                printR(level + 1, p.right);
            }
        }
    }

    private static void printLevel(int level) {
        if (level == 0) {
            return;
        }
        for (int i = 0; i < level - 1; i++) {
            System.out.print("   ");
        }
        System.out.print("|__");
    }
}
