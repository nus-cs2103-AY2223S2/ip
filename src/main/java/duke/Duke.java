package duke;

import java.io.IOException;

import duke.commands.CommandInput;
import duke.commands.DeadlineCmd;
import duke.commands.DeleteCmd;
import duke.commands.EventCmd;
import duke.commands.ListCmd;
import duke.commands.MarkCmd;
import duke.commands.ToDoCmd;
import duke.commands.UnmarkCmd;
import duke.tasks.TaskList;

public class Duke {

    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();
        Storage.loadFromFile(taskList);

        Ui.greetUser();
        while (Ui.isRunning) {
            CommandInput command = CommandInput.getCommandInput(Ui.line);
            switch (command) {
            case LIST:
                new ListCmd(taskList, Ui.line).execute();
                break;
            case MARK:  
                new MarkCmd(taskList, Ui.line).execute();
                break;
            case UNMARK:
                new UnmarkCmd(taskList, Ui.line).execute();
                break;
            case DELETE:
                new DeleteCmd(taskList, Ui.line).execute();
                break;
            case EVENT:
                new EventCmd(taskList, Ui.line).execute();
                break;
            case DEADLINE:
                new DeadlineCmd(taskList, Ui.line).execute();
                break;
            case TODO:
                new ToDoCmd(taskList, Ui.line).execute();
                break;
            case BYE:
                Ui.shutDown();
                break;
            case UNRECOGNIZED_CMD:
                Ui.displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
            Ui.getNextCommand();
        }
        Storage.saveToFile(taskList);;
    }
}
