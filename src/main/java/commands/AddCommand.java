package commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;
import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;


public class AddCommand extends Command {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    TaskType taskType;

    public AddCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);

        switch (keyword) {
        case "todo":
            this.taskType = TaskType.TODO;
            break;
        case "deadline":
            this.taskType = TaskType.DEADLINE;
            break;
        case "event":
            this.taskType = TaskType.EVENT;
            break;
        default:
            throw new IllegalCommandException("Invalid keyword for AddCommand");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() == 0 || this.params.size() > 3) {
            throw new IllegalInputException("Wrong number of arguments to make a task!");
        }
        Task addedTask = null;
        switch (this.taskType) {
        case TODO:
            if (this.params.size() != 1) {
                throw new IllegalInputException("Too many arguments for a todo!");
            } else if (this.params.get(0).isEmpty()) {
                throw new IllegalInputException("Cannot make a todo without a description!");
            }
            addedTask = new ToDo(this.params.get(0));
            break;
        case DEADLINE:
            if (this.params.size() != 2) {
                throw new IllegalInputException("Wrong number of arguments for a deadline!");
            }
            try {
                addedTask = new Deadline(this.params.get(0), LocalDate.parse(this.params.get(1)));
            } catch (DateTimeParseException e) {
                throw new IllegalInputException("You have keyed in an invalid date");
            }
            break;
        case EVENT:
            if (this.params.size() != 3) {
                throw new IllegalInputException("Wrong number of arguments for an Event!");
            }
            try {
                addedTask = new Event(this.params.get(0),
                        LocalDate.parse(this.params.get(1)), LocalDate.parse(this.params.get(2)));
            } catch (DateTimeParseException e) {
                throw new IllegalInputException("You have keyed in an invalid date");
            }
            break;
        default:
            throw new IllegalCommandException("Unable to add task");
        }

        try {
            tasks.addTask(addedTask);
            storage.refreshStorage(tasks);
            ui.display("I have successfully added the following task for you! \n" + addedTask);
        } catch (IOException e) {
            ui.display("Seems like there is something wrong with the storage file \n"
                    + "Any updates will not be saved!");
            ui.errorDisplay(e);
            e.printStackTrace();
        }

    }
}
