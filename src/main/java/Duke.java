import java.util.Scanner;
import parser.inputParser;

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

        Duke.print(intro);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while (true) {
            String req = scanner.nextLine();  // Read user req
            inputParser input = new inputParser(req);
            String out = input.parse();

            if (out.equalsIgnoreCase("bye")) {
                break;
            }

            Duke.print(out);
        }
        Duke.print(extStr);
    }
}
