package commands;

import exceptions.InvalidMarkInput;
import tasks.ITask;
import uitilties.UserInterface;

import java.util.ArrayList;

public class Unmark extends ICommand{
    private final String _input;
    public Unmark(ArrayList<ITask> tasks, String input){
        super(tasks);
        _input = input;
    }

    @Override
    public boolean run() throws InvalidMarkInput {
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
        t.markAsUnDone();

        UserInterface.Speak("OK, I've marked this task as not done yet:\n" + t);
        return false;
    }
}
