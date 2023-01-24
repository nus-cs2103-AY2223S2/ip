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
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.getTasks());
        } catch (IOException exception) {
            ui.replyError(exception.getMessage());
        }
    }

    /**
     * Starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        Scanner scanner = new Scanner(System.in);
        boolean isEnd = false;

        duke.ui.greetUser();

        while (!isEnd) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parseCommand(input, duke.ui, duke.taskList, duke.storage);
                isEnd = command.execute();
            } catch (DukeException exception) {
                duke.ui.replyError(exception.getMessage());
            }
        }
    }
}
