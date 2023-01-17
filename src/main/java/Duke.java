import java.util.Scanner;

public class Duke {
    /**
     * Prints formatted response to the console.
     *
     * @param s string that will be printed
     */
    public static void print(String s) {
        String p = String.format("\t____________________________________________________________\n" +
                "\t %s\n" +
                "\t____________________________________________________________\n", s);
        System.out.println(p);
    }

    public static void main(String[] args) {
        final String intro = "Hello! I'm Duke\n\t What can I do for you?";
        final String extStr = "Bye. Hope to see you again soon!";
        final String empStr = "Please enter an input for me to echo!";

        Duke.print(intro);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while (true) {
            String input = scanner.nextLine();  // Read user input

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equals("")) {
                Duke.print(empStr);
            } else {
                Duke.print(input);
            }
        }
        Duke.print(extStr);
    }
}
