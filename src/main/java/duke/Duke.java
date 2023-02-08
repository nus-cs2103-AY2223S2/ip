package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.UI;

public class Duke {
    private UI ui;
    private Storage storage;
    private static TaskList taskList;


    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
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