package duke;
import java.io.IOException;

import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;


public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    public void run() throws IOException {
        ui.greetUser();

        try {
            this.tasks = new TaskList(storage.load());
            ui.notifySuccessfulLoad();
        } catch (DukeException e) {
            ui.printException(e);
        }

        awaitInput();

    }
    
    private void awaitInput() throws IOException {

        String userInput = ui.getUserInput();

        while (!userInput.toUpperCase().equals("BYE")) {   
            this.handleInput(userInput); 
            userInput = ui.getUserInput();
        }

        this.endSession();
    }

    private void handleInput(String userInput) {

        try {
            Command c = Parser.parseCommand(userInput);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            this.ui.printException(e);
        }
    
    }

    private void endSession() throws IOException {
        storage.store(tasks);
        ui.endSession();
    }

}
