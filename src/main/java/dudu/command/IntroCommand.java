package dudu.command;

import dudu.task.TaskList;
import dudu.util.Storage;

/**
 * Command class to introduce dudu.
 */
public class IntroCommand extends Command {
    //Solution below adapted from
    // "https://github.com/hansstanley/ip/blob/master/src/main/java/jarvis/command/IntroCommand.java"
    public IntroCommand() {
        super("Hi");
    }

    @Override
    public String execute(TaskList list, Storage storage) {
        return "Hello! I'm Dudu\n" + "What can I do for you?\n";
    }
}
