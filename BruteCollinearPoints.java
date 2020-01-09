import java.util.Arrays;
import edu.princeton.cs.algs4.ResizingArrayBag;

public class BruteCollinearPoints {
    private final ResizingArrayBag<LineSegment> lineSegments = new ResizingArrayBag<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Array is null");
        }
        for (int x = 0; x < points.length; x++) {
            if (points[x] == null) {
                throw new IllegalArgumentException("Array contains null");
            }
        }
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int x = 0; x < pointsCopy.length; x++) {
            if (x > 0) {
                if (pointsCopy[x-1].compareTo((pointsCopy[x])) == 0) {
                    throw new IllegalArgumentException("Array contains a duplicate");
                }
            }
        }
        for (int i = 0; i < pointsCopy.length - 3; i++) {
            for (int j = i + 1; j < pointsCopy.length - 2; j++) {
                for (int k = j + 1; k < pointsCopy.length - 1; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k]) && pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[l])) {

                            Point first = null;
                            Point second = null;

                            if (pointsCopy[i].compareTo(pointsCopy[j]) + pointsCopy[i].compareTo(pointsCopy[k]) + pointsCopy[i].compareTo(pointsCopy[l]) == 3) {
                                first = pointsCopy[i];
                            } else if (pointsCopy[j].compareTo(pointsCopy[i]) + pointsCopy[j].compareTo(pointsCopy[k]) + pointsCopy[j].compareTo(pointsCopy[l]) == 3) {
                                first = pointsCopy[j];
                            } else if (pointsCopy[k].compareTo(pointsCopy[j]) + pointsCopy[k].compareTo(pointsCopy[i]) + pointsCopy[k].compareTo(pointsCopy[l]) == 3) {
                                first = pointsCopy[k];
                            } else {
                                first = pointsCopy[l];
                            }

                            if (pointsCopy[i].compareTo(pointsCopy[j]) + pointsCopy[i].compareTo(pointsCopy[k]) + pointsCopy[i].compareTo(pointsCopy[l]) == -3) {
                                second = pointsCopy[i];
                            } else if (pointsCopy[j].compareTo(pointsCopy[i]) + pointsCopy[j].compareTo(pointsCopy[k]) + pointsCopy[j].compareTo(pointsCopy[l]) == -3) {
                                second = pointsCopy[j];
                            } else if (pointsCopy[k].compareTo(pointsCopy[j]) + pointsCopy[k].compareTo(pointsCopy[i]) + pointsCopy[k].compareTo(pointsCopy[l]) == -3) {
                                second = pointsCopy[k];
                            } else {
                                second = pointsCopy[l];
                            }
                            LineSegment segment = new LineSegment(first, second);
                            lineSegments.add(segment);
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] returnSegments = new LineSegment[numberOfSegments()];
        int i = 0;
        for (LineSegment ls : lineSegments) {
            returnSegments[i] = ls;
            i++;
        }
        return returnSegments;
    }
}
