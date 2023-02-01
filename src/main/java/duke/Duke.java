package duke;

import duke.DukeException.InvalidCommandException;
import duke.Tasks.TaskList;
import duke.utils.DukeIO;
import duke.utils.MyDuke;
import duke.utils.Parser;
import duke.utils.Storage;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

/**
 * The main class to run the Duke application.
 */
public class Duke {
    /** Initialises the runner MyDuke. */
    private static MyDuke duke = new MyDuke();
    /** Initialises DukeIo to handle user inputs and outputs.  */
    private static DukeIO dukeIo = new DukeIO();
    /** Initialises Storage class to fetch tasks or save tasks  */
    private static Storage storage = new Storage();
    /** Initialises an empty TaskList to maintain a list of tasks. */
    private static TaskList taskList = TaskList.ofNull();
    /** Initialises a parser class to tokenise user inputs. */
    private static Parser parser = new Parser();

    /**
     * The main method that starts Duke.
     * Upon boot, taskList is loaded from save file.
     * Outputs "Nothing to load" if save file does not exist
     * 
     * @param args
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
            // Save file does not exist
            dukeIo.echoMessage("Nothing to load");
        } catch (EOFException e) {
            // Save file exists but is corrupted or does not work.
            dukeIo.echoMessage("Unable to load from save file.");
        }        
        processCommands();        
    }

    /**
     * Restricted method that accepts user inputs until the application is quit.
     * 
     * @throws InvalidCommandException
     * @throws IOException
     */
    private static void processCommands() 
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
        storage.saveFrom(taskList.getAllTasks());
    }

    /**
     * Returns true if "bye" command is entered.
     * Else, passes the chain of responsibility to MyDuke.
     * 
     * @param tokens
     * @param taskList
     * @return true if user inputs "bye"
     * @throws InvalidCommandException
     */
    private static boolean handle(String[] tokens, TaskList taskList) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.length()==0) {  
            // allow user to press "enter" continuously
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
