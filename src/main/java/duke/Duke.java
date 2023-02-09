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
        return Ui.genWelcomeMsg();
    }

    public void loadTaskList(String path) {
        try {
            storage = new Storage(path);
            storage.read(taskList);
        } catch (FileNotFoundException e) {

        }
    }

    public String genResponse(String userInput) {
        Command command = Parser.parse(userInput, taskList);
        return command.execute();
    }

    public void saveTaskList() {
        try {
            storage.save(taskList.toStorageString());
        } catch(IOException e) {

        }
    }

}
