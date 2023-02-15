package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class DeleteNoteCommand extends Command {

    protected int index;

    public DeleteNoteCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {
        String output = taskList.deleteNoteInTask(index);
        ui.printText(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String output = "deleteNote taskIndex" +
                "\n This command deletes the note in the selected task" +
                "\nExample: deleteNote 3";
        return output;
    }
}
