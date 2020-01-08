import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import edu.princeton.cs.algs4.ResizingArrayBag;

public class FastCollinearPoints {
  private final ResizingArrayBag<LineSegment> lineSegments = new ResizingArrayBag<>();

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
        if (points[x-1].compareTo((points[x])) == 0) {
          throw new IllegalArgumentException("Array contains a duplicate");
        }
      }
    }

    Point[] copyPoints = points.clone();

    for (int i = 0; i < points.length; i++) {
      Arrays.sort(copyPoints, points[i].slopeOrder());
      int copyIndex = 0;
      int countSegments = 0;
      while (copyIndex < copyPoints.length - 1) {
        if (points[i].slopeTo(copyPoints[copyIndex]) == points[i].slopeTo(copyPoints[copyIndex + 1])) {
          countSegments++;
          copyIndex++;
          if (copyIndex == copyPoints.length - 1 && countSegments >= 3) {
            Point minSegment = points[i];
            Point maxSegment = copyPoints[copyIndex];
            lineSegments.add(new LineSegment(minSegment, maxSegment));
          }
        } else {
          // If there are 3 or more points with same slope to point[i] and next point does not have same slope, draw line segment.
          if (countSegments >= 3) {
            Point minSegment = points[i];
            Point maxSegment = copyPoints[copyIndex];
            lineSegments.add(new LineSegment(minSegment, maxSegment));
          }
          countSegments = 0;
          copyIndex++;
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

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}