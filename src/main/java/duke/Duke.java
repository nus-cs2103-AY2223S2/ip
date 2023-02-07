package duke;

import javafx.application.Platform;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    protected String getResponse(String input) {
        try {
            return Parser.handleInput(input, ui, tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Something went wrong while handling this task.";
        } catch (IOException e) {
            return "Something went wrong";
        }
    }

}
