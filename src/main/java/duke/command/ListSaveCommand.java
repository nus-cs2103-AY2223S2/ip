package duke.command;

import duke.Save;
import duke.SaveList;
import duke.Tasklist;
import duke.Ui;

import java.util.ArrayList;

public class ListSaveCommand extends Command{
    private final SaveList SAVE_LIST;


    public ListSaveCommand(SaveList save_list) {
        SAVE_LIST = save_list;
    }

    public String execute(Tasklist tasklist, int saveNo) {
        return Ui.listSaves(SAVE_LIST);
    }

    public boolean isExit() {
        return false;
    }
}
