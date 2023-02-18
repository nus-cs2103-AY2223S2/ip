package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

import java.util.ArrayList;

/**
 * This command is used before closing the chatbot to save the users' tasks.
 * When executed, the tasks are saved to a save file, specified in the storage attribute.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
    }

    /**
     * This is the last method to be run before the user closes the chatbot.
     *
     * @param tasks   the TaskList to be saved
     * @param ui      the UI that displays messages
     * @param storage the Storage that writes data in tasks to the specified file location
     * @return a String that bids goodbye to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
