import java.util.Deque;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * This class accepts an integer from standard input and computes the factorial
 * of a number. The class contains a main method which makes it runnable. When
 * executed, the user will be prompted to enter an integer from standard input
 * to compute its factorial. Once entered, the factorial of the input number is
 * computed and printed in the next line. The main method is designed such that
 * it prompts for input and computes the output in a loop so that a user can
 * keep working with the program trying different numbers as input. The loop
 * will terminate upon entering a negative number.
 * </p>
 * <p>
 * Factorial of a number is nothing but the product of the natural numbers,
 * starting from 1 until the desired number. For e.g. factorial of 5, i.e. 5! =
 * 1 x 2 x 3 x 4 x 5. In general factorial of N, where N is a natural number can
 * be written as :-
 * </p>
 * <p>
 * N! = 1 x 2 x 3 ..... x (N - 1) x N.
 * </p>
 * <p>
 * Factorial can be programmatically computed by multiplying the numbers in a
 * loop or by means of recursive function calls. This class incorporates the
 * recursive call method for computing factorial of numbers up to 20. For
 * numbers greater than 20, either of the two methods will return incorrect
 * results due to overflow. Overflow happens as the result of series of
 * multiplications producing large numbers sufficient to cross the storage limit
 * of the variable being used. On such occasions, the traditional method of
 * multiplication is adopted which helps to manage large values resulting from
 * multiplying large numbers. Also, this will help prevent overflow errors which
 * is common with the recursive call method.
 * </p>
 * 
 * @author Murali
 * @version 1.0
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html">java.util.LinkedList</a>
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html">java.util.Deque</a>
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/List.html">java.util.List</a>
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html">java.lang.Integer</a>
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html">java.util.Scanner</a>
 */
public class Factorial {

	/**
	 * Default Constructor
	 */
	public Factorial() {
	}

	/**
	 * This method initiates the factorial computation process. Serves as an
	 * interface to a caller. If the number passed as parameter is less than or
	 * equal to 20, then method {@link #simpleFactorial(int)} is called. Else,
	 * method {@link #complexFactorial(int)} is called by passing the parameter.
	 * 
	 * @param num
	 *            An integer value to compute the factorial. Although any number
	 *            can be passed, the method is tested for {@code 1 <= N <= 100},
	 *            where N is an integer.
	 * 
	 * @return The factorial of the integer passed as argument
	 */
	public String getFactorial(int num) {
		String result = "";

		if (num <= 20)
			// If param <= 20, use recursive method to compute factorial
			result = Long.toString(simpleFactorial(num));
		else
			// If param > 20, use advanced computation method
			result = complexFactorial(num);

		return result;
	}

	/**
	 * Computes and returns the factorial of a number passed as parameter using
	 * the recursive call method. This method works well within parameter range
	 * 1 &lt;= N &lt;= 20, where N is an integer.
	 * 
	 * @param num
	 *            An integer value to compute the factorial. Value must be in
	 *            the range &lt;= N &lt;= 20, where N is an integer.
	 * 
	 * @return The factorial of the number passed as parameter.
	 */
	private long simpleFactorial(int num) {
		if (num == 0)
			return 1L;
		else
			return num * simpleFactorial(num - 1);
	}

	/**
	 * <p>
	 * Computes and returns the factorial of a number passed as parameter. If
	 * the number passed as parameter is greater than 20, to improve
	 * performance, it first computes factorial of 20 by calling
	 * {@link #simpleFactorial(int)} method, which computes factorial by
	 * recursive call method. Then it performs a series of multiplications based
	 * on traditional method where 2 numbers are multiplied by multiplying
	 * individual digits and adding up the numbers obtained as a result of
	 * multiplication. This method utilizes java.util.LinkedList class.
	 * </p>
	 * <p>
	 * java.util.LinkedList is a list data structure which acts as a List,
	 * Dequeue (Double ended queue) and a Stack. It is used to store large
	 * values generated as a result of multiplying large numbers. This method of
	 * multiplication guarantees accuracy of the result produced.
	 * </p>
	 * 
	 * @param num
	 *            An integer value to compute the factorial
	 * @return The factorial of the number passed as parameter.
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html">java.util.LinkedList</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html">java.util.Deque</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/List.html">java.util.List</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html">java.lang.Integer</a>
	 */
	private String complexFactorial(int num) {
		// First, compute the factorial of 20
		long fact20 = simpleFactorial(20);

		// Convert to List structure
		List<Integer> intList = toList(fact20);

		// Start a loop from 21 and perform multiplications until the value
		// passed as parameter
		for (int i = 21; i <= num; i++)
			intList = multiply(intList, toList(i));

		// Instantiate a StringBuilder class
		StringBuilder sb = new StringBuilder();

		// Populate StringBuilder object with the final result
		for (Integer i : intList)
			sb.append(Integer.toString(i));

		return sb.toString();
	}

	/**
	 * Returns a list of Integer objects after converting the number passed as
	 * parameter. The list return is an instance of java.util.LinkedList class.
	 * 
	 * @param n
	 *            A number of type long to be converted into a list.
	 * @return java.util.List&lt;Integer&gt;, an instance of
	 *         java.util.LinkedList class.
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html">java.util.LinkedList</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/List.html">java.util.List</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html">java.lang.Integer</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/Long.html">java.lang.Long</a>
	 */
	private List<Integer> toList(long n) {

		// Convert the integer passed as parameter to String
		String strNum = Long.toString(n);
		List<Integer> intList = new LinkedList<>();

		// Convert the String variable into an array of characters
		char[] chArray = strNum.toCharArray();

		// Iterate over the character array and convert each digit of type char
		// into int and add to LinkedList
		for (char c : chArray)
			intList.add(Character.getNumericValue(c));

		return intList;
	}

	/**
	 * <p>
	 * Accepts two parameters of type java.util.List, performs multiplication
	 * and returns result of type java.util.List. Runtime type of both
	 * parameters and the return value is java.util.LinkedList.
	 * </p>
	 * <p>
	 * This method iterates over the list of integers passed as parameter and
	 * performs a manual multiplication just like we do with a paper and a pen.
	 * Multiplication is performed from right to left, digit by digit, taking
	 * care of the carry generated. After each iteration of multiplications, the
	 * digits generated are pushed into a queue, implemented as java.util.Deque.
	 * Depending on the number of digits present in the numbers passed as
	 * parameters, the number queues generated may vary.
	 * </p>
	 * <p>
	 * Once all iterations are complete, the queues produced are equalized by
	 * adding leading and trailing zeroes. Finally, the numbers in the form of
	 * queues are added up from right to left to compute the result of
	 * multiplication. The result is added into a list of type java.util.List
	 * and returned.
	 * </p>
	 * 
	 * @param lnum
	 *            Parameter, of type java.util.List, is the number on the left
	 *            hand side of the multiplication expression. For e.g. In 2 x 3,
	 *            2 is at the left hand side.
	 * @param rnum
	 *            Parameter, of type java.util.List, is the number on the right
	 *            hand side of the multiplication expression. For e.g. In 2 x 3,
	 *            3 is at the right hand side.
	 * @return A list of type java.util.List, is the result of multiplication.
	 * 
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html">java.util.LinkedList</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html">java.util.Deque</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/List.html">java.util.List</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html">java.lang.Integer</a>
	 */
	private List<Integer> multiply(List<Integer> lnum, List<Integer> rnum) {

		@SuppressWarnings("unchecked")

		// Create an array of queues
		Deque<Integer>[] decks = new LinkedList[rnum.size()];

		// Iterates over the list represents the number on the right hand side
		// of the multiplication expression.
		for (int i = rnum.size() - 1, k = 0; i >= 0; i--, k++) {

			// Create an object of LinkedList to store the result of
			// multiplication performed during the iterations
			decks[k] = new LinkedList<>();

			// To store carry generated
			int carry = 0;

			// Iterates over the list represents the number on the left hand
			// side of the multiplication expression.
			for (int j = lnum.size() - 1; j >= 0; j--) {

				// Multiply digit by digit from right to left, taking care of
				// the carry generated
				int m = lnum.get(j).intValue() * rnum.get(i).intValue() + carry;

				if (m >= 10) {
					carry = m / 10;
					m %= 10;
				} else
					carry = 0;

				// Add the result of multiplication to the queue
				decks[k].addLast(m);
			}

			// Add carry, if any remains, to the queue
			if (carry > 0)
				decks[k].addLast(carry);

		}

		// Add trailing zeroes to the queue to make all queues equal in size
		for (int i = 0; i < decks.length; i++)
			for (int j = 0; j < i; j++)
				decks[i].addFirst(0);

		// Add leading zeroes to the queue, to make all queues equal in size
		for (int i = decks.length - 2; i >= 0; i--) {
			int s1 = decks[i + 1].size();
			int s2 = decks[i].size();
			for (int j = 0; j < s1 - s2; j++)
				decks[i].addLast(0);
		}

		// Create new list to store the result of addition of numbers stored in
		// queues
		List<Integer> resultList = new LinkedList<>();

		// To store carry generated
		int carry = 0;

		// Iterate over the array of queues until the first queue becomes empty.
		// Elements are removed automatically from a queue while it is polled.
		while (!decks[0].isEmpty()) {
			int sum = 0;

			// This loop iterates across queues to add up corresponding digits
			for (Deque<Integer> d : decks)
				sum += d.pollFirst().intValue();

			sum += carry;

			if (sum >= 10) {
				carry = sum / 10;
				sum %= 10;
			} else
				carry = 0;

			// Add the result of addition to the results queue
			resultList.add(0, sum);
		}

		// Add carry, if any remains, to the queue
		if (carry > 0)
			resultList.add(0, carry);

		return resultList;

	}

	/**
	 * The entry point of execution. Creates an instance of java.util.Scanner
	 * class to accept user input from standard input. As the execution starts,
	 * the method starts a loop and prompts to enter the number to compute
	 * factorial. It creates an instance of the Factorial class and calls
	 * {@link #getFactorial(int)} method to compute the factorial of the number
	 * entered as input. The result is printed and the user prompt repeats. This
	 * continues until the user enters a negative number as input to exit the
	 * loop and terminate the program.
	 * 
	 * @param args
	 *            An array of type String, is the command line parameters
	 *            injected at runtime.
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html">java.util.Scanner</a>
	 */
	public static void main(String[] args) {

		// Create an instance of java.util.Scanner class to accept input from
		// standard input console
		Scanner scan = new Scanner(System.in);

		// Create an instance of the Factorial class to compute factorial of the
		// number input.
		Factorial fact = new Factorial();

		try {

			// This loop helps to continue working with the program, entering as
			// many inputs as needed until a negative number is input which
			// exists the loop and terminates the program.
			while (true) {
				System.out.println("Enter a positive integer. Enter any negative number to quit.\n");

				// Read an integer from standard input.
				int input = scan.nextInt();

				// If number input is less than 0, exit, else compute factorial.
				if (input < 0)
					break;
				else
					System.out.println(fact.getFactorial(input));
			}
		} catch (InputMismatchException e) {
			// This exception is thrown in case invalid input is entered.
			System.out.println("Not a valid number\n");

			scan.next();
		} catch (Exception e) {
			// Generic exception handler
			System.out.println("An unexpected error occurred : " + e.getMessage());
		} finally {
			scan.close();
			System.out.println("\nTerminated");
		}
	}
}
