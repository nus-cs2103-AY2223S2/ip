package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        return messageLoader.getGoodbyeMessage();
    }
}
