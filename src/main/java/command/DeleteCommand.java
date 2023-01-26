package command;

import duke.DukeList;
import exception.TaskOutOfRangeException;

public class DeleteCommand extends Command {
    private String string;
    private DukeList dukelist;

    public DeleteCommand(String string, DukeList dukeList) {
        this.string = string;
        this.dukelist = dukeList;
    }

    @Override
    public void execute() {
        try {
            dukelist.delete(Integer.parseInt(string));
        }  catch (TaskOutOfRangeException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
