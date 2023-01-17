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
        String intro = "Hello! I'm Duke\n\t What can I do for you?";
        Duke.print(intro);

        String input;
        while (true) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String in = myObj.nextLine();  // Read user input
            input = in; // save the input

            if (input.equalsIgnoreCase("bye")) {
                String extStr = "Bye. Hope to see you again soon!";
                Duke.print(extStr);
                System.exit(0);
            } else if (input.equals("")) {
                String empStr = "Please enter an input for me to echo!";
                Duke.print(empStr);
            } else {
                Duke.print(input);
            }
        }


    }
}
