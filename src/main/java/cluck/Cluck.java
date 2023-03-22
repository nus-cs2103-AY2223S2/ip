package cluck;

import java.util.Objects;
import java.util.Scanner;

import cluck.commands.Command;
import cluck.commands.ExitCommand;
import cluck.exceptions.CluckException;
import cluck.messages.Messages;
import cluck.parser.Parser;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;


/**
 * Cluck class is the main Class and module for Cluck.
 */
public class Cluck {
    private final TaskList taskList;
    private final Storage storage;
    private boolean isRunning = true;

    /**
     * Instantiates a new Cluck with no arguments for JavaFx Application use.
     */
    public Cluck() {
        this.storage = new Storage("/data/cluckSave.txt");
        this.taskList = storage.readSave();

    }

    /**
     * Instantiates a Cluck class that will store the data in the given file path.
     *
     * @param filePath path of saved path as String.
     */
    public Cluck(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = storage.readSave();
    }

    /**
     * Starts cluck instance such that it loads saved data and
     * begins taking in user commands.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Messages.MESSAGE_WELCOME);
        while (isRunning) {
            String response = getResponse(scanner.nextLine());
            assert Objects.nonNull(response);
            System.out.println(response);
        }
        storage.writetoSave(taskList);
    }

    private Command getCommand(String userInput) throws CluckException {
        assert Objects.nonNull(userInput);
        return Parser.commandFactory(userInput);
    }

    private String executeCommand(Command command) throws CluckException {
        if (command instanceof ExitCommand) {
            isRunning = false;
        }
        return command.execute(taskList, storage);
    }

    /**
     * Gets response from execution of the command created by parsing userInput.
     *
     * @param userInput the user input.
     * @return the response.
     */
    public String getResponse(String userInput) {
        try {
            Command currCommand = getCommand(userInput);
            return executeCommand(currCommand);
        } catch (CluckException e) {
            return e.getMessage();
        }
    }

    /**
     * The entry point of application.
     * Creates running instance of Cluck to handle task management.
     *
     * @param args the input arguments.
     */
    public static void main(String[] args) {
        new Cluck("C:/Users/User/OneDrive - National University of Singapore/"
                + "NUS/Y2S2/ip/data/CluckSave.txt").run();
    }
}
