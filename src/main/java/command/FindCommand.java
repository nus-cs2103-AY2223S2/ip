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
        DukeList foundList = this.dukeList.findSubString(task);
        if (foundList.isEmpty()) {
            System.out.println("Sorry man, couldn't find anything with [" + task + "]");
        } else {
            System.out.println(new TextBorder("So I've found these:"));
            System.out.println(foundList);
        }

    }
}
