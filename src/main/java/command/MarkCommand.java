package command;

import duke.DukeList;
import duke.Ui;

public class MarkCommand extends Command{

    private String task;
    private boolean marker;
    private DukeList dukeList;
    private Ui ui;


    public MarkCommand(String task, boolean marker, DukeList dukeList) {
        this.task = task;
        this.marker = marker;
        this.dukeList = dukeList;
    }

    @Override
    public void execute() {
        dukeList.findAndMark(task, marker);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
