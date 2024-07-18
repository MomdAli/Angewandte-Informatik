package pythagorastree;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point copy(double offsetX, double offsetY) {
        return new Point(x + offsetX, y + offsetY);
    }

    public Point copy() {
        return new Point(x, y);
    }

    public void moveX(double offset) {
        this.x += offset;
    }

    public void moveY(double offset) {
        this.y += offset;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(X: %.2f, Y: %.2f)", x, y);
    }
}
