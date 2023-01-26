package duke;
import duke.dukeexceptions.DukeException;
import java.util.Scanner;

/**
 * Class that prints output for the user.
 */
public class Ui {
    private Scanner r;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.r = new Scanner(System.in);
    }

    /**
     * Greets the user upon starting.
     */
    public void greetUser() {
        System.out.println("Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n");
    }

    /**
     * Returns the command given by the user.
     *
     * @return the command read from the user.
     */
    public String readCommand(){
        String input = r.nextLine();
        return input;
    }

    /**
     * Returns boolean based on the command from the user.
     * Prints a reply to the user.
     *
     * @param s command string given by the user.
     * @return a boolean indicating if the program should still run.
     */
    public boolean replyUser(String s) {
        if (s == "bye") {
            sayBye();
            return true;
        }
        System.out.println(s);
        return false;
    }

    /**
     * Prints error messages.
     *
     * @param e an exception met by Duke.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("__________________________________");
    }

    /**
     * Prints goodbye message to the user upon exit.
     */
    public void sayBye() {
        System.out.println("Bye I'm gonna go eat cookies. Hope to see you again soon!");
    }
}
