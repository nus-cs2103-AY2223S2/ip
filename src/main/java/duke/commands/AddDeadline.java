package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

public class AddDeadline extends Command{
    private String description;
    private String dueBy;

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

    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
            ui.printAddedTask(d, tasks.size());

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
