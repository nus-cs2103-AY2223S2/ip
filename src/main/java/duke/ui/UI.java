package duke.ui;

import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.exceptions.DukeException;
import duke.task.TaskList;


/**
 * Class that handles the interface that user interacts with.
 */
public class UI {
    private Scanner scanner;
    private Parser parser;

    /**
     * Constructor for UI object.
     * @param tasks A list of possible saved tasks from the previous session.
     */
    public UI(TaskList tasks) {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser(tasks);
    }

    /**
     * Greets the user and waits for input.
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello i'm\n"
                + logo
                + "\nWhat can I do for you?";
        System.out.println(Span.format(greeting));
    }

    /**
     * Bids the user farewell.
     */
    public void byeUser() {
        System.out.println(Span.format(
                "Bye. Hope to see you again soon!")
        );
    }

    /**
     * Processes user input.
     * @return a boolean value that signals if the program has come to an end.
     * @throws DukeException Throws DukeException if an invalid input is encountered.
     */
    public boolean processInput() throws DukeException {
        // get user input and trim trailing white sp
        String echo = scanner.nextLine().trim();
        String firstWord = echo.split(" ")[0];
        Command command = Command.get(firstWord);
        boolean exit = this.parser.execute(command, echo);
        return exit;
    }
}
