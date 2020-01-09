import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import edu.princeton.cs.algs4.ResizingArrayBag;

public class FastCollinearPoints {
  private ResizingArrayBag<LineSegment> lineSegments = new ResizingArrayBag<>();

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
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

    Point[] sortCopy = pointsCopy.clone();

    for (int i = 0; i < pointsCopy.length; i++) {
      Arrays.sort(sortCopy);
      Arrays.sort(sortCopy, pointsCopy[i].slopeOrder());
      int copyIndex = 0;
      int countSegments = 1;
      while (copyIndex < sortCopy.length - 1) {
        if (pointsCopy[i].slopeTo(sortCopy[copyIndex]) == pointsCopy[i].slopeTo(sortCopy[copyIndex + 1])) {
          countSegments++;
          copyIndex++;
          if (copyIndex == sortCopy.length - 1 && countSegments >= 3 && pointsCopy[i].compareTo(sortCopy[copyIndex - countSegments + 1]) < 0) {
            Point minSegment = pointsCopy[i];
            Point maxSegment = sortCopy[copyIndex];
            lineSegments.add(new LineSegment(minSegment, maxSegment));
          }
        } else {
          // If there are 3 or more points with same slope to point[i] and next point does not have same slope, draw line segment.
          if (countSegments >= 3 && pointsCopy[i].compareTo(sortCopy[copyIndex - countSegments + 1]) < 0) {
            Point minSegment = pointsCopy[i];
            Point maxSegment = sortCopy[copyIndex];
            lineSegments.add(new LineSegment(minSegment, maxSegment));
          }
          countSegments = 1;
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