package cluck;

import cluck.commands.Command;
import cluck.exceptions.CluckException;
import cluck.parser.Parser;
import cluck.storage.Storage;
import cluck.taskList.TaskList;
import cluck.ui.Ui;



public class Cluck {
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning = true;
    private Storage storage;

    /**
     * Cluck class contains tasklist, User interface and parser needed.
     */
    public Cluck(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.readSave();
    }

    /**
     * Starts cluck instance such that it loads saved data and
     * begins taking in user commands.
     */
    public void run() {
        ui.greetUser();
        String userInput;
        String response;
        Command currCommand;
        while (isRunning) {
            userInput = ui.readInput();
            try {
                currCommand = Parser.commandFactory(userInput);
                response = currCommand.execute(taskList);
            } catch (CluckException e) {
                response = e.getMessage();
            }
            ui.printResponse(response);
        }
    }

    public static void main(String[] args) {
        new Cluck("data/CluckSave.txt").run();

    }
}
