package duke;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exception.InvalidCommandException;
import duke.tasks.TaskList;
import duke.utils.DukeIo;
import duke.utils.MyDuke;
import duke.utils.Parser;
import duke.utils.Storage;

/**
 * The main class to run the Duke To-do application.
 */
public class Duke {
    /** Initialises the runner MyDuke */
    private static MyDuke duke = new MyDuke();
    /** Initialises DukeIO to handle user inputs and outputs */
    private static DukeIo dukeIo = new DukeIo();
    /** Initialises Storage class to fetch tasks or save tasks */
    private static Storage storage = new Storage();
    /** Initialises an EMPTY TaskList to maintain a List of Tasks */
    private static TaskList taskList = TaskList.ofNull();
    /** Initialises Parse class to tokenise user inputs */
    private static Parser parser = new Parser();

    /**
     * The Main method that starts Duke.
     * Upon boot, TaskList is loaded form save file.
     */
    public Duke() {
        duke.init();
        taskList.loadFrom(storage.load());
    }

    /**
     * Restricted method that accepts user inputs until the application is quit.
     * Saves TaskList to a .txt file upon quit.
     * @throws InvalidCommandException Occurs when user enters an unrecognised command.

    private static void runCommands() throws InvalidCommandException {
        boolean isBye = false;
        dukeIo.showPrompt();
        while (!isBye) {
            String[] tokens = parser.tokenise();
            isBye = handle(tokens, taskList);
            if (!isBye) {
                dukeIo.showPrompt();
            }
        }
    }
    */
    
    /**
     * Returns true if "bye" command is entered.
     * @param tokens tokens entered into the Command Line Interface
     * @param taskList List of tasks which commands are operated on.
     * @return true if user inputs "bye"
     * @throws InvalidCommandException
     */
    private static String handle(String[] tokens, TaskList taskList) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.equals("bye")) {
            return duke.quit();
        }
        return duke.exec(tokens, taskList);
    }

    public String getResponse(String input) throws InvalidCommandException {
        String[] tokens = input.split(" "); 
        return handle(tokens, taskList);
    }
}
