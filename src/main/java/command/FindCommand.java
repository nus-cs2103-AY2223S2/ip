package command;

import duncan.DuncanList;

public class FindCommand extends Command{
    private String task;
    private DuncanList duncanList;

    public FindCommand(String task, DuncanList duncanList) {
        this.task = task;
        this.duncanList = duncanList;
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        this.duncanList.findSubString(task);
    }
}
