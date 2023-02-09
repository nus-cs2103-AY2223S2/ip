package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.*;

public class Duke {
    private UI ui;
    private Storage storage;
    private static TaskList taskList;


    public Duke(String filePath) {
        loadTasks(filePath);
    }

    public boolean loadTasks(String filePath) {
        storage = Storage.createStorage(filePath);
        try {
            taskList = new TaskList(storage.load());
            return true;
        } catch (DukeException e) {
            taskList = new TaskList();
        }
        return false;
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        assert c != null;
        String response = "";
        try {
            response = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }

}