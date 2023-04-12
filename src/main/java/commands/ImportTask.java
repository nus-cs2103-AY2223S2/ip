package commands;

import exceptions.DukeDeadlineBadInput;
import exceptions.DukeEventBadInput;
import exceptions.DukeException;
import exceptions.DukeTodoNoDescription;
import storage.Storage;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

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

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.isDone.equals("true")) {
            this.task.importMark();
        }
        tasks.add(task);
    }

    public boolean isExit() {
        return false;
    }
}
