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
    public String sayHi() {
        String newLine = System.getProperty("line.separator");
        return "-------------------------------------------------------"
                + newLine + "Hello! Jak Sie Masz! I am Borat.\n What I do for you Premier Azamat?";
    }

    /**
     * Prints goodbye message for User upon exit.
     */
    public String sayBye() {
        return "Chenquieh. Hope to see you again Premier Azamat!";
    }
    /**
     * Parses the user's input as Duke runs.
     */
    public String parseInput() throws DukeException {
        String echo = scanner.nextLine().trim(); // get user input and trim trailing white sp
        return this.parser.parse(echo);
    }

    public String parseInput(String echo) throws DukeException {
        return this.parser.parse(echo);
    }
    public String showError(Exception e) {
        return e.getMessage();
    }
}


