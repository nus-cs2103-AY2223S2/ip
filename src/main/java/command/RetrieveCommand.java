package command;

import duke.DukeList;

public class RetrieveCommand extends Command {
    private String archiveTaskNumber;
    private DukeList list;
    private DukeList archive;

    public RetrieveCommand(String archiveTaskNumber, DukeList list, DukeList archive) {
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
