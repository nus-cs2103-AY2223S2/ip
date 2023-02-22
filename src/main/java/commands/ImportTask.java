package commands;

import exceptions.DukeDeadlineBadInput;
import exceptions.DukeEventBadInput;
import exceptions.DukeException;
import exceptions.DukeTodoNoDescription;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;

public class ImportTask implements Command{

    private Task task;
    private ArrayList<Task> taskList;
    private String isDone;

    // assumes save file has not been tampered with
    public ImportTask(String taskType, String isDone, String args, ArrayList<Task> taskList) throws DukeException {
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
                    this.task = new Deadline(argumentsSplit[0], argumentsSplit[1]);
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
                    this.task = new Event(desc, fromAndTo[0], fromAndTo[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeEventBadInput();
                }
                break;
            }
        }
        this.isDone = isDone;
        this.taskList = taskList;
    }

    public String execute(){
        if (this.isDone.equals("true")) {
            this.task.importMark();
        }
        this.taskList.add(task);
        return "added: " + this.task.getDescription();
    }
}
