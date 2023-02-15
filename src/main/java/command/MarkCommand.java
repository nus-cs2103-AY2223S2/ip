package command;

import duncan.DuncanList;
import duncan.Ui;

public class MarkCommand extends Command{

    private String task;
    private boolean marker;
    private DuncanList duncanList;
    private Ui ui;


    public MarkCommand(String task, boolean marker, DuncanList duncanList) {
        this.task = task;
        this.marker = marker;
        this.duncanList = duncanList;
    }

    @Override
    public void execute() {
        duncanList.findAndMark(task, marker);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
