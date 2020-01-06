import java.util.Arrays;


public class FastCollinearPoints {

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Array is null");
    }
    Arrays.sort(points);
    for (int x = 0; x < points.length; x++) {
      if (points[x] == null) {
        throw new IllegalArgumentException("Array contains is null");
      } else if (x > 0) {
        if (points[x-1].toString().equals(points[x].toString())) {
          throw new IllegalArgumentException("Array contains a duplicate");
        }
      }
    }

    Point[] copyPoints = new Point[points.length];

    for (int x = 0; x < points.length; x++) {
      copyPoints[x] = points[x];
    }

    for (int i = 0; i < points.length; i++) {
      Arrays.sort(copyPoints, points[i].slopeOrder());
    }
  }

  // the number of line segments
  public int numberOfSegments() {

  }

  // the line segments
  public LineSegment[] segments() {

  }
}