package duke;

import duke.dukeException.DukeException;
import duke.parser.Parser;
import duke.parser.Parser.Command;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.dukeException.UI.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A chatbot with functionality to add or remove different
 * types of Tasks.
 */
public class Duke {
    // Duke.Storage component handling loading and writing tasks.
    private Storage storage;
    // Task list with operations.
    private TaskList tasks;
    // Deals with interactions.
    private Ui ui;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath The location to load or save data from/to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main logic of the chatbot.
     * Supports 3 tasks: Todo, Event and Deadline.
     * Supports commands: adding, deletion, marking,
     * unmarking, listing.
     */
    public void run() {
        ui.showWelcome();
        // Scanner for user input.
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String input = userInput.nextLine();
            Command inputType = Parser.parse(input);

            ui.showLine();

            try {
                switch (inputType) {
                case LIST:
                    tasks.listTasks();
                    break;
                case MARK:
                    tasks.markTask(Parser.contents(input));
                    break;
                case UNMARK:
                    tasks.unmarkTask(Parser.contents(input));
                    break;
                case DELETE:
                    tasks.deleteTask(Parser.contents(input));
                    break;
                case UNKNOWN:
                    throw new DukeException("unknown");
                case BYE:
                    ui.showGoodbye();
                    break;
                default:
                    tasks.addTask(inputType, Parser.contents(input));
                    break;
                }
            } catch (DukeException e) {
                if (e.getMessage().equals("index")) {
                    System.out.println("☹ OOPS!!! Index out of range.");
                } else if (e.getMessage().equals("unknown")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    System.out.println("☹ OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect format detected.");
                System.out.println("Please enter date/time in the following format:");
                System.out.println("    yyyy-MM-dd HHmm");
            }

            storage.writeData();
            ui.showLine();

            if (inputType == Command.BYE) {
                break;
            }
        }

        userInput.close();
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }
}
