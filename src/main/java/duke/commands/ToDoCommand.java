package duke.commands;
import duke.dukeexceptions.MissingArgumentException;
import duke.tasks.ToDo;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to add a "to do" task to a task list.
 */
public class ToDoCommand extends Command {
    private String requestContent;

    public ToDoCommand(String requestContent) {
        super("TODO");
        this.requestContent = requestContent;
    }

    @Override
    public String execute(TaskList tasks) throws MissingArgumentException {
        if (requestContent.trim().equals("")) {
            throw new MissingArgumentException("The description of a todo cannot be empty.");
        }
        ToDo newToDo = new ToDo(requestContent);
        tasks.addToDo(newToDo);

        String reply = "Got it. I've added this task:\n"
                + "  " + newToDo.toString()
                + "Now you have " + tasks.getLen() + " tasks in the list.\n";
        System.out.print(reply);
        return reply;
    }
}
