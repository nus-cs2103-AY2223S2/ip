package duke;

import duke.commands.Command;
import duke.tasks.TaskList;
import duke.utils.DukeIo;
import duke.utils.Parser;
import duke.utils.Storage;

/**
 * The class to run the Duke To-do application.
 */
public class Duke {
    /** Initialises Storage class to fetch tasks or save tasks */
    private static Storage storage = new Storage();
    /** Initialises an EMPTY TaskList to maintain a List of Tasks */
    private static TaskList taskList = TaskList.ofNull();
    /** Initialises Parse class to tokenise user inputs */
    private static Parser parser = new Parser();
    private static DukeIo dukeIo = new DukeIo();
    private static boolean isBye;

    /**
     * The Main method that starts Duke.
     * Upon boot, TaskList is loaded form save file.
     */
    public Duke() {
        taskList.loadFrom(storage.load());
        Duke.isBye = false;
    }

    /**
     * Shows hello message upon start up.
     * @return User-friendly interpretation of welcome message.
     */
    public String showHello() {
        return storage.isLoadSuccess() ? 
            dukeIo.printHello() + dukeIo.notifyLoad() : dukeIo.printHello();
    }

    /**
     * Returns true if "bye" command is entered.
     * @param tokens tokens entered into the Command Line Interface
     * @param taskList List of tasks which commands are operated on.
     * @return String reply from the Duke
     */
    private static String handle(String tokens, TaskList taskList) {
        parser.tokenise(tokens);
        Command command = parser.getCommand();
        Duke.isBye = command.isExitCommand();
        return command.exec(dukeIo, taskList);
    }

    /**
     * Tokenises the input from user with parser. Then, handles the command
     * @param input Input entered by user
     * @return The UI response from Duke
     */
    public String getResponse(String input) {
        return handle(input, taskList);
    }

    /**
     * Checks if user has entered a command to quit.
     * @return True if user has entered a command to quit. False otherwise.
     */
    public boolean isBye() {
        return Duke.isBye;
    }
}
