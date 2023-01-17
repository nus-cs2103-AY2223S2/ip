package commands;

import tasks.ITask;
import uitilties.Parser;
import uitilties.UserInterface;

public class ListTasks extends ICommand {

    public ListTasks(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() {

        int i = 1;
        StringBuilder sentence = new StringBuilder("Here are the tasks in your list:\n");
        for (ITask item : getParser().getTasks()) {
             sentence.append(i).append(".").append(item).append("\n");
            i++;
        }
        sentence.append("You have total ").append(getParser().getTasks().size()).append(" tasks in the list.");
        UserInterface.Speak(sentence.toString());
        return false;

    }
}
