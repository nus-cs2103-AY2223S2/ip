package genie.main;

import genie.command.Command;
import genie.exception.DukeException;

import java.io.*;
import java.util.ArrayList;

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
                return e.getMessage();
            } catch (DukeException e) {
                return (e.getMessage());
            }
        }
        return "This should not print.\n";
    }
    public String showGreetingMessage() {
        ArrayList<String> loadedTasks = storage.getLoadedTaskList();
        boolean hasLoadedTasks = !loadedTasks.isEmpty();
        if (hasLoadedTasks) {
            ui.appendOldUserGreeting(loadedTasks);
        }
        if (!hasLoadedTasks) {
            ui.appendNewUserGreeting();
        }
        String greetMessage = ui.getResponse();
        ui.clearResponse();
        return greetMessage;
    }
}