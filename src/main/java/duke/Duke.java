package duke;

import duke.exception.InvalidCommandException;
import duke.tasks.TaskList;
import duke.utils.MyDuke;
import duke.utils.Parser;
import duke.utils.Storage;

/**
 * The class to run the Duke To-do application.
 */
public class Duke {
    /** Initialises the runner MyDuke */
    private static MyDuke duke = new MyDuke();
    /** Initialises Storage class to fetch tasks or save tasks */
    private static Storage storage = new Storage();
    /** Initialises an EMPTY TaskList to maintain a List of Tasks */
    private static TaskList taskList = TaskList.ofNull();
    /** Initialises Parse class to tokenise user inputs */
    private static Parser parser = new Parser();
    private static boolean isExit;
    /**
     * The Main method that starts Duke.
     * Upon boot, TaskList is loaded form save file.
     */
    public Duke() {
        duke.init();
        taskList.loadFrom(storage.load());
        Duke.isExit = false;
    }
    
    /**
     * Returns true if "bye" command is entered.
     * @param tokens tokens entered into the Command Line Interface
     * @param taskList List of tasks which commands are operated on.
     * @return String reply from the Duke
     * @throws InvalidCommandException
     */
    private static String handle(String[] tokens, TaskList taskList) throws InvalidCommandException {
        String cmd = parser.getCommand(tokens);
        if (cmd.equals("bye")) {
            isExit = true;
            return duke.quit();
        }
        return duke.exec(tokens, taskList);
    }

    public String getResponse(String input) throws InvalidCommandException {
        String[] tokens = parser.tokenise(input); 
        return handle(tokens, taskList);
    }
}
