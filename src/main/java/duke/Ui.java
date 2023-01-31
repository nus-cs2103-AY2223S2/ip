package duke;
import java.util.Scanner;

/**
 * Ui to communicate with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Class constructor of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("    ______________________________________________________________");

    }

    /**
     * Reads the command from the user.
     * @return the string command that the user typed
     */
    public String readCommand() {
        String command = this.sc.nextLine();
        return command;
    }

    /**
     * Prints the content of the DukeException.
     * @param error the DukeException to be shown to the user
     */
    public void showError(DukeException error) {
        System.out.println(error);
    }

    /**
     * Prints the error that occurs during loading the data file.
     */
    public void showLoadingError() {
        System.out.println("The file does not exist.");
    }

    /**
     * Prints the user guide for different commands of Duke.
     */
    public void showGuide() {
        System.out.println("    list");
        System.out.println("    todo (content)");
        System.out.println("    deadline (content) /by (dd/MM/yyyy HH:mm)");
        System.out.println("    event (content) /from (dd/MM/yyyy HH:mm) /to (dd/MM/yyyy HH:mm)");
        System.out.println("    mark (index)");
        System.out.println("    unmark (index)");
        System.out.println("    delete (index)");
        System.out.println("    bye");
    }

    /**
     * Prints the farewell message.
     */
    public void end() {
        System.out.println("    Bye. Hope to see you again soon!");
        this.sc.close();
    }
}
