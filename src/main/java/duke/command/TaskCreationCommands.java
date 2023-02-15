package duke.command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.TaskList;
import duke.task.Task;
import duke.UI.TextOutput;

/**
 * Executes the task creation commands stored in the command queue, by parsing the user input.
 */
public class TaskCreationCommands extends Command {

    public TaskCreationCommands(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Executes the task creation commands stored in the command queue, by parsing the user input.
     * @param list the list of tasks to execute the commands on.
     */
    @Override
    public String execute(TaskList list){
        String content = this.getCommandStorage();
        Task task;
        if (content.matches("^deadline\\s.*$")) {
            String back = content.substring(9);
            String[] substrings = back.split(" /by ");
            String date = substrings[1];
            task = new Deadline(substrings[0], date, false);
        } else if (content.matches("^event\\s.*$")) {
            String back = content.substring(6);
            String[] substrings = back.split(" /from ");
            String[] dates = substrings[1].split(" /to ");
            String from = dates[0];
            String to = dates[1];
            task = new Event(substrings[0], false, from, to);
        } else if (content.matches("^todo\\s.*$")) {
            String desc = content.substring(5);
            task = new Todo(desc, false);
        } else {
            return TextOutput.makeErrorString();
        }
        list.addTask(task);
        return TextOutput.makeAddString(task.toString(), list.getTaskCount());
    }
}