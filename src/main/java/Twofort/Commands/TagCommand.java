package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

import java.util.ArrayList;

public class TagCommand extends Command {
    private int index;
    private ArrayList<String> tags;

    public TagCommand(int index, ArrayList<String> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.tagTask(index, tags);
        saver.save(taskList);
        return message;

    }

}
