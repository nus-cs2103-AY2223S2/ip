package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class FindCommand extends Command{
    protected String word;
    public FindCommand(String word) {
        this.word = word;
    }
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {
        ui.printText(taskList.findTaskWithWord(word));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
