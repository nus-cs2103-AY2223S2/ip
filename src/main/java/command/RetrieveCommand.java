package command;

import duncan.DuncanList;

public class RetrieveCommand extends Command {
    private String archiveTaskNumber;
    private DuncanList list;
    private DuncanList archive;

    public RetrieveCommand(String archiveTaskNumber, DuncanList list, DuncanList archive) {
        this.archiveTaskNumber = archiveTaskNumber;
        this.list = list;
        this.archive = archive;
    }
    @Override
    public void execute() {
        archive.transferTaskTo(list, Integer.parseInt(archiveTaskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
