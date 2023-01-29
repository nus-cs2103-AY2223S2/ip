package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand() {}
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<String> toWrite = new ArrayList<>();
        int size = tasks.getSize();
        for (int i = 0; i < size; i ++) {
            toWrite.add(tasks.get(i).toMemory());
        }
        storage.writeSave(toWrite);
        ui.showExitText();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
