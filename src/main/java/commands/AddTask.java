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

public class AddTask implements Command{
    private Task task;
    private ArrayList<Task> taskList;

    public AddTask(String taskType, String args, ArrayList<Task> taskList) throws DukeException {
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
                    this.task = new Deadline(argumentsSplit[0], argumentsSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeDeadlineBadInput();
                }
                break;
            }
            case "event": {
                try {
                    String[] argumentsSplit = args.split(" /from ", 2);
                    String desc = argumentsSplit[0];
                    String[] fromAndTo = argumentsSplit[1].split(" /to ", 2);
                    this.task = new Event(desc, fromAndTo[0], fromAndTo[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeEventBadInput();
                }
                break;
            }
        }
        this.taskList = taskList;
    }

    public String execute(){
        this.taskList.add(task);
        return "added: " + this.task.getDescription();
    }
}
