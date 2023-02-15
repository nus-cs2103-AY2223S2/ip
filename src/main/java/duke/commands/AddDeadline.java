package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

/**
 * The AddDeadline class is a subclass of the Command class. It has two private fields, description and
 * due_by. It has a constructor that takes in a string input and splits the input into two parts, the
 * description and the due_by date. It has an execute method that takes in a TaskList, Ui and Storage
 * object. It creates a new Deadline task object and adds it to the TaskList. It then saves the TaskList to
 * the storage and prints out the new tasklist
 */
public class AddDeadline extends Command{
    private String description;
    private String dueBy;

    // A constructor that takes in a string input and splits the input into two parts, the
    //  * description and the due_by date.
    public AddDeadline(String input) {
        try {
            if (input.length() < 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
            }

            this.description = input.substring(9);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The function splits the input using '/by', stores the split string in an arr of string, stores
     * the first index in the array as a task description, and creates a new Deadline object with the
     * description and due_by date
     *  @param tasks a list of tasks
     * @param ui a reference to the UI object
     * @param storage a Storage object that contains the methods to save the tasks to a file
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            //splits the input using '/by', stores the split string in an arr of string
            String[] arrOfStr = description.split("/by ");

            //stores the first index in the array as a task description
            if (arrOfStr.length < 2) {
                throw new DukeException("please indicate the deadline in the follow format: (deadline description) + /by (deadline date in the format: YYYY-MM-DD)");
                
            } else {
                this.description = arrOfStr[0];
                this.dueBy = arrOfStr[1];
            }
            Deadline d = new Deadline(this.description, this.dueBy);
            tasks.add(d);
            storage.saveTaskList(tasks);
            return ui.printAddedTask(d, tasks.size());

        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }

    }
}
