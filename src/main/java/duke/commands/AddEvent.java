package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

/**
 * > This class is a command that adds an event to the calendar
 */
public class AddEvent extends Command {
    private String description;
    private String start;
    private String end;

    // This is the constructor of the class AddEvent. It takes in a string input and splits it into 3
    // parts, the description, start and end of the event.
    public AddEvent(String input) {
        try {
            if (input.length() < 6) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty");
            }
            this.description = input.substring(6);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function creates a new event object with the description, start and end time, adds it to
     * the task list, saves the task list to storage and prints the added task to the user
     *  @param tasks the list of tasks
     * @param ui the user interface
     * @param storage Storage
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            // Splitting the input into an array of strings using /from and /to to seperate the strings.
            String[] arrOfStr = this.description.split("/from|/to");
            if (arrOfStr.length < 3) {
                throw new DukeException("please indicate the event in the follow format: (event description) + /from (event start date/time) /to (event end date/time)");
                
            } else {
            //stores the first index in the array as the task description
            //second index as start of event and third index as end of event
                this.description = arrOfStr[0];
                this.start = arrOfStr[1];
                this.end = arrOfStr[2];
            }
            Event e = new Event(this.description, this.start, this.end);
            tasks.add(e);
            storage.saveTaskList(tasks);
            return ui.printAddedTask(e, tasks.size());

        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}