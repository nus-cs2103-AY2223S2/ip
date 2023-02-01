package dudu.command;

import dudu.task.TaskList;
import dudu.util.Storage;

public class IntroCommand extends Command {
    public IntroCommand() {
        super("Hi");
    }

    @Override
    public String execute(TaskList list, Storage storage) {
        return "Hello! I'm Dudu\n" + "What can I do for you?\n";
    }
}
