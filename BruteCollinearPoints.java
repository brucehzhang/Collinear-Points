import java.util.Arrays;
import edu.princeton.cs.algs4.ResizingArrayBag;

public class BruteCollinearPoints {
    private final ResizingArrayBag<LineSegment> lineSegments = new ResizingArrayBag<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Array is null");
        }
        Arrays.sort(points);
        for (int x = 0; x < points.length; x++) {
            if (points[x] == null) {
                throw new IllegalArgumentException("Array contains is null");
            } else if (x > 0) {
                if (points[x-1].compareTo((points[x])) == 0) {
                    throw new IllegalArgumentException("Array contains a duplicate");
                }
            }
        }
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {

                            Point first = null;
                            Point second = null;

                            if (points[i].compareTo(points[j]) + points[i].compareTo(points[k]) + points[i].compareTo(points[l]) == 3) {
                                first = points[i];
                            } else if (points[j].compareTo(points[i]) + points[j].compareTo(points[k]) + points[j].compareTo(points[l]) == 3) {
                                first = points[j];
                            } else if (points[k].compareTo(points[j]) + points[k].compareTo(points[i]) + points[k].compareTo(points[l]) == 3) {
                                first = points[k];
                            } else {
                                first = points[l];
                            }

                            if (points[i].compareTo(points[j]) + points[i].compareTo(points[k]) + points[i].compareTo(points[l]) == -3) {
                                second = points[i];
                            } else if (points[j].compareTo(points[i]) + points[j].compareTo(points[k]) + points[j].compareTo(points[l]) == -3) {
                                second = points[j];
                            } else if (points[k].compareTo(points[j]) + points[k].compareTo(points[i]) + points[k].compareTo(points[l]) == -3) {
                                second = points[k];
                            } else {
                                second = points[l];
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
