
/**
 * @author Connor Flint
 * 
 * Test Suite that tests the methods in QueueWAR and Player
 *
 */
public class HockeyWARTests extends QueueWAR{
	
	/**
	 * Constructor for test suite
	 * 
	 * @param capacity
	 */
	public HockeyWARTests(int capacity) {
		super(capacity);
	}
	
	/**
	 * Tests testAddNewPlayer()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testAddNewPlayer() {
		QueueWAR queue = new QueueWAR(3);
		Player p1 = new Player("p1", 0, 0, 0);
		Player p2 = new Player("p2", 100, 100, 15);
		Player p3 = new Player("p3", 100, 100, 15);
		Player p4 = new Player("p4", 0, 100, 0);
		
		// Test 1: Player is null
		boolean testFailed = true;
		try {
			queue.addNewPlayer(null);
		} catch (NullPointerException n) {
			testFailed = false;
		}
		if (testFailed == true) {
			return false;
		}

		// Test 2: Should work correctly
		try {
			queue.addNewPlayer(p1);
		} catch (IndexOutOfBoundsException io1) {
			return false;
		}

		try {
			queue.addNewPlayer(p2);
		} catch (IndexOutOfBoundsException io2) {
			return false;
		}

		try {
			queue.addNewPlayer(p3);
		} catch (IndexOutOfBoundsException io3) {
			return false;
		}

		if (!queue.array[0].equals(p2)) {
			return false;
		}
		if (!queue.array[1].equals(p1)) {
			return false;
		}
		if (!queue.array[2].equals(p3)) {
			return false;
		}

		//Test 3: Array is full, should throw exception
		testFailed = true;
		try {
			queue.addNewPlayer(p4);
		} catch (IndexOutOfBoundsException i) {
			testFailed = false;
		}
		if (testFailed == true) {
			return false;
		}

		return true;
	}
	
	/**
	 * Tests testCheckHighestPlayer()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testCheckHighestPlayer() {
		QueueWAR queue = new QueueWAR(3);

		// Test 1: Queue is empty
		boolean testFailed = true;
		try {
			queue.checkHighestPlayer();
		} catch (IllegalStateException i) {
			testFailed = false;
		}
		if (testFailed == true) {
			return false;
		}

		Player p1 = new Player("p1", 0, 0, 0);
		Player p2 = new Player("p2", 100, 100, 15);
		Player p3 = new Player("p3", 100, 100, 15);
		
		// Test 2: Works correctly
		try {
			queue.addNewPlayer(p1);
		} catch (IndexOutOfBoundsException io1) {
			return false;
		}

		try {
			queue.addNewPlayer(p2);
		} catch (IndexOutOfBoundsException io2) {
			return false;
		}

		try {
			queue.addNewPlayer(p3);
		} catch (IndexOutOfBoundsException io3) {
			return false;
		}

		Player result;
		try {
			result = queue.checkHighestPlayer();
		} catch (IllegalStateException i) {
			return false;
		}
		if (!result.equals(p2)) {
			return false;
		}

		return true;
	}
	
	/**
	 * Tests removeBestPlayer()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testRemoveBestPlayer() {
		QueueWAR queue = new QueueWAR(3);

		// Test 1: Queue is empty
		boolean testFailed = true;
		try {
			queue.removeBestPlayer();
		} catch (IllegalStateException i) {
			testFailed = false;
		}
		if (testFailed == true) {
			return false;
		}
		
		Player p1 = new Player("p1", 0, 0, 0);
		Player p2 = new Player("p2", 100, 100, 15);
		Player p3 = new Player("p3", 200, 400, 0);

		// Test 2: Works correctly
		try {
			queue.addNewPlayer(p1);
		} catch (IndexOutOfBoundsException io1) {
			return false;
		}

		try {
			queue.addNewPlayer(p2);
		} catch (IndexOutOfBoundsException io2) {
			return false;
		}

		try {
			queue.addNewPlayer(p3);
		} catch (IndexOutOfBoundsException io3) {
			return false;
		}

		// remove player with highest WAR
		Player removed;
		try {
			removed = queue.removeBestPlayer();
		} catch (IllegalStateException i) {
			return false;
		}

		if (!removed.equals(p3)) {
			return false;
		}
		if (!queue.array[0].equals(p2)) {
			return false;
		}
		if (!queue.array[1].equals(p1)) {
			return false;
		}

		// remove player that now has highest WAR
		try {
			removed = queue.removeBestPlayer();
		} catch (IllegalStateException i) {
			return false;
		}

		if (!removed.equals(p2)) {
			return false;
		}
		if (!queue.array[0].equals(p1)) {
			return false;
		}

		// remove final player in queue
		try {
			removed = queue.removeBestPlayer();
		} catch (IllegalStateException i) {
			return false;
		}

		if (!removed.equals(p1)) {
			return false;
		}

		return true;
	}
	
	/**
	 * Tests testCheckQueueSize()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testCheckQueueSize() {
		QueueWAR queue = new QueueWAR(3);
		
		Player p1 = new Player("p1", 0, 0, 0);
		Player p2 = new Player("p2", 100, 100, 15);
		
		try {
			queue.addNewPlayer(p1);
		} catch (IndexOutOfBoundsException io1) {
			return false;
		}

		try {
			queue.addNewPlayer(p2);
		} catch (IndexOutOfBoundsException io2) {
			return false;
		}
		
		//Test 1: Checks that correct queue size is returned
		
		if (queue.checkQueueSize() == 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Tests parentOf()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testParentOf() {

		if (QueueWAR.parentOf(1) != 0) {
			return false;
		}

		if (QueueWAR.parentOf(6) != 2) {
			return false;
		}

		if (QueueWAR.parentOf(11) != 5) {
			return false;
		}

		return true;
	}

	/**
	 * Tests leftChildOf()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testLeftChildOf() {

		if (QueueWAR.leftChildOf(0) != 1) {
			return false;
		}

		if (QueueWAR.leftChildOf(4) != 9) {
			return false;
		}

		if (QueueWAR.leftChildOf(7) != 15) {
			return false;
		}

		return true;
	}

	/**
	 * Tests rightChildOf()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testRightChildOf() {

		if (QueueWAR.rightChildOf(0) != 2) {
			return false;
		}

		if (QueueWAR.rightChildOf(5) != 12) {
			return false;
		}

		if (QueueWAR.rightChildOf(3) != 8) {
			return false;
		}

		return true;
	}

	/**
	 * Tests testSwap()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testSwap() {
		// using a min heap to test the swap

		QueueWAR queue = new QueueWAR(7);

		Player[] testArray = new Player[7];

		Player p1 = new Player("p1", 1, 0, 0);
		Player p2 = new Player("p2", 2, 0, 0);
		Player p3 = new Player("p3", 3, 0, 0);
		Player p4 = new Player("p4", 4, 0, 0);
		
		
		testArray[0] = p1;
		testArray[1] = p2;
		testArray[2] = p3;
		testArray[3] = p4;

		queue.array = testArray;
		queue.size = 4;

		// Test 1: tests a good swap
		try {
			queue.swap(0, 2);
		} catch (Exception e) {
			return false;
		}

		if (!testArray[0].equals(p3)) {
			return false;
		}

		if (!testArray[2].equals(p1)) {
			return false;
		}

		// Test 2: tests a bad swap. Index less than 0.
		boolean passed = true;

		try {
			queue.swap(-1, 1);
		} catch (Exception e) {
			passed = true;
		}

		if (passed != true) {
			return false;
		}

		// Test 3: tests a bad swap. Index greater than or equal to size.
		passed = true;
		try {
			queue.swap(0, 5);
		} catch (Exception e) {
			passed = true;
		}

		if (passed != true) {
			return false;
		}

		return true;
	}

	/**
	 * Tests propagateUp()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testPropagateUp() {
		QueueWAR queue = new QueueWAR(7);

		Player[] testArray = new Player[7];

		Player p0 = new Player("p0", 0, 0, 0);
		Player p1 = new Player("p1", 1, 0, 0);
		Player p2 = new Player("p2", 2, 0, 0);
		Player p3 = new Player("p3", 3, 0, 0);
		Player p4 = new Player("p4", 4, 0, 0);
		Player p5 = new Player("p5", 5, 0, 0);
		Player p6 = new Player("p6", 6, 0, 0);
		
		// Largest value ("6") placed at end of array
		testArray[0] = p5;
		testArray[1] = p4;
		testArray[2] = p3;
		testArray[3] = p2;
		testArray[4] = p1;
		testArray[5] = p0;
		testArray[6] = p6;

		queue.array = testArray;
		queue.size = 7;

		// Test 1: Should work
		try {
			queue.propagateUp(6);
		} catch (Exception e) {
			return false;
		}

		if (!testArray[0].equals(p6)) {
			return false;
		}

		if (!testArray[1].equals(p4)) {
			return false;
		}

		if (!testArray[2].equals(p5)) {
			return false;
		}

		if (!testArray[3].equals(p2)) {

			return false;
		}

		if (!testArray[4].equals(p1)) {
			return false;
		}

		if (!testArray[5].equals(p0)) {
			return false;
		}

		if (!testArray[6].equals(p3)) {
			return false;
		}

		// Test 2: Already in sorted order
		try {
			queue.propagateUp(6);
		} catch (Exception e) {
			return false;
		}

		if (!testArray[0].equals(p6)) {
			return false;
		}

		if (!testArray[1].equals(p4)) {
			return false;
		}

		if (!testArray[2].equals(p5)) {
			return false;
		}

		if (!testArray[3].equals(p2)) {

			return false;
		}

		if (!testArray[4].equals(p1)) {
			return false;
		}

		if (!testArray[5].equals(p0)) {
			return false;
		}

		if (!testArray[6].equals(p3)) {
			return false;
		}

		// Test 3: Exception

		try {
			queue.propagateUp(-1);
		} catch (Exception e1) {
			try {
				queue.propagateUp(100);
			} catch (Exception e2) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Tests propagateDown()
	 * 
	 * @return true if method functions correctly, false if not
	 */
	public static boolean testPropagateDown() {
		QueueWAR queue = new QueueWAR(7);

		Player[] testArray = new Player[7];
		
		Player p0 = new Player("p0", 0, 0, 0);
		Player p1 = new Player("p1", 1, 0, 0);
		Player p2 = new Player("p2", 2, 0, 0);
		Player p3 = new Player("p3", 3, 0, 0);
		Player p4 = new Player("p4", 4, 0, 0);
		Player p5 = new Player("p5", 5, 0, 0);
		Player p6 = new Player("p6", 6, 0, 0);
		

		// Lowest value ("0") placed at root
		testArray[0] = p0;
		testArray[1] = p6;
		testArray[2] = p5;
		testArray[3] = p4;
		testArray[4] = p3;
		testArray[5] = p2;
		testArray[6] = p1;

		queue.array = testArray;
		queue.size = 7;

		// Test 1: Should work
		try {
			queue.propagateDown(0);
		} catch (Exception e) {
			return false;
		}

		if (!testArray[0].equals(p6)) {
			return false;
		}

		if (!testArray[1].equals(p4)) {
			return false;
		}

		if (!testArray[2].equals(p5)) {
			return false;
		}

		if (!testArray[3].equals(p0)) {
			return false;
		}

		if (!testArray[4].equals(p3)) {
			return false;
		}

		if (!testArray[5].equals(p2)) {
			return false;
		}

		if (!testArray[6].equals(p1)) {
			return false;
		}

		// Test 2: Already in sorted order
		try {
			queue.propagateDown(0);
		} catch (Exception e) {
			return false;
		}

		if (!testArray[0].equals(p6)) {
			return false;
		}

		if (!testArray[1].equals(p4)) {
			return false;
		}

		if (!testArray[2].equals(p5)) {
			return false;
		}

		if (!testArray[3].equals(p0)) {
			return false;
		}

		if (!testArray[4].equals(p3)) {
			return false;
		}

		if (!testArray[5].equals(p2)) {
			return false;
		}

		if (!testArray[6].equals(p1)) {
			return false;
		}

		// Test 3: Exceptions should be called
		try {
			queue.propagateDown(-1);
		} catch (Exception e1) {
			try {
				queue.propagateDown(100);
			} catch (Exception e2) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Main method that runs tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("testAddNewPlayer returns: " + HockeyWARTests.testAddNewPlayer());
		System.out.println("testCheckHighestPlayer returns: " +
				HockeyWARTests.testCheckHighestPlayer());
		System.out.println("testRemoveBestPlayer returns: " + 
				HockeyWARTests.testRemoveBestPlayer());
		System.out.println("testCheckQueueSize returns: " + HockeyWARTests.testCheckQueueSize());
		System.out.println("testParentOf returns: " + HockeyWARTests.testParentOf());
		System.out.println("testLeftChildOf returns: " + HockeyWARTests.testLeftChildOf());
		System.out.println("testRightChildOf returns: " + HockeyWARTests.testRightChildOf());
		System.out.println("testSwap returns: " + HockeyWARTests.testSwap());
		System.out.println("testPropagateUp returns: " + HockeyWARTests.testPropagateUp());
		System.out.println("testPropagateDown returns: " + HockeyWARTests.testPropagateDown());
	}

}
