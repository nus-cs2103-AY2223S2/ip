package duke.ui;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

import java.util.Scanner;

/**
 * Represents a User Interface (Ui) instance.
 */
public class Ui {

    /** Scanner Object for reading User input. */
    private Scanner scanner;
    private Parser parser;

    public Ui(TaskList tasks) {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser(tasks);
    }

    /**
     * Prints greeting message for User upon startup.
     */
    public void sayHi() {
        String newLine = System.getProperty("line.separator");
        System.out.println("-------------------------------------------------------"
                + newLine + "Hello! Jak Sie Masz! I am Borat.\n What I do for you Premier Azamat?");
    }

    /**
     * Prints goodbye message for User upon exit.
     */
    public void sayBye() {
        System.out.println("Chenquieh. Hope to see you again Premier Azamat!");
    }

    /**
     * Parses the user's input as Duke runs.
     */
    public boolean parseInput() throws DukeException {
        String echo = scanner.nextLine().trim(); // get user input and trim trailing white sp
        boolean toExit = this.parser.parse(echo);
        return toExit;
    }
}
