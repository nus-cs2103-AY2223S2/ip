package duke;

/**
 * Represents UI for Duke
 */
public class Ui {
    private static final String LINES = "\t____________________________________________________________";

    public Ui() {
    }

    /**
     * Prints a new line with specific format.
     *
     * @param hasNextLine Indicates that whether there is a new line.
     */
    public static void line(boolean hasNextLine) {
        if (hasNextLine) {
            System.out.println(LINES);
        } else {
            System.out.print(LINES);
        }
    }

    /**
     * Takes in a input string and prints it with a specific format.
     *
     * @param s String to be printed.
     */
    public static void print(String s) {
        line(true);
        System.out.println("\t" + s);
        line(true);
    }

    /**
     * Prints greeting message to user.
     */
    public static void greet() {
        print("Hello! I'm Duke\n"
                + "\tWhat can I do for you?");
    }

    /**
     * Prints exit message to user.
     */
    public static void goodbye() {
        print("Bye. Hope to see you again soon!");
    }

}
