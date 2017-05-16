package by.Homework2;

public class Coordinates {
  public static void main(String[] args) {
    Point pointFirst = new Point(2.8, 4.9);
    Point pointSecond = new Point(4.9, 8.1);
    Point point = new Point();
    System.out.println("Растояние между точками: " + point.distance(pointFirst, pointSecond));
  }
}
