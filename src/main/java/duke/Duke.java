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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.getCommandType() == Command.CommandType.EXIT;
            } catch (DukeException e) {
                ui.printFormattedOutput(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String response = "";
        try {
            response = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }

}