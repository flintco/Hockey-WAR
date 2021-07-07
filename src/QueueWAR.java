import java.util.Scanner;

/**
 * @author Connor Flint
 *
 * QueueWAR is a priority queue where the higher a player's WAR, the higher the priority 
 * in the queue (implemented using a max-heap).
 * 
 * Practical Application: "Wins above Replacement" or WAR is a way to measure how valuable an 
 * athlete is to his/her team. I created an algorithm to take the common statistics (goals, assists,
 * etc) of a hockey player and converted it into WAR, a single number. This program allows a hockey
 * talent evaluator to create a list of hockey players in order of WAR. The user can perform useful 
 * functions such as adding to queue, checking highest WAR, removing highest WAR, and checking the
 * length of the queue. 
 */
public class QueueWAR {
	protected Player[] array; // zero-indexed max-heap
	protected int size;
	
	/**
	 * Constructor to create QueueWAR
	 * 
	 * @param arrayCapacity
	 */
	public QueueWAR(int arrayCapacity) {
		this.size = 0;
		this.array = new Player[arrayCapacity];
	}
	
	//Priority Queue ADT Operations
	/**
	 * Adds a given player to QueueWAR
	 * 
	 * @param player to be added
	 * @throws NullPointerException if player is null
	 * @throws IndexOutofBoundsException if QueueWAR is at capacity
	 */
	public void addNewPlayer(Player player) throws NullPointerException, 
	IndexOutOfBoundsException {
		if (player == null) {
			throw new NullPointerException("Player can't be null!");
		}
		if (array.length == size) {
			throw new IndexOutOfBoundsException("QueueWAR is already full!");
		}

		array[size] = player;

		this.size += 1;

		try {
			propagateUp(size - 1);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Returns player in queue with the highest WAR
	 * 
	 * @returns string representation of player
	 * @throws IllegalStateException QueueWAR is empty
	 */
	public Player checkHighestPlayer() throws IllegalStateException {
		if (this.size == 0) {
			throw new IllegalStateException("QueueWAR is empty!");
		}
		Player highestPlayer = this.array[0];
		return highestPlayer;
		}
	
	
	/**
	 * Removes Player with the highest WAR from HockeyWAR and returns its string representation
	 * 
	 * @returns player that is removed
	 * @throws IllegalStateException QueueWAR is empty
	 */
	public Player removeBestPlayer() throws IllegalStateException {
		if (this.size == 0) {
			throw new IllegalStateException("QueueWAR is empty!");
		}

		Player toRemove = this.checkHighestPlayer();

		// moves last element to front and propagates down
		array[0] = array[size - 1];
		array[size - 1] = null;

		this.size -= 1;

		try {
			this.propagateDown(0);
		} catch (Exception e) {

		}

		return toRemove;
	}
	
	/**
	 * Returns the current size of QueueWAR
	 * 
	 * @return size of QueueWAR
	 */
	public int checkQueueSize() {
		return this.size;
	}
	
	//Supplementary methods for heap array functionality
	
	/**
	 * Given an index into the heap array, this method returns that index's parent index.
	 * 
	 * @param index
	 * @return index of parent
	 */
	protected static int parentOf(int index) {
		index += 1;
		int parIndex = index / 2;
		return (parIndex - 1);
	}

	/**
	 * Given an index into the heap array, this method returns that index's left child index.
	 * 
	 * @param index
	 * @return index of left child
	 */
	protected static int leftChildOf(int index) {
		index += 1;
		int leftIndex = index * 2;
		return (leftIndex - 1);
	}

	/**
	 * Given an index into the heap array, this method returns that index's right child index.
	 * 
	 * @param index
	 * @return index of right child
	 */
	protected static int rightChildOf(int index) {
		index += 1;
		int rightIndex = (index * 2) + 1;
		return (rightIndex - 1);
	}

	/**
	 * Given two indexes into the heap array, this method swaps the Players at those indexes.
	 * 
	 * @param indexA
	 * @param indexB
	 * @throws Exception if either index is below 0 or out of range
	 */
	protected void swap(int indexA, int indexB) throws Exception {
		if (indexA < 0 || indexA >= size) {
			throw new Exception("IndexA out of bounds!");
		}

		if (indexB < 0 || indexB >= size) {
			throw new Exception("IndexB out of bounds!");
		}

		Player transfer = array[indexA];
		Player newA = array[indexB];
		Player newB = transfer;

		array[indexA] = newA;
		array[indexB] = newB;

	}

	/**
	 * Given an index into the heap array, this method recursively swaps any Players necessary to
	 * enforce the heap's order property between this index and the heap's root.
	 * 
	 * @param index to start
	 * @throws Exception if index is invalid
	 */
	protected void propagateUp(int index) throws Exception {
		if (index < 0 || index >= size) {
			throw new Exception("Index is invalid");
		}

		int parentIndex = QueueWAR.parentOf(index);

		// checks that parent index exists
		if (parentIndex < 0 || parentIndex >= size) {
			return;
		}

		Player parent = array[parentIndex];
		Player curTicket = array[index];

		if (curTicket.compareTo(parent) > 0) {
			try {
				this.swap(parentIndex, index);
			} catch (Exception e) {
				System.out.println("Invalid swap here.");
			}
			propagateUp(parentIndex);
		} else {
			return;
		}

	}

	/**
	 * Given an index into the heap array, this method recursively swaps any Players necessary to 
	 * enforce the heap's order property between this index and it's children.
	 * 
	 * @param index to start
	 * @throws Exception if index is invalid
	 */
	protected void propagateDown(int index) throws Exception {
		if (index < 0 || index >= size) {
			throw new Exception("Not an acceptable index");
		}

		Player parent = array[index];
		Player greatestChild;
		int greatestChildIndex;
		int leftIndex = QueueWAR.leftChildOf(index);
		int rightIndex = QueueWAR.rightChildOf(index);

		// neither a left or right child exists
		if (leftIndex >= size && rightIndex >= size) {
			return;
		}
		// only a left child
		else if (rightIndex >= size) {
			greatestChild = array[leftIndex];
			greatestChildIndex = leftIndex;
		}
		// only a right child
		else if (leftIndex >= size) {
			greatestChild = array[rightIndex];
			greatestChildIndex = rightIndex;
		}
		// both a right and left child exist
		else {
			Player leftChild = array[leftIndex];
			Player rightChild = array[rightIndex];

			// find the greater child
			if (leftChild.compareTo(rightChild) > 0) {
				greatestChild = leftChild;
				greatestChildIndex = leftIndex;
			} else {
				greatestChild = rightChild;
				greatestChildIndex = rightIndex;
			}
		}

		// compare greatest child to parent
		if (parent.compareTo(greatestChild) < 0) {
			try {
				this.swap(index, greatestChildIndex);
			} catch (Exception e) {
				System.out.println("Invalid swap here.");
			}
			propagateDown(greatestChildIndex);
		} else {
			return;
		}

	}
	
	
	
	//Command Interface that allows user to interact with priority queue
	public void printCommandMenu() {
		System.out.println("Enter one of the following options:");
		System.out.println("[1 <Name> <Goals> <Assists> <Penalties>] Add a new player");
		System.out.println("[2] See which player has highest WAR");
		System.out.println("[3] Remove player with highest WAR");
		System.out.println("[4] See how many players are in the queue");
		System.out.println("[5] Quit");
		System.out.println("--------------------------------------------------------");	
	}
	
	//Process user commands
	public void readCommand(Scanner scanner) {

		int commandInt = 0;
		while (commandInt != 5) {
			printCommandMenu();
			System.out.print("ENTER COMMAND: ");
			commandInt = scanner.nextInt();
			switch (commandInt) {
			case 1:
				String name = scanner.next();
				String goalsString = scanner.next();
				int goals;
				try {
					goals = Integer.parseInt(goalsString);
				} catch (NumberFormatException e) {
					System.out.println("Player could not be successfully added");
					System.out.println("");
					break;
				}
				String assistsString = scanner.next();
				int assists;
				try {
					assists = Integer.parseInt(assistsString);
				} catch (NumberFormatException e) {
					System.out.println("Player could not be successfully added");
					System.out.println("");
					break;
				}
				String penaltiesString = scanner.next();
				int penalties;
				try {
					penalties = Integer.parseInt(penaltiesString);
				} catch (NumberFormatException e) {
					System.out.println("Player could not be successfully added");
					System.out.println("");
					break;
				}
				Player p = new Player(name, goals, assists, penalties);
				addNewPlayer(p);
				break;
			case 2:
				System.out.println(checkHighestPlayer());
				break;
			case 3:
				Player removed = removeBestPlayer();
				System.out.println(removed + " was removed");
				break;
			case 4:
				System.out.println("The queue size is currently " + checkQueueSize());
				break;
			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		QueueWAR queue = new QueueWAR(10);
		System.out.println("\n--------------------------------------------------------");
		System.out.println("    QueueWAR");
		System.out.println("--------------------------------------------------------");
		queue.readCommand(scanner);
		System.out.println("\n--------------------------------------------------------");
		System.out.println("    QueueWAR has been closed");
		System.out.println("--------------------------------------------------------");
		scanner.close();
	}
}
