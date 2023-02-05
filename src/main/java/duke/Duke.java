package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.packages.AddCommand;
import duke.packages.DeleteCommand;
import duke.packages.FindCommand;
import duke.packages.ListCommand;
import duke.packages.MarkCommand;
import duke.packages.UnmarkCommand;
import exceptions.IncorrectNoOfArgumentException;

/**
 * Represents the Duke application, capable of storing 3 types of Tasks ("ToDos", "Deadline" & "Event")
 * in the hard disk for users to refer to at any point of time.
 * <p></p>
 * Acts as a form of reminder / tracker and supports the following functions:
 * add, remove, mark, unmark and display list.
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object that requires the filePath and folderPath to initialize the application,
     * retrieving tasks stored in the hard disk.
     *
     * @param filePath  Relative path to locate the file with the stored tasks.
     * @param folderPath Relative path to locate the directory storing the file.
     */
    public Duke(String filePath, String folderPath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, folderPath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showLoadingError(e);
            this.tasks = new TaskList(new ArrayList<String>());
        }
    }

    /**
     * Executes the Duke application, requires user input to perform necessary actions and stops the Duke application
     * when user inputs the given command to end.
     * <p></p>
     * Functions currently supported by Duke: add Task, remove Task, mark Task, unmark Task, delete Task, list, bye.
     * @throws IOException On input error.
     * @see IOException
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            ArrayList<String> commandInfoList;
            try {
                commandInfoList = Parser.parse(ui.readCommand());
            } catch (IncorrectNoOfArgumentException e) {
                System.out.println(e);
                continue;
            }
            if (commandInfoList.size() != 0) {
                switch (commandInfoList.get(0)) {
                case "bye":
                    isExit = true;
                    ui.showFarewellMessage();
                    break;
                case "list":
                    this.tasks = new ListCommand().execute(commandInfoList, this.tasks, this.storage, this.ui);
                    break;
                case "find":
                    this.tasks = new FindCommand().execute(commandInfoList, this.tasks, this.storage, this.ui);
                    break;
                case "mark":
                    this.tasks = new MarkCommand().execute(commandInfoList, this.tasks, this.storage, this.ui);
                    break;
                case "unmark":
                    this.tasks = new UnmarkCommand().execute(commandInfoList, this.tasks, this.storage, this.ui);
                    break;
                case "delete":
                    this.tasks = new DeleteCommand().execute(commandInfoList, this.tasks, this.storage, this.ui);
                    break;
                case "error":
                    break;
                default:
                    try {
                        this.tasks = new AddCommand().execute(commandInfoList, this.tasks, this.storage, this.ui);
                    } catch (DateTimeParseException e) {
                        ui.printInvalidDateError();
                        break;
                    }
                    break;
                }
            }
        }
    }

    /**
     * This is the main method that creates the Duke object and boots the application up via run() method.
     *
     * @param args Input given by users via the CLI
     * @throws IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/storage.txt", "data").run();
    }
}
