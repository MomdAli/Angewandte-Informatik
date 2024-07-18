package pythagorastree;

import java.util.Random;

public class Steuerung {

    private final static double DELTA = 30;
    private final static Random RAND = new Random();

    public static void main(String[] args) {
        StdDraw.setCanvasSize(1024,1024);
        StdDraw.setPenRadius(.0005);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.show(0);
//        drawPyTree1(new Point(0.6, 0.1), .17, 0, 20);
        drawPyTree2(new Point(0.5, 0.2), .1, 0.05, 0, 20);
        StdDraw.show(0);
    }

    public static void drawPyTree1(Point p, double w, double gamma, int n) {
        double gammaRad = Math.toRadians(gamma);
        double deltaRad = Math.toRadians(DELTA);
        double s = w * Math.sin(gammaRad);
        double t = w * Math.cos(gammaRad);

        Point a = p.copy();
        Point b = p.copy(t, s);
        Point c = p.copy(t - s, s + t);
        Point d = p.copy(-s, t);

        StdDraw.line(a.getX(), a.getY(), d.getX(), d.getY());
        StdDraw.line(b.getX(), b.getY(), c.getX(), c.getY());

        if (n > 0) {
            n--;
            double u = w * Math.cos(deltaRad);
            double v = w * Math.sin(deltaRad);
            Point e = d.copy(u * Math.cos(deltaRad + gammaRad),
                    u * Math.sin(deltaRad + gammaRad));

            if (n < 2)
                StdDraw.setPenColor(StdDraw.GREEN);

            drawPyTree1(d, u, gamma + DELTA, n);
            drawPyTree1(e, v, gamma - (90 - DELTA), n);
            StdDraw.setPenColor(StdDraw.BOOK_RED);
        }
    }

    public static void drawPyTree2(Point p, double w, double lastHeight, double gamma, int n) {
        double gammaRad = Math.toRadians(gamma);
        double betaRad = Math.toRadians(90 - gamma);

        double h = RAND.nextDouble(lastHeight * 0.6) + w;

        double q = w * Math.cos(gammaRad);
        double r = w * Math.sin(gammaRad);
        double s = h * Math.sin(betaRad);
        double t = h * Math.cos(betaRad);

        Point a = p.copy();
        Point b = p.copy(q, r);
        Point c = p.copy(q - t, r + s);
        Point d = p.copy(-t, s);


        if (n < 3)
            StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(a.getX(), a.getY(), d.getX(), d.getY());
        StdDraw.line(b.getX(), b.getY(), c.getX(), c.getY());
        StdDraw.setPenColor(StdDraw.BOOK_RED);

        if (n > 0) {
            n--;
            double delta = RAND.nextDouble(20) + 35;
            double deltaRad = Math.toRadians(delta);

            double u = w * Math.cos(deltaRad);
            double v = w * Math.sin(deltaRad);
            Point e = d.copy(u * Math.cos(deltaRad + gammaRad),
                    u * Math.sin(deltaRad + gammaRad));

            drawPyTree2(d, u, h, gamma + delta, n);
            drawPyTree2(e, v, h, gamma - (90 - delta), n);
            StdDraw.setPenColor(StdDraw.BOOK_RED);
        }
    }
}
