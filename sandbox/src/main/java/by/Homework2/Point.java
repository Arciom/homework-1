package by.Homework2;

import java.lang.Math.*;

public class Point {

  public double x;
  public double y;
  double distance;
  public Point(){};

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p1, Point p2){
    return distance = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
  }
}
