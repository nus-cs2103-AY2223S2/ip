package duke;

import duke.DukeException.InvalidCommandException;
import duke.Tasks.TaskList;
import duke.utils.DukeIo;
import duke.utils.MyDuke;
import duke.utils.Parser;
import duke.utils.Storage;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

/** 
 * The main class to run the Duke To-do application.
 * 
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
     * Outputs "Nothing to Load" if save file does not exist.
     * 
     * @param args The command line arguments. Currently, no arguments accepted.
     * @throws InvalidCommandException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) 
            throws InvalidCommandException, IOException, ClassNotFoundException {
        
        duke.init();

        try {
            taskList.loadFrom(storage.load());
        } catch (FileNotFoundException p) {
            // save file has not been created.
            dukeIo.echoMessage("Nothing to load");
        } catch (EOFException e) {
            // save file is corrupted or does not work
            dukeIo.echoMessage("Unable to load from save file.");
        }

        runCommands();        
    }

    /**
     * Restricted method that accepts user inputs until the application is quit.
     * Saves TaskList to a .txt file upon quit.
     * 
     * @throws InvalidCommandException
     * @throws IOException
     */
    private static void runCommands() 
            throws InvalidCommandException, IOException {
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

    /**
     * Returns true if "bye" command is entered.
     * 
     * @param tokens
     * @param taskList
     * @return true if user inputs "bye"
     * @throws InvalidCommandException
     */
    private static boolean handle(String[] tokens, TaskList taskList) 
        throws InvalidCommandException, IOException {
        String cmd = tokens[0];
        // Allows user to press "Enter" continuously
        if (cmd.length() == 0) {  
            return false;    
        } else if (cmd.equals("bye")) {  
            duke.quit(); 
            return true; 
        } else {   
            duke.exec(tokens, taskList); 
        }
        return false;
    }
}
