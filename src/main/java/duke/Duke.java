package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;

/**
 * Contains the entry point of the Duke application, a Personal Assistant Chatbot used
 * for keeping track of various things.
 */
public class Duke {
    private TaskList taskList = new TaskList();
    private Storage storage;

    public String getStartUpMsg() {
        return MessageGenerator.genWelcomeMsg();
    }

    /**
     * Loads previously saved task data at the given path
     *
     * @param path filepath to the .txt file containing the data to load
     */
    public void loadTaskList(String path) {
        assert taskList != null;
        try {
            storage = new Storage(path);
            storage.read(taskList);
        } catch (FileNotFoundException e) {
            // Don't do anything
        }
    }

    /**
     * Generates a response to the given input.
     *
     * @param userInput
     * @return
     */
    public DukeResponse genResponse(String userInput) {
        Command command = Parser.parse(userInput, taskList);
        assert command != null : "Command should not be null";
        return command.execute();
    }

    /**
     * Saves the current state of the task list to a .txt file. loadTaskList should always
     * be called before saveTaskList. The path for saveTaskList is the same as the path given to loadTaskList.
     */
    public void saveTaskList() {
        assert storage != null : "Storage should not be null when saving.";
        try {
            storage.save(taskList.toStorageString());
        } catch (IOException e) {
            // Don't do anything
        }
    }

}
