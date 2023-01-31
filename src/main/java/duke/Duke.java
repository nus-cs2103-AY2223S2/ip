package duke;

import java.io.IOException;

import duke.commands.CommandInput;
import duke.commands.DeadlineCmd;
import duke.commands.DeleteCmd;
import duke.commands.EventCmd;
import duke.commands.FindCmd;
import duke.commands.ListCmd;
import duke.commands.MarkCmd;
import duke.commands.ToDoCmd;
import duke.commands.UnmarkCmd;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;


/**
 * Main class where duke is initialized and runs.
 */
public class Duke {
    private TaskList tasks;

    public Duke() {
        tasks = new TaskList();
        try {
            Storage.loadFromFile(tasks);
        } catch (IOException e) {
            // Ui.showLoadingError();
        }
    }

    public void run() {
        Ui.greetUser();
        while (Ui.isRunning) {
            CommandInput command = CommandInput.getCommandInput(Ui.line);
            try {
                switch (command) {
                case LIST:
                    new ListCmd(tasks, Ui.line).execute();
                    break;
                case MARK:  
                    new MarkCmd(tasks, Ui.line).execute();
                    break;
                case UNMARK:
                    new UnmarkCmd(tasks, Ui.line).execute();
                    break;
                case DELETE:
                    new DeleteCmd(tasks, Ui.line).execute();
                    break;
                case EVENT:
                    new EventCmd(tasks, Ui.line).execute();
                    break;
                case DEADLINE:
                    new DeadlineCmd(tasks, Ui.line).execute();
                    break;
                case TODO:
                    new ToDoCmd(tasks, Ui.line).execute();
                    break;
                case BYE:
                    Ui.shutDown();
                    try {
                    Storage.saveToFile(tasks);;
                    } catch (IOException e) {
                        // Ui.showSavingError()
                    }
                    break;
                case FIND:
                    new FindCmd(tasks, Ui.line).execute();
                    break;
                case UNRECOGNIZED_CMD:
                    Ui.displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            } catch (CommandExecutionError e) {
                Ui.displayMsg("Couldn't execute command :/ \n" + e.toString());
            } 
            Ui.getNextCommand();
        }
    }

    // Initialize and run duke
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
