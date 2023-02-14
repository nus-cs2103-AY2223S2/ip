package duke.components;

import duke.commands.Command;
import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is the driver class for Duke, the CLI task manager.
 * Every Duke object has its own storage, tasklist, and ui objects.
 *
 * @author Jian Cheng
 * @version 0.1
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Returns a new Duke object.
     * @param filePath the filepath of the serialized TaskList object stored in memory
     */
    public Duke(String filePath) {

        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        assert storage != null : "storage doesn't exist";
        assert tasks != null : "task-list doesn't exist";
    }

    public String getResponse(String userInput) {
        String response = "";
        try {
            Command c = Parser.parse(userInput);
            response = c.execute(tasks, storage);
        } catch (DukeException error) {
            return error.getMessage();
        }
        return response;
    }

    public static String getFilePath() {
        Path dirPath = Paths.get(".", "data");
        try {
            //This method creates a directory if it does not exist yet, but will not
            //throw an error even if it exists, and so is safe to call.
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path dataPath = Paths.get(dirPath.toString(), "DukeMem.ser");
        return dataPath.toString();
    }


}
