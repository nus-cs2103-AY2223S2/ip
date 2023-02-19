package Duke.Commands;

import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader) {
        return messageLoader.getGoodbyeMessage();
    }
}
