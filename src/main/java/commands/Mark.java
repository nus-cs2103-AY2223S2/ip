package commands;

import exceptions.DukeException;
import exceptions.InvalidMarkInput;
import tasks.ITask;
import uitilties.UserInterface;

import java.util.ArrayList;

public class Mark extends ICommand {
    private final String _input;

    public Mark(ArrayList<ITask> tasks, String input) {
        super(tasks);
        _input = input.trim();
    }

    @Override
    public boolean run() throws DukeException {
        int index;
        try{
            index = Integer.parseInt(_input) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidMarkInput(_input);
        }
        if(index < 0 || index > getTasks().size() -1) {
            throw new InvalidMarkInput(_input);
        }
        ITask t = getTasks().get(index);
        t.markAsDone();

        UserInterface.Speak("Nice! I've marked this task as done:\n" + t);

        return false;
    }
}
