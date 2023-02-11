package duke;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Contains the entry point of the Duke application, a Personal Assistant Chatbot used
 * for keeping track of various things.
 */
public class Duke {
    public TaskList taskList = new TaskList();
    public Storage storage;

    public String getStartUpMsg() {
        return MessageGenerator.genWelcomeMsg();
    }

    public void loadTaskList(String path) {
        assert taskList != null;
        try {
            storage = new Storage(path);
            storage.read(taskList);
        } catch (FileNotFoundException e) {
            // Don't do anything
        }
    }

    public DukeResponse genResponse(String userInput) {
        Command command = Parser.parse(userInput, taskList);
        assert command != null : "Command should not be null";
        return command.execute();
    }

    public void saveTaskList() {
        assert storage != null : "Storage should not be null when saving.";
        try {
            storage.save(taskList.toStorageString());
        } catch(IOException e) {
            // Don't do anything
        }
    }

}
