package commands;

import tasks.ITask;
import uitilties.UserInterface;

import java.util.ArrayList;

public class ListTasks extends ICommand {

    public ListTasks(ArrayList<ITask> tasks) {
        super(tasks);
    }

    @Override
    public boolean run() {

        int i = 1;
        StringBuilder sentence = new StringBuilder("Here are the tasks in your list:\n");
        for (ITask item : getTasks()) {
             sentence.append(i).append(".").append(item).append("\n");
            i++;
        }
        UserInterface.Speak(sentence.toString());
        return false;

    }
}
