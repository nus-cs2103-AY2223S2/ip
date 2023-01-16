package commands;

import tasks.ITask;
import uitilties.UserInterface;

import java.util.ArrayList;

public class Exit extends ICommand{
    public Exit(ArrayList<ITask> tasks) {
        super(tasks);
    }

    @Override
    public boolean run() {
        UserInterface.Speak("Bye. Hope to see you again soon!");
        return true;
    }
}
