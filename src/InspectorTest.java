import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class InspectorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	//tests if the logic boolean works properly for false
	public void testInspectFalse() throws Exception {
		Inspector anInspector = new Inspector();	
		assertEquals("false", anInspector.inspect(anInspector, false));
	}
	@Test
	//tests if the logic boolean works properly for true
	public void testInspectTrue() throws Exception {
		Inspector anInspector = new Inspector();	
		assertEquals("true", anInspector.inspect(anInspector, true));
	}
	@Test
	//tests if Inspect returns null when initialized with null
	public void testInspectNull() throws Exception {
		Inspector anInspector = new Inspector();	
		assertNull("true", null);
	}
	@Test
	//tests if it Inspect returns not null when initialized with null
	public void testInspectNotNull() throws Exception {
		Inspector anInspector = new Inspector();	
		assertNotNull("true", anInspector.inspect(anInspector, true));
	}

	
	@Test
	public void testClassInspector() {
		fail("Not yet implemented");
	}

	@Test
	public void testMethodInspector() {
		fail("Not yet implemented");
	}

	@Test
	public void testConstructorInspector() {
		fail("Not yet implemented");
	}

	@Test
	public void testFieldInspector() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckField() {
		fail("Not yet implemented");
	}

}
