import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testNeighbours() {
        Coordinate c1 = new Coordinate(0, 0);
        Coordinate c2 = new Coordinate(1, 1);
        Coordinate c3 = new Coordinate(2, 2);
        Coordinate c4 = new Coordinate(0, 1);
        Coordinate c5 = new Coordinate(1, 0);
        Coordinate c6 = new Coordinate(2, 0);

        assertTrue(c1.neighbours(c2));
        assertTrue(c2.neighbours(c1));
        assertFalse(c1.neighbours(c3));
        assertTrue(c2.neighbours(c3));
        assertTrue(c1.neighbours(c4));
        assertTrue(c1.neighbours(c5));
        assertFalse(c1.neighbours(c6));
    }
    public static void main(String[] args) {
        CoordinateTest test = new CoordinateTest();
        test.testNeighbours();
    }
}