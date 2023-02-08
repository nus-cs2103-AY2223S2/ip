package duke.command;

import duke.exception.InvalidCmdException;
import duke.tasklist.Deadline;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.Todo;
import duke.tasklist.Event;

/**
 * Represents the class of command which adds into the taskList.
 */
public class AddCommand {

    private TaskList tasks;
    private String[] cmd;

    public AddCommand(TaskList arrList, String[] cmd) {
        this.tasks = arrList;
        this.cmd = cmd;
    }

    /**
     * Logic for the addition of the new task.
     * Returns a String representation of the text that is generated
     * upon running the specific add command.
     *
     * @return String that represents the program output.
     */
    public String execute() {
        Task task;
        switch(cmd[0]) {
        case "todo":
            task = new Todo(cmd[1]);
            break;
        case "deadline":
            String[] dl = cmd[1].split("/by ");
            try {
                validateDate(dl);
            } catch (InvalidCmdException e) {
                System.out.println(e.getMessage());
            }
            task = new Deadline(dl[0], dl[1]);
            break;
        case "event":
            String[] ev = cmd[1].split("/from");
            String[] time = ev[1].split("/to");
            task = new Event(ev[0], time[0], time[1]);
            break;
        default:
            return "An error has occurred!";
        }
        this.tasks.add(task);
        String toReturn = "Very nice. I've added this task:" + System.lineSeparator() + task
                + System.lineSeparator();
        toReturn = toReturn + "Now you have " + this.tasks.size() + " tasks in the list.";
        return toReturn;
    }

    /**
     * Ensures that of User inputs date as necessary for Events and Deadlines
     *
     * @param cmd String array representation of the command.
     * @throws InvalidCmdException If User fails to provide a Date.
     */
    public static void validateDate(String[] cmd) throws InvalidCmdException {
        if (cmd.length == 1) {
            throw new InvalidCmdException("Please specify date.");
        }
    }
}

