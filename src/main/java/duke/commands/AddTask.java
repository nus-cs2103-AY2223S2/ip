package duke.commands;
import duke.exceptions.DukeDeadlineBadInput;
import duke.exceptions.DukeEventBadInput;
import duke.exceptions.DukeException;
import duke.exceptions.DukeTodoNoDescription;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTask implements Command{
    private Task task;

    public AddTask(String taskType, String args) throws DukeException {
        switch (taskType) {
            case "todo": {
                if (args == "") {
                    throw new DukeTodoNoDescription();
                }
                this.task = new Todo(args);
                break;
            }
            case "deadline": {
                try {
                    String[] argumentsSplit = args.split(" /by ", 2);
                    this.task = new Deadline(argumentsSplit[0], LocalDate.parse(argumentsSplit[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeDeadlineBadInput();
                } catch (DateTimeParseException e) {
                    throw new DukeDeadlineBadInput();
                }
                break;
            }
            case "event": {
                try {
                    String[] argumentsSplit = args.split(" /from ", 2);
                    String desc = argumentsSplit[0];
                    String[] fromAndTo = argumentsSplit[1].split(" /to ", 2);
                    this.task = new Event(desc, LocalDate.parse(fromAndTo[0]), LocalDate.parse(fromAndTo[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeEventBadInput();
                } catch (DateTimeParseException e) {
                    throw new DukeEventBadInput();
                }
                break;
            }
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAddTaskSuccess(task.getDescription());
    }

    public boolean isExit() {
        return false;
    }
}
