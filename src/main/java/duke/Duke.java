package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Command;
import duke.util.Parser;
import duke.util.Storage;

public class Duke {

    private Storage storage;
    private boolean isTaskListLoaded;
    private TaskList taskList;


    public Duke(String filePath) {
        loadTasks(filePath);
    }

    public boolean loadTasks(String filePath) {
        storage = Storage.createStorage(filePath);
        try {
            taskList = new TaskList(storage.load());
            isTaskListLoaded = true;
        } catch (DukeException e) {
            taskList = new TaskList();
            isTaskListLoaded = false;
        }
        return isTaskListLoaded;
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        assert c != null;
        String response = "";
        try {
            response = c.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }

    public boolean getLoadedStatus() {
        return isTaskListLoaded;
    }

}