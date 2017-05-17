package by.Homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by arciom on 17.05.2017.
 */
public class PointTest {
  @Test
  public void rightResultTestDistance() {
    Point p1 = new Point(2.8, 6.9);
    Point p2 = new Point(6.9, 8.1);
    Assert.assertEquals(p1.distance(p2), 4.272001872658766);
  }
  @Test
  public void wrongResultTestDistance() {
    Point p1 = new Point(2.8, 6.9);
    Point p2 = new Point(6.9, 8.1);
    Assert.assertEquals(p1.distance(p2), 6.272001872658766);
  }
  @Test
  public void nullResultTestDistance() {
    Point p1 = new Point(0.0, 0.0);
    Point p2 = new Point(0.0, 0.0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }
}
