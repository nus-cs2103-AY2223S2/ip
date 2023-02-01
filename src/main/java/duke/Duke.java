package duke;

import duke.DukeException.InvalidCommandException;
import duke.Tasks.TaskList;
import duke.utils.DukeIO;
import duke.utils.MyDuke;
import duke.utils.Parser;
import duke.utils.Storage;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    private static MyDuke duke = new MyDuke();
    private static DukeIO dukeIo = new DukeIO();
    private static Storage storage = new Storage();
    private static TaskList taskList = TaskList.ofNull();
    private static Parser parser = new Parser();

    public static void main(String[] args) 
            throws InvalidCommandException, IOException, ClassNotFoundException {
        
        duke.init();

        try {
            taskList.loadFrom(storage.load());
        } catch (FileNotFoundException p) {
            dukeIo.echoMessage("Nothing to load");
        }

        processCommands();        
    }

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

    private static boolean handle(String[] tokens, TaskList taskList) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.length()==0) {  
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
