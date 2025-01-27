package test;

public class Aufgabe1 {

    static class Node {
        private int data;
        private Node left = null;
        private Node right = null;

        public Node(int d, Node l, Node r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }

        public Node(int d) {
            this.data = d;
        }
    }

    public static void main(String[] args) {
        Node t = new Node(7, new Node(5), new Node(9));
        t.left.right = new Node(4);

        Node p = t.left;
        p.left = new Node(1);
        p.left = new Node(3, p.left, null);

        for (p = t; p.left != null; p = p.left) {
        }
        System.out.println(p.data);
    }
}