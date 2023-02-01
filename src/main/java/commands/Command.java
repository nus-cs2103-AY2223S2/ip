package commands;

import java.io.IOException;

import features.DukeException;
import features.Storage;
import features.TaskList;

abstract class Command {
    public abstract String handle(String[] userInput) throws DukeException;

    /**
     * Automatically saves state of taskList after each change.
     * @param taskList The taskList to save.
     * @throws DukeException  If the taskList cannot be saved.
     */
    public void autoSave(TaskList taskList) throws DukeException {
        try {
            new Storage().saveTaskList(taskList);
        } catch (IOException err) {
            throw new DukeException("[ERROR]\nOops, we couldn't save that!");
        }
    }
}
