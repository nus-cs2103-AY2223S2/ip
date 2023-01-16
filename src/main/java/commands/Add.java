package commands;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.ITask;
import tasks.Todo;
import uitilties.UserInterface;

import java.util.ArrayList;

public class Add extends ICommand {
    private final String _input;
    private final ITask.TaskTypes _type;

    public Add(ArrayList<ITask> tasks, String input, ITask.TaskTypes type) {
        super(tasks);
        _input = input;
        _type = type;
    }


    @Override
    public boolean run() throws DukeException {
        ITask task;
        switch (_type) {
            case ToDos:
                task = new Todo(_input);
                getTasks().add(task);
                break;
            case Events:
                task = new Event(_input);
                getTasks().add(task);
                break;
            case Deadlines:
                task = new Deadline(_input);
                getTasks().add(task);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + _type);
        }
        UserInterface.Speak(task + "\nAdded");
        return false;

    }
}
