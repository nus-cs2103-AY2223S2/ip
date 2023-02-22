package cluck;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;
import cluck.parser.Parser;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.ui.Ui;


/**
 * Cluck class is the main Class and module for Cluck.
 */
public class Cluck {
    private final TaskList taskList;
    private final Ui ui;
    private boolean isRunning = true;
    private final Storage storage;

    /**
     * Cluck class contains and instance of TaskList, Ui, and Storage.
     * These classes are the building blocks of Cluck.
     *
     * @param filePath path of saved path as String
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
                if (currCommand instanceof ExitCommand) {
                    isRunning = false;
                }
            } catch (CluckException e) {
                response = e.getMessage();
            }
            ui.printResponse(response);
        }
        storage.writeSave(taskList);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Cluck("C:/Users/User/OneDrive - National University of Singapore/NUS/Y2S2/ip/data/CluckSave.txt").run();
    }
}
