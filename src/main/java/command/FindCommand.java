package command;

import duke.DukeList;
import duke.TextBorder;
import task.Task;

public class FindCommand extends Command{
    private String task;
    private DukeList dukeList;

    public FindCommand(String task, DukeList dukeList) {
        this.task = task;
        this.dukeList = dukeList;
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        this.dukeList.findSubString(task);
    }
}
