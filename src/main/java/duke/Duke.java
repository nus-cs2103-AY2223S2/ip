package duke;

import java.io.IOException;

import duke.command.Command;
import duke.task.TaskList;

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

    /**
     * Creates a chatbot with the specified file path as storage.
     *
     * @param filePath File path to store data for tasks.
     * @throws IOException If an I/O error occurs.
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.getTasks());
    }

    /**
     * Returns the response of the chatbot based on the given input.
     *
     * @param input User's CLI input.
     * @return Chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input, taskList, storage);
            return command.execute();
        } catch (DukeException exception) {
            return "An error has occurred!\n" + exception.getMessage();
        }
    }
}
