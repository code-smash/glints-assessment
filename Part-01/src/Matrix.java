
/**
 * This class abstracts the required functionalities of a real time matrix. It
 * stores matrix using a 2D integer array. This class implements and exposes
 * matrix rotation feature.
 * 
 * @author Murali
 * @version 1.0
 *
 */
public class Matrix {

	/**
	 * 2D array to store matrix
	 */
	private int[][] matrix;

	/**
	 * 2D array to store clone or copy of a matrix
	 */
	private int[][] clonedMatrix;

	/**
	 * No of rows of the matrix
	 */
	private int matRows;

	/**
	 * No of columns of the matrix
	 */
	private int matCols;

	/**
	 * This private constructor initializes the class properties
	 */
	private Matrix() {
		matrix = null;
		clonedMatrix = null;
		matRows = 0;
		matCols = 0;
	}

	/**
	 * This constructor sets the number of rows and columns of the matrix, and
	 * creates the 2D array to store matrix contents.
	 * 
	 * @param m
	 *            Number of rows of the matrix
	 * @param n
	 *            Number of columns of the matrix
	 */
	public Matrix(int m, int n) {
		this();

		matRows = m;
		matCols = n;

		// Create the array to store matrix
		matrix = new int[matRows][matCols];
	}

	/**
	 * This method sets value of an element of the matrix identified by
	 * parameters r (row) and c (column). For e.g. A method call set(2, 3, 15)
	 * would set the value of the element located at 2nd row, 3rd column to
	 * 15.The methods also validates r and c to ensure that they are within the
	 * acceptable range, if not, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param r
	 *            Row of the element
	 * @param c
	 *            Column of the element
	 * @param value
	 *            The value to be set
	 * @throws IndexOutOfBoundsException
	 *             If r or c is outside the acceptable range
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html">java.lang.IndexOutOfBoundsException</a>
	 */
	public void set(int r, int c, int value) {
		// Check if r and c are within the acceptable range
		if (r >= 0 && r < matRows && c >= 0 && c < matCols)
			matrix[r][c] = value;
		else
			// If r and c are out of acceptable range, throw an exception
			throw new IndexOutOfBoundsException("Indexes specified are not within the acceptable range");
	}

	/**
	 * This method returns the value located at indexes specified by parameters
	 * r (Row) and c (Column). For e.g. A method call get(3,1) would return the
	 * value of the element located at 3rd row 1st column. The methods also
	 * validates r and c to ensure that they are within the acceptable range, if
	 * not, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param r
	 *            Row of the element
	 * @param c
	 *            Column of the element
	 * @throws IndexOutOfBoundsException
	 *             If r or c is outside the acceptable range
	 * @return The value located at indexes specified by the parameters r (Row)
	 *         and c (Column)
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html">java.lang.IndexOutOfBoundsException</a>
	 * 
	 */
	public int get(int r, int c) {
		int value = 0;

		// Check if r and c are within the acceptable range
		if (r >= 0 && r < matRows && c >= 0 && c < matCols)
			value = matrix[r][c];
		else
			// If r and c are out of acceptable range, throw an exception
			throw new IndexOutOfBoundsException("Indexes specified are not within the acceptable range");

		return value;
	}

	/**
	 * Returns the original 2D array which stores elements of the matrix
	 * 
	 * @return Integer 2D array. int[][]
	 */
	public int[][] getArray() {
		return matrix;
	}

	/**
	 * Rotates the matrix, a number of times as specified by the times
	 * parameter. A caller must call this method to rotate the matrix. This
	 * method creates a clone of the original 2D array which stores matrix
	 * values before it attempts to rotate the elements. Rotation is performed
	 * by shifting elements located at 4 sides (rows and columns) anti clockwise
	 * as many number of times as specified by the times parameter.
	 * 
	 * @param times
	 *            The number of times the matrix should be rotated
	 */
	public void rotate(int times) {

		// Verify if the matrix has at least a row or column with number of
		// elements greater than 1
		if (matRows > 1 || matCols > 1)

			for (int i = 0; i < times; i++) {
				// Clone original matrix array
				clonedMatrix = cloneMatrix();

				// Rotate the matrix once
				rotateOnce();

				clonedMatrix = null;
			}
	}

	/**
	 * Rotates the matrix once. This method rotates the matrix part by part.
	 * Starting with the original number of rows and columns, it rotates
	 * different segments of the matrix. After each iteration, the number of
	 * rows and columns are decremented so as to rotate inner segments of the
	 * matrix. This process continues until either number of rows or columns
	 * becomes less than or equal to 1. At this point, the whole matrix would
	 * have undergone one round of rotation.
	 */
	private void rotateOnce() {
		// Local variable to store number of rows of the matrix
		int rows = matRows;

		// Local variable to store number of columns of the matrix
		int cols = matCols;

		// Starting point of rotation
		int start = 0;

		// Loop while rows or columns are greater than 1
		while (rows > 1 || cols > 1) {

			// Rotate the matrix once by calling rotateOnce method
			rotatePart(start, rows, cols);

			// Decrements rows and columns by two to select inner segments
			rows -= 2;
			cols -= 2;

			// Resets the starting point of rotation
			start++;
		}
	}

	/**
	 * Rotates the selected 4 segments of the matrix once. Segments are selected
	 * both row wise and column wise. The point of selection is determined by
	 * the start parameter. For e.g. If start = 0, rows 0 and rows - 1 and
	 * columns 0 and cols - 1 are selected . Similarly, start = 1, will select
	 * the segments identified by row 1 and rows and columns 1 and cols. Each of
	 * the 4 selected segments are rotated anti clockwise by iterations starting
	 * with the element at row = start, col = start. Once all iterations are
	 * completed, the selected segments might have been completed one rotation
	 * anti clockwise
	 * 
	 * @param start
	 *            The element at which rotation begins.
	 * @param rows
	 *            The number of rows to be considered in the current context
	 * @param cols
	 *            The number of columns to be considered in the current context
	 */
	private void rotatePart(int start, int rows, int cols) {

		if (start == 0)

			// While start is 0, Iterate over column 0 and shift the elements
			// down once
			for (int i = start + 1; i < rows; i++)
				matrix[i][start] = clonedMatrix[i - 1][start];
		else

			// While start is greater than 0, iterate over the column identified
			// by start and shift the elements down once
			for (int i = start + 1; i <= rows; i++)
				matrix[i][start] = clonedMatrix[i - 1][start];

		if (start == 0)

			// While start is 0, iterate over the last row and shift the
			// elements right once
			for (int i = start + 1; i < cols; i++)
				matrix[rows - 1][i] = clonedMatrix[rows - 1][i - 1];
		else

			// While start is greater than 0, Iterate over the row identified by
			// start and shift the elements right once
			for (int i = start + 1; i <= cols; i++)
				matrix[rows][i] = clonedMatrix[rows][i - 1];

		if (start == 0)

			// While start is 0, iterate over last column and shift the elements
			// up once
			for (int i = rows - 2; i >= 0; i--)
				matrix[i][cols - 1] = clonedMatrix[i + 1][cols - 1];
		else

			// While start is greater than 0, iterate over the column identified
			// by start and shift the elements up once
			for (int i = rows - 1; i >= start; i--)
				matrix[i][cols] = clonedMatrix[i + 1][cols];

		if (start == 0)

			// While start is 0, iterate over the first row and shift the
			// elements left once
			for (int i = cols - 2; i >= 0; i--)
				matrix[start][i] = clonedMatrix[start][i + 1];
		else

			// While start is greater than 0, iterate over the row identified by
			// start and shift the elements left once.
			for (int i = cols - 1; i >= start; i--)
				matrix[start][i] = clonedMatrix[start][i + 1];
	}

	/**
	 * Returns a copy of the original 2D array which stores matrix. Cloning
	 * helps to prevent losing the original array elements as a result of
	 * displacement during matrix rotation.
	 * 
	 * @return A new 2D array contains the original values of the matrix
	 */
	private int[][] cloneMatrix() {
		// Create a new 2D array with the same size as that of the original
		// array
		int[][] clone = new int[matRows][matCols];

		// Iterate over the original matrix array and copy corresponding
		// elements to the new array created in the previous step.
		for (int i = 0; i < matRows; i++)
			for (int j = 0; j < matCols; j++)
				clone[i][j] = matrix[i][j];

		return clone;
	}

	/**
	 * Prints the original matrix stored as a 2D array in a readable format.
	 */
	public void print() {
		// Leave a blank line
		System.out.println();

		// Iterate over the 2D array and print element by element
		for (int i = 0; i < matRows; i++) {
			for (int j = 0; j < matCols; j++)
				System.out.print(matrix[i][j] + " ");

			System.out.println();
		}
	}
}
