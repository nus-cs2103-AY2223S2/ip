package command;

import duke.DukeList;

public class ArchiveCommand extends Command{
    private String taskNumber;
    private DukeList list;
    private DukeList archive;

    public ArchiveCommand(String taskNumber, DukeList list, DukeList archive) {
        this.taskNumber = taskNumber;
        this.list = list;
        this.archive = archive;
    }

    @Override
    public void execute() {
        list.transferTaskTo(archive, Integer.parseInt(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
