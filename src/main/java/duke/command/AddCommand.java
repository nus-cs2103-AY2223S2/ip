package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * AddCommands help calls the function add a new Task object be it Task, Event or Deadline into the task list.
 */
public class AddCommand extends Command {

    protected Task task;

    /**
     * constructor for AddCommand
     * @param task represent the either the to do task, event task or deadline task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * executes the purpose of the AddCommand
     * @param taskList is where a task will be added into the list
     * @param storage can be ignored but is required due to the abstract class
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        String output = "Got it. I've added this task:";
        taskList.addTask(task);

        output = output + "\n " + task;

        ui.printText(output + "\n\nYou have " + taskList.getNumberOfTask() + " tasks in the list.");

    }

    /**
     * indication if the command should end the program
     * @return false as addCommand is not an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String output = "todo taskname" +
                "\nThis command adds a todoTask" +
                "\nExample: todo Buy fruits";

        output  = output + "\n\ndeadline taskname /by dd/MM/yyyy HH:mm" +
                "\nThe commands shows add a deadline task" +
                "\nExample: deadline Math homework /by 08/03/2023 23:59";

        output = output + "\n\nevent taskname /by dd/MM/yyyy HH:mm /to dd/MM/yyyy HH:mm" +
                "\nThis command adds a event task"+
                "\nExample: event Science Fair at NUS /from 23/04/2023 10:00 /to 23/04/2023 18:00\n";

        return output;
    }
}
