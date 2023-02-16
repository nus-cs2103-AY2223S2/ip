package genie.main;

import genie.command.Command;
import genie.exception.GenieException;

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

    /**
     * A constructor for Genie.
     */
    public Genie() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = storage.loadData();
        } catch (IOException e) {
           e.getMessage();
        }
    }

    /**
     * Retrieves Genie's response according to the user's input.
     * @param input user's input
     * @return response
     */
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
            } catch (GenieException e) {
                return (e.getMessage());
            }
        }
        return "This should not print.\n";
    }

    /**
     * Shows greeting message when the app launches.
     * @return greeting message
     */
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