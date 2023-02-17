package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A personal assistant chatbox to manage daily tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private boolean isExit;

    /**
     * Constructs a new Duke object.
     */
    public Duke() {
        storage = new Storage();
        ui = new Ui();
        parser = new Parser();
        isExit = false;
    }

    /**
     * Runs CLI version of Duke.
     */
    private void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                tasks = storage.loadFileToTaskList();
                Command c = parser.parseInput(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e1) {
                ui.showError(e1);
            } catch (IOException e2) {
                ui.showError(e2);
            } finally {
                ui.printNewLine();
            }
        }
    }

    private void run(String input) {
        if (isExit) {
            ui.addToResponseMessage("I'm currently unavailable.");
            return;
        }
        try {
            tasks = storage.loadFileToTaskList();
            Command c = parser.parseInput(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e1) {
            ui.showError(e1);
        } catch (IOException e2) {
            ui.showError(e2);
        }
    }

    public String getResponse(String input) {
        run(input);
        String response = ui.showResponse();
        ui.clearResponse();
        return response;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
