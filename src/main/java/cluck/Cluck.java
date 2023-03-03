package cluck;

import java.util.Objects;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;
import cluck.messages.Messages;
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
     * Instantiates a new Cluck with no arguments for JavaFx Application use.
     */
    public Cluck() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("C:/Users/User/OneDrive - National University of Singapore/"
                + "NUS/Y2S2/ip/data/CluckSave.txt");
    }

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

        while (isRunning) {
            userInput = ui.readInput();
            response = getResponse(userInput);
            ui.printResponse(response);
        }
        storage.writeSave(taskList);
    }
    private Command getCommand(String userInput) throws CluckException {
        return Parser.commandFactory(userInput);
    }
    private String executeCommand(Command command) throws CluckException {
        if (command instanceof ExitCommand) {
            isRunning = false;
        }
        return command.execute(taskList);
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        if (Objects.isNull(userInput)) {
            return Messages.MESSAGE_WELCOME;
        }
        Command currCommand;
        try {
            currCommand = getCommand(userInput);
            return executeCommand(currCommand);
        } catch (CluckException e) {
            return e.getMessage();
        }
    }

    /**
     * The entry point of application. Creates running instance of Cluck to handle task management
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Cluck("C:/Users/User/OneDrive - National University of Singapore/"
                + "NUS/Y2S2/ip/data/CluckSave.txt").run();
    }
}
