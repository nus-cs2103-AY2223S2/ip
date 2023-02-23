package Duke.Commands;

import Duke.Saver;
import Duke.Tasks.TaskList;

import java.util.ArrayList;

public class UntagCommand extends Command {
    private int index;
    private ArrayList<String> tags;

    public UntagCommand(int index, ArrayList<String> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.untagTask(index, tags);
        saver.save(taskList);
        return message;

    }

}