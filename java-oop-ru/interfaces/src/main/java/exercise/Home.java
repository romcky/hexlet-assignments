package exercise;

// BEGIN
public interface Home {

    double getArea();

    default int compareTo(Home h) {
        if (getArea() < h.getArea()) {
            return -1;
        } else if (getArea() > h.getArea()) {
            return 1;
        } else {
            return 0;
        }
    }
}

// END
