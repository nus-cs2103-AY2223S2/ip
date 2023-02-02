import java.util.Scanner;

public class Ui {

    private final Scanner SC; // scanner to read user input.
    public Ui() {
        this.SC = new Scanner(System.in);
    }

    /**
     * @param txt text to indent.
     * @return indented string.
     */
    private static String autoIndent(String txt) {
        return "    " + txt.replace("\n", "\n    ");
    }

    /**
     * prints border line.
     */
    private static void borderLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    /**
     * Prints output in a nice format. (adds borders and indentation).
     *
     * @param inp input string.
     */
    public void prettifyOut(String inp) {
        borderLine();
        System.out.println(autoIndent(inp));
        borderLine();
    }

    /**
     * @return the command entered by the user.
     */
    public String readCommand() {
        return this.SC.nextLine();
    }

    /**
     * Prints border line.
     */
    public void printBorderLine() {
        borderLine();
    }


    /**
     * Prints out the Greeting message.
     */
    public void printGreeting() {
        String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
        prettifyOut(GREETING);
    }

    /**
     * Prints out the Goodbye message.
     */
    public void printGoodbye() {
        String GOODBYE = "Bye. Hope to see you again soon!";
        prettifyOut(GOODBYE);
    }

    public void showLoadingError() {
        String ERROR = "Error loading file";
        prettifyOut(ERROR);
    }

    public void printDukeException(DukeException e) {
        prettifyOut(e.getMessage());
    }

    public void printTaskAdded(Task task, int size) {
        prettifyOut("Got it. I've added this task:\n  " + task + "\nNow you have " + size +
                " tasks in the list.");
    }

    public void printTaskMarked(Task task) {
        prettifyOut("Nice! I've marked this task as done:\n  " + task);
    }

    public void printTaskUnmarked(Task task) {
        prettifyOut("OK, I've marked this task as not done yet:\n" + task);
    }

    public void printTaskDeleted(Task task, int size) {
        prettifyOut("Noted. I've removed this task:\n  " + task + "\nNow you have " + size +
                " tasks in the list.");
    }
}
