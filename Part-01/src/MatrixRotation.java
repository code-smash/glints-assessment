import java.util.Scanner;

/**
 * <p>
 * An object of this class accepts matrix order, matrix values and number of
 * rotations from standard input and performs matrix rotation by using an object
 * of the Matrix class and prints the output on standard input. The class
 * includes a main method which makes it runnable.
 * </p>
 * <p>
 * When executed, it first prompts to enter the matrix order (Number of rows and
 * columns) and number of rotations. All 3 inputs should be specified in one
 * line separated by spaces. Next, a prompt for entering the matrix elements
 * will be displayed. Elements in each row of the matrix should be separated by
 * space, and each row should contain the required number of elements specified
 * by the number of columns entered in the previous step. Press enter key after
 * each row so that the cursor positions in the next line and waits to type in
 * the elements of the next row. This process continues until all rows specified
 * by the number of rows entered in the previous step.
 * </p>
 * <p>
 * When all required inputs are read from the standard input console, an
 * instance of the {@link Matrix} class is created by passing the number of rows
 * and columns as parameters to {@link Matrix#Matrix(int, int)}. In the next
 * step, {@link Matrix#rotate(int)} method is called to perform matrix rotation
 * the required number of times and prints the result.
 * </p>
 * 
 * @author Murali
 * @version 1.0
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html">java.util.Scanner</a>
 */
public class MatrixRotation {

	/**
	 * The default constructor
	 */
	public MatrixRotation() {
	}

	/**
	 * When executed, this method serves as the entry point. An object of
	 * java.util.Scanner is created to read from standard input. To begin with,
	 * it prompts to enter the matrix order (The number of rows and columns of
	 * the matrix) and the number of rotations required. In the next step, a
	 * prompt to enter the matrix elements is displayed. Enter the matrix
	 * elements row by row where each of the elements in a row separated by
	 * space. Upon completing this step, the method creates an instance of the
	 * {@link Matrix} class and calls the {@link Matrix#rotate(int)} method, by
	 * passing the number rotations accepted input as the parameter. With this,
	 * array undergoes rotation. Result is printed by calling
	 * {@link Matrix#print()} method.
	 * 
	 * @param args
	 *            An array of type String, is the command line parameters
	 *            injected at runtime.
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html">java.util.Scanner</a>
	 */
	public static void main(String[] args) {

		// Create an object of Scanner to read from standard input
		Scanner scan = new Scanner(System.in);

		try {

			// Prompt and read matrix order and number of rotations from
			// standard input
			System.out.println("Enter matrix order and number of rotations (M,N and R) separated by spaces");
			String strInput = scan.nextLine();
			String[] inputs = strInput.split(" ");

			// If input is not sufficient, throw RuntimeException
			if (inputs.length < 3)
				throw new RuntimeException("The input does not include all required values");

			// Convert input values of type String to values of type int
			int m = Integer.parseInt(inputs[0]);
			int n = Integer.parseInt(inputs[1]);
			int r = Integer.parseInt(inputs[2]);
			Matrix mat = new Matrix(m, n);

			// Prompt and read matrix elements
			System.out.println("Enter the matrix");
			for (int i = 0; i < m; i++) {
				strInput = scan.nextLine();
				inputs = strInput.split(" ");

				for (int j = 0; j < n; j++)
					mat.set(i, j, Integer.parseInt(inputs[j]));
			}

			// System.out.println("\nOriginal Matrix");
			// mat.print();
			System.out.println("\nAfter Rotation");

			// Rotate the matrix and print result
			mat.rotate(r);
			mat.print();

		} catch (NumberFormatException e) {
			System.out.println("Not a valid number " + e.getMessage().toLowerCase());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred : " + e.getMessage());
		} finally {
			System.out.println("\n\nTerminated");
		}

	}
}
