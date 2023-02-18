package duke.command;

import duke.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LoadCommand extends Command{
    private final int SAVE_NO;


    public LoadCommand(int saveNo) {
        this.SAVE_NO = saveNo;
    }

    public String execute(Tasklist tasklist, int saveNo) throws IOException {
        Tasklist newTasklist = Storage.load(SAVE_NO);
        SaveList saveData = Storage.readSaveData();
        Storage.writeSaveData(saveData, SAVE_NO);
        return Ui.showLoad(this.SAVE_NO);
    }

    public boolean isExit() {
        return false;
    }
}
