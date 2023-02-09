package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.*;

public class Duke {
    private UI ui;
    private Storage storage;
    private static TaskList taskList;


    public Duke(String filePath) {
        storage = Storage.createStorage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
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