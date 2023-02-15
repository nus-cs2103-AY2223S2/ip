package command;

import duncan.DuncanList;

public class ArchiveCommand extends Command{
    private String taskNumber;
    private DuncanList list;
    private DuncanList archive;

    public ArchiveCommand(String taskNumber, DuncanList list, DuncanList archive) {
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
