package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    /**
     * This method closes the chatbot upon execution.
     *
     * @param tasks   the TaskList to be saved
     * @param ui      the UI that displays messages
     * @param storage the Storage that writes data in tasks to the specified file location
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<String> toWrite = new ArrayList<>();
        int size = tasks.getSize();
        for (int i = 0; i < size; i++) {
            toWrite.add(tasks.get(i).toMemory());
        }
        storage.writeSave(toWrite);
        return ui.showContainer(ui.showExitText());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
