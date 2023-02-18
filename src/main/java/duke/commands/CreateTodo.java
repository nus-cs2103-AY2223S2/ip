package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;
import duke.tasktype.Todo;

/**
 * The class for the todo commands.
 */
public class CreateTodo extends Command {
    private String cmdLine;

    public CreateTodo(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    /**
     * Creates a todo task for the user.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 5) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Todo task or not?");
            }

            String task = cmdLine.substring(5);
            lst.add(new Todo(task));
            String response = "";
            response += "New Todo task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!\n";
            response += "  " + lst.get(lst.size() - 1).toString() + "\n";
            response += "You save " + lst.size() + " tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!";
            storage.save(lst);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
