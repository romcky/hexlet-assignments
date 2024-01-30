package exercise;

// BEGIN
public class Segment {
    private final Point a;
    private final Point b;

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getBeginPoint() {
        return a;
    }

    public Point getEndPoint() {
        return b;
    }

    public Point getMidPoint() {
        return new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
    }
}

// END
