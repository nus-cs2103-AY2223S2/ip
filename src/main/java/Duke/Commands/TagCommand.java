package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

import java.util.ArrayList;

public class TagCommand extends Command {
    private int index;
    private ArrayList<String> tags;
    public TagCommand(int index, ArrayList tags) {
        this.index = index;
        this.tags = tags;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        String message = taskList.tagTask(index, tags);
        saver.save(taskList);
        return message;

    }

}
