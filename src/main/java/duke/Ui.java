package duke;
import java.util.Scanner;

public class Ui {

    /**
     * Prints an error message if loading tasks from text file fails.
     */
    public void showLoadingError() {
        System.out.println("Unable to load tasks from storage");
    }

    /**
     * Prints the welcome message on start.
     */
    public void showWelcomeMessage() {
        String chopper =
                "           /\\_/\\\n" +
                        "          ( o.o )\n" +
                        "           > ^ <\n";
        System.out.println(chopper);
        System.out.println("    Hello I'm chopper\n" +
                "    My commands are the following:\n" +
                "      1. todo <description>\n" +
                "      2. deadline <description> /by <yyyy-MM-dd HHmm(optional)>\n" +
                "      3. event <description> /from <date> /to <date>\n" +
                "      4. delete <task number>\n" +
                "      5. mark <task number>\n" +
                "      6. unmark <task number>\n" +
                "      7. list\n" +
                "      8. bye\n" +
                "    What can I do for you?");
    }

    /**
     * Prints a line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________");
    }

    /**
     * Reads the user input from the command line
     * using a Scanner object.
     *
     * @return String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
