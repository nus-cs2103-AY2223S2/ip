package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String helpMessage = "Here are the available commands and their respective function: \n\n" +
                "- bye : Exit the program\n\n" +
                "- deadline [description] /by [yyyy-mm-dd] : Add a deadline event with its deadline specified\n\n" +
                "- delete [taskIndex] : Delete the task specified by the given index.\n\n" +
                "- event [description] /by [yyyy-mm-dd] /from [yyyy-mm-dd] : Add a deadline event with its starting " +
                "and ending date specified\n\n" +
                "- find [keyword] : List all the events that matches the input keyword. (case insensitive)\n\n" +
                "- help : Show help menu.\n\n" +
                "- list: Display all tasks in the current Task List.\n\n" +
                "- mark [taskIndex] : Mark the task specified by the given index as done.\n\n" +
                "- search [yyyy-mm-dd] : List all the Deadline tasks and Event tasks that takes place on the " +
                "given day.\n\n" +
                "- unmark [taskIndex] : Mark the task specified by the given index as undone.\n\n" +
                "- update [taskIndex] [description] : Update the description of the task specified by the given " +
                "index to be the new description.\n\n" +
                "- todo [description] : Add a todo event\n\n" +
                "All the command keywords are case insensitive!"
                ;
        ui.appendResponse(helpMessage);
    }
}
