package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

import java.io.IOException;

public class SaveCommand extends Command {
    private final String SAVE_INFO;
    private final int SAVE_NO;

    public SaveCommand(int saveNo, String saveInfo) {
        this.SAVE_INFO = saveInfo;
        this.SAVE_NO = saveNo;
    }

    public String execute(Tasklist tasklist, int saveNo) throws IOException {
        Storage.save(tasklist, SAVE_NO, SAVE_INFO);
        return Ui.showSave(SAVE_NO);
    }

    public boolean isExit() {
        return false;
    }
}
