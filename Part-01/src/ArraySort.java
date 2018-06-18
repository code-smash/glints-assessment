import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * This class analyzes an array, attempts to sort and outputs the method by
 * which the array can be sorted in ascending order. The analysis is performed
 * based on the requirements given in the problem statement which is described
 * below :-
 * </p>
 * <p>
 * Given an array with n elements, sort this array in ascending order using only
 * one of the following operations.
 * <ol>
 * <li>Swap two elements.</li>
 * <li>Reverse one sub-segment</li>
 * </ol>
 * </p>
 * <p>
 * <ul>
 * <li>If the array is already sorted, output yes on the first line. You do not
 * need to output anything else.</li>
 * <li>If array can be sorted using one single operation (from the two permitted
 * operations) then output yes on the first line and then:
 * <ol>
 * <li>If array can be sorted by swapping dl and dr, output swap l r in the
 * second line. l and r are the indices of the elements to be swapped, assuming
 * that the array is indexed from 1 to n.</li>
 * <li>Else if it is possible to sort the array by reversing the segment
 * d[l...r], output reverse l r in the second line. l and r are the indices of
 * the first and last elements of the subsequence to be reversed, assuming that
 * the array is indexed from 1 to n.</li>
 * <li>d[l...r] represents the sub-sequence of the array, beginning at index l
 * and r ending at index, both inclusive.</li>
 * <li>If an array can be sorted by either swapping or reversing, stick to the
 * swap-based method.</li>
 * </ol>
 * </li>
 * <li>If you cannot sort the array in either of the above ways, output no in
 * the first line.</li>
 * </ul>
 * </p>
 * <p>
 * The class has both instance methods and a main method. At runtime, it prompts
 * to enter the size of the array followed by the array elements in a line
 * separated by space. In the next step, an object of this class is created by
 * passing the array reference to the constructor. Then it calls the
 * {@link #analyzeArray()} method to begin analyze and figure out the
 * possibility of sorting the array as described in the problem statement.
 * </p>
 * 
 * @author murali
 * @version 1.0
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html">java.util.Scanner</a>
 */
public class ArraySort {

	/**
	 * Sort method constant to indicate that the array is already sorted
	 */
	public static final int SORTED_ALREADY = 0;

	/**
	 * Sort method constant to indicate that the array can be sorted by swapping
	 * 2 elements
	 */
	public static final int SORT_BY_SWAP = 1;

	/**
	 * Sort method constant to indicate that the array can be sorted by
	 * reversing a sub-segment
	 */
	public static final int SORT_BY_REVERSAL = 2;

	/**
	 * Sort method constant to indicate that the array cannot be sorted either
	 * by swapping elements or by reversing a sub-segment.
	 */
	public static final int NOT_POSSIBLE = 3;

	/**
	 * The original array
	 */
	private int[] numArray;

	/**
	 * Clone of the original array
	 */
	private int[] clonedArray;

	/**
	 * The sorting method as indicated by sort method constants
	 */
	private int method;

	/**
	 * Stores index of the left most element in a sub-segment
	 */
	private int lindex;

	/**
	 * Stores index of the right most element in a sub-segment
	 */
	private int rindex;

	/**
	 * Default constructor which initializes the instance variables
	 */
	private ArraySort() {
		numArray = null;
		clonedArray = null;
		method = NOT_POSSIBLE;
		lindex = -1;
		rindex = -1;
	}

	/**
	 * Constructor, to be called while creating the object of this class
	 * 
	 * @param num
	 *            Reference to the array of numbers
	 */
	public ArraySort(int[] num) {
		this();
		numArray = num;
	}

	/**
	 * Returns the sort method (Either one of the sort method constants) set by
	 * {@link #analyzeArray()} method
	 * 
	 * @return One of the selected sort method constant
	 */
	public int getSortMethod() {
		return method;
	}

	/**
	 * Returns the index of the left element set by calling either
	 * {@link #analyzeSwapMethod()} method or {@link #analyzeReversalMethod()}
	 * method
	 * 
	 * @return Index of the left most element
	 */
	public int getLIndex() {
		return lindex + 1;
	}

	/**
	 * Returns the index of the right element set by calling either
	 * {@link #analyzeSwapMethod()} method or {@link #analyzeReversalMethod()}
	 * method
	 * 
	 * @return Index of the right most element
	 */
	public int getRIndex() {
		return rindex + 1;
	}

	/**
	 * This method should be called to start analyzing the array to determine
	 * the possibility to sort. If possible, the method of sort (Either by
	 * swapping 2 elements or by reversing a sub-segment) and the left and right
	 * indexes of the respective elements are captured into variables
	 * {@link #method}, {@link #lindex} and {@link #rindex}.
	 * <p>
	 * This method creates a clone of the original array first, and checks if
	 * the array is already sorted by calling {@link #isArraySorted()} method.
	 * If not sorted already, then method {@link #analyzeSwapMethod()} is called
	 * to check whether the array can be sorted by means of swapping 2 elements.
	 * If not possible, then it checks the same by calling
	 * {@link #analyzeReversalMethod()} method, to figure out whether reversing
	 * a sub-segment can help to sort the array. If this check also fails, then
	 * it concludes that the array is not possible to sort using either of the
	 * defined methods.
	 * </p>
	 * <p>
	 * During the course of execution, this method sets the variable
	 * {@link #method} to an appropriate value defined by sort method constants.
	 * </p>
	 */
	public void analyzeArray() {
		// Create a clone of the original array
		cloneArray();

		// Check if array is already sorted
		if (!isArraySorted()) {
			// If not already sorted, check if array can be sorted by swapping
			// elements
			if (analyzeSwapMethod())
				method = SORT_BY_SWAP;

			// If array cannot be sorted by swapping, check reversal method
			else if (analyzeReversalMethod())
				method = SORT_BY_REVERSAL;
		} else
			method = SORTED_ALREADY;
	}

	/**
	 * <p>
	 * This method analyzes the cloned array to figure out whether it is
	 * possible to sort by swapping any 2 elements.
	 * </p>
	 * <p>
	 * The method iterates over the array from left to right and selects pair of
	 * elements. The selected pair is swapped by calling {@link #swap(int, int)}
	 * method. In the next step, method {@link #isArraySorted()} is called to
	 * check whether the array is sorted or not. If sorted, then the method sets
	 * the indexes of left and right elements to variables {@link #lindex},
	 * {@link #rindex} respectively and the loop exists. If not sorted, then the
	 * process will continue by checking the subsequent pair of elements in the
	 * same manner.
	 * </p>
	 * <p>
	 * The method continues to select pair of elements until it either finds
	 * that sorting is possible or the loop runs out. From a given set of N
	 * elements, there are total N * (N - 1) unique pairs can be selected in
	 * spite of pairing with the same element.
	 * </p>
	 * 
	 * @return true, if it is possible to sort the array. Otherwise, false
	 */
	private boolean analyzeSwapMethod() {
		// Declare and initialize variable to store the return value
		boolean possible = false;

		// Start loops to select pair of elements to test by swapping
		for (int i = 0; i < clonedArray.length && !possible; i++)
			for (int j = 0; j < clonedArray.length && !possible; j++)

				// Check if indexes are the same. If so, skip
				if (j != i) {
					// Swap 2 elements
					swap(i, j);

					// Check whether the array is sorted or not
					possible = isArraySorted();

					if (possible) {
						// If sorted, set the left and right indexes and exit
						// the loop
						lindex = i;
						rindex = j;
					} else
						// If not sorted, undo swapping done in the previous
						// step
						swap(i, j);
				}

		return possible;
	}

	/**
	 * <p>
	 * This method analyzes the cloned array to figure out whether it is
	 * possible to sort by reversing a sub-segment.
	 * </p>
	 * <p>
	 * The method iterates over the cloned array from right to left, to select
	 * segments of all possible lengths. There are 2 for loops, of which the
	 * outer loop keeps track of the length of the sub-segments. The inner loop
	 * determines the lower and higher indexes of the desired sub-segment. In
	 * the next step, method {@link #reverseArray(int, int)} is called to
	 * reverse the sub-segment identified by the left and right indexes. After
	 * reversing the elements in this way, method {@link #isArraySorted()} is
	 * called to check whether the array is sorted or not. This process
	 * continues until either the array becomes sorted or the loop runs out. If
	 * the array becomes sorted during a particular iteration, indexes of the
	 * left and right elements are assigned to variables {@link #lindex},
	 * {@link #rindex} respectively.
	 * </p>
	 * <p>
	 * Determining the length of a segment is carried out by the loop variables
	 * of the inner for loop. Right most index of the segment is set by inner
	 * loop variable r and the left most index l is initialized as l = r - c +
	 * 1, where c is the outer loop variable, which defines the length of the
	 * segment. Once, initialized, both the variables are decremented after each
	 * iteration. In this way, segments of all possible length can be
	 * determined.
	 * </p>
	 * 
	 * @return true, if it is possible to sort the array. Otherwise, false
	 */
	private boolean analyzeReversalMethod() {
		boolean possible = false;

		// Outer for loop variable c determines length of the segment
		for (int c = clonedArray.length; c >= 2 && !possible; c--)
			// Inner for loop variables r and l determines right and left
			// indexes
			for (int r = clonedArray.length - 1, l = r - c + 1; l > 0 && !possible; r--, l--) {
				// Reverse the segment from index r to index l, both inclusive
				reverseArray(l, r);

				// Check if array is sorted
				possible = isArraySorted();

				// If sorted, store left and right indexes
				if (possible) {
					lindex = l;
					rindex = r;
				} else
					// Clone original array to reset the cloned array
					cloneArray();
			}

		return possible;
	}

	/**
	 * Checks whether the array is sorted or not and returns true/false. Array
	 * elements are compared one by one and if at any point, an element is found
	 * to be greater than the previous element, then array is identified as not
	 * sorted.
	 * 
	 * @return true, if the array is sorted. Otherwise false
	 */
	private boolean isArraySorted() {
		boolean sorted = true;

		// Loop over array from left to right
		for (int i = 1; i < clonedArray.length; i++)

			// Check if current element is less than the previous one. If so,
			// the array is not sorted, so exit the loop
			if (clonedArray[i] < clonedArray[i - 1]) {
				sorted = false;
				break;
			}

		return sorted;
	}

	/**
	 * Swaps the elements specified by the parameters index1 (left) and index2
	 * (right)
	 * 
	 * @param index1
	 *            Index of the element on the left in the array
	 * @param index2
	 *            Index of the element on the right in the array
	 */
	private void swap(int index1, int index2) {
		// Store left element in a temporary variable
		int temp = clonedArray[index1];

		// Replace element at index1 with element at index2
		clonedArray[index1] = clonedArray[index2];

		// Replace element at index2 with the value in variable temp (The value
		// of the element at index1)
		clonedArray[index2] = temp;
	}

	/**
	 * Reverses the elements of the array starting from index l to r, inclusive
	 * of both the indexes. This method pairs the elements at the left most and
	 * right most indexes specified by l,r respectively. Method
	 * {@link #swap(int, int)} is called to interchange the pair of elements.
	 * This process continues by incrementing the left index and decrementing
	 * the right index, until all possible iterations are complete.
	 * 
	 * @param l
	 *            Index of the array element on the left
	 * @param r
	 *            Index of the array element on the right
	 */
	private void reverseArray(int l,int r) {

		// Determine the number of iterations required
		int itrns = (r - l + 1) / 2;

		// Loop until the required number of iterations are complete
		for (int i = l, j = r, c = 1; c <= itrns; i++, j--, c++)
			swap(i,j);
	}

	/**
	 * Clones the original array. A new clone array is created only when the
	 * method is called for the first time. At all other times, the existing
	 * {@link #clonedArray} variable is overwritten with the corresponding
	 * values from the original array.
	 */
	private void cloneArray() {
		// Create the array with size same as that of the original array, only
		// when calling the method for the very first time.
		if (clonedArray == null)
			clonedArray = new int[numArray.length];

		// Copy the corresponding elements from the original array
		for (int i = 0; i < clonedArray.length; i++)
			clonedArray[i] = numArray[i];
	}

	/**
	 * <p>
	 * The entry point of execution. This method creates an object of <a href=
	 * "https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html">java.util.Scanner</a>
	 * class to read data from standard input.
	 * <p>
	 * The method prompts to enter the number of elements first. In the next
	 * step, it prompts to enter the elements. After necessary validations, the
	 * method creates the array and initializes with the elements read in the
	 * previous step. Thereafter, an object of this class is created by passing
	 * the array reference to the constructor and calls the
	 * {@link #analyzeArray()} method which determines the sort method, the left
	 * and right indexes of the elements to be either swapped or reversed. The
	 * result is printed in the next step.
	 * </p>
	 * 
	 * @param args
	 *            An array of type String, is the command line parameters
	 *            injected at runtime.
	 * @throws RuntimeException
	 *             If the size of the array entered does not match the size
	 *             specified in the previous step.
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html">java.lang.RuntimeException</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/InputMismatchException.html">java.util.InputMismatchException</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/NumberFormatException.html">java.lang.NumberFormatException</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html">java.lang.Exception</a>
	 */
	public static void main(String[] args) {
		// Create an object of Scanner to read from standard input
		Scanner scan = new Scanner(System.in);

		try {
			// Prompt to enter the array size and read the value
			System.out.println("Enter the number of elements");
			int n = scan.nextInt();

			// Prompt to enter the array element separated by space and read the
			// values
			System.out.println("Enter " + n + " integers separated by space ");
			scan.nextLine();
			String[] input = scan.nextLine().split(" ");

			// Validate array size
			if (input.length < n)
				throw new RuntimeException("Input does not contain " + n + " numbers");

			// Create the original array
			int[] num = new int[n];

			// Assign array elements read in the previous step
			for (int i = 0; i < n; i++)
				num[i] = Integer.parseInt(input[i]);

			// Create an object of this class and start analyzing the array
			// values
			ArraySort arraySort = new ArraySort(num);
			arraySort.analyzeArray();

			// Examine the sort method set as a result of calling analyzeArray
			// method in the previous step and print the result.
			switch (arraySort.getSortMethod()) {
			case SORTED_ALREADY:
				System.out.println("Yes");
				break;
			case SORT_BY_SWAP:
				System.out.println("Yes");
				System.out.print("Swap");
				System.out.print(" " + arraySort.getLIndex());
				System.out.print(" " + arraySort.getRIndex());
				break;
			case SORT_BY_REVERSAL:
				System.out.println("Yes");
				System.out.print("Reverse");
				System.out.print(" " + arraySort.getLIndex());
				System.out.print(" " + arraySort.getRIndex());
				break;
			case NOT_POSSIBLE:
				System.out.println("No");
			}

		} catch (InputMismatchException e) {
			System.out.println("Not a valid number ");
		} catch (NumberFormatException e) {
			System.out.println("Not a valid integer " + e.getMessage().toLowerCase());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred : " + e.getMessage());
		} finally {
			System.out.println("\nTerminated");
		}

	}
}
