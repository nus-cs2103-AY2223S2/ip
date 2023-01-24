package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * The chatbot that users will be interacting with.
 *
 * @author wz2k
 */
public class Duke {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /**
     * Creates a chatbot with the specified file path as storage.
     *
     * @param filePath File path to store data for tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(storage.getTasks());
        } catch (IOException exception) {
            this.ui.replyError(exception.getMessage());
        }
    }

    /**
     * Starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);

        duke.ui.greetUser();
        boolean end = false;

        while (!end) {
            try {
                String input = scanner.nextLine();
                Command command = parser.parseCommand(input, duke.ui, duke.taskList, duke.storage);
                end = command.execute();
            } catch (DukeException exception) {
                duke.ui.replyError(exception.getMessage());
            }
        }
    }
}
