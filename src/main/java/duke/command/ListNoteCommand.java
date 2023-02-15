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

    @Override
    public String toString() {
        String output = "listNote\nThis command is to list all tasks that consist note";

        output = output + "\n\nlistNote taskWithNoteIndex" +
                "\nThis command is to list only one specific task with note" +
                "\nExample: listNote 3";
        return output;
    }
}
