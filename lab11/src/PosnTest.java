import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class PosnTest {

	@Ignore
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPosn(){
        assertEquals(Math.sqrt(16), 4.0, 0.1);
        assertEquals(Math.pow(2, 10), 1024.0, 0.1);
        assertEquals(new Posn(2, 4), new Posn(2, 4));
        
        // Tests for the Dist method
        assertEquals(new Posn(2, 4).dist(new Posn(5, 8)), 5.0, 1.0);

        
        // Tests for the Move method
        Posn p1 = new Posn(0, 0);
        p1.move(3.0, 4.0);
        Posn p2 = new Posn(3.0, 4.0);
        assertEquals(p1, p2);
    }              

}
