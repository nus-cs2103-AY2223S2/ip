package genie.main;

import genie.command.Command;
import genie.exception.DukeException;

import java.io.*;

/**
 * The main class for Genie, a Command-line Interface bot. Primarily functions as a task planner that responds to
 * and recognises user commands.
 */
public class Genie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Genie() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = storage.loadData();
        } catch (IOException e) {
           e.getMessage(); //todo fix this
        }
    }

    public String getResponse(String input) {
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser();
                Command c = parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExitCommand();
                String response = ui.getResponse();
                ui.clearResponse();
                return response;
            } catch (IOException e) {
                return e.getMessage(); //todo fix this
            } catch (DukeException e) {
                return (e.getMessage()); //todo fix this
            }
        }
        return "This should not print.\n";
    }
}