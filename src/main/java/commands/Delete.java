package commands;

import exceptions.DukeException;
import exceptions.InvalidMarkInput;
import tasks.ITask;
import uitilties.UserInterface;

import java.util.ArrayList;

public class Delete extends ICommand {
    private final String _input;

    public Delete(ArrayList<ITask> tasks, String input) {
        super(tasks);
        _input = input;
    }

    @Override
    public boolean run() throws DukeException {
        int index;
        try {
            index = Integer.parseInt(_input) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidMarkInput(_input);
        }
        if (index < 0 || index > getTasks().size() - 1) {
            throw new InvalidMarkInput(_input);
        }
        ITask t = getTasks().remove(index);

        UserInterface.Speak("Noted. I've removed this task:\n" + t + "\nNow you have " + getTasks().size() + " in the list.");

        return false;
    }
}
