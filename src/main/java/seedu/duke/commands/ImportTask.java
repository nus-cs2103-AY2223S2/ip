package seedu.duke.commands;

import seedu.duke.exceptions.DukeDeadlineBadInput;
import seedu.duke.exceptions.DukeEventBadInput;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DukeTodoNoDescription;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;
import seedu.duke.ui.Ui;

import java.time.LocalDate;

/**
 * Imports one task per command
 */
public class ImportTask implements Command{

    private Task task;
    private String isDone;

    // assumes save file has not been tampered with
    public ImportTask(String taskType, String isDone, String args, TaskList tasks) throws DukeException {
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
                    String[] argumentsSplit = args.split(",", 2);
                    this.task = new Deadline(argumentsSplit[0], LocalDate.parse(argumentsSplit[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeDeadlineBadInput();
                }
                break;
            }
            case "event": {
                try {
                    String[] argumentsSplit = args.split(",", 2);
                    String desc = argumentsSplit[0];
                    String[] fromAndTo = argumentsSplit[1].split(",", 2);
                    this.task = new Event(desc, LocalDate.parse(fromAndTo[0]), LocalDate.parse(fromAndTo[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeEventBadInput();
                }
                break;
            }
        }
        this.isDone = isDone;
    }

    /**
     * Executes the command with the specified TaskList, Ui, and, Storage
     * @param tasks The TaskList object containing the tasks
     * @param ui The Ui object handling input/output
     * @param storage The Storage object handling persistent storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.isDone.equals("true")) {
            this.task.importMark();
        }
        tasks.add(task);
    }

    /**
     * Returns whether the command is an Exit command
     * @return False
     */
    public boolean isExit() {
        return false;
    }
}
