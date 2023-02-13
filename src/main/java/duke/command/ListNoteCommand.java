package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class ListNoteCommand extends Command{

    protected int index;

    public ListNoteCommand(){
        index = -1;
    }

    public ListNoteCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {

        if (index==-1) {
            String output = "Here are all the task with notes: \n\n" + taskList.viewAllTasksWithNotes();
            ui.printText(output);
        } else {
            String output = taskList.viewSingleTaskWithNote(index);
            ui.printText(output);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
