package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class AddNoteCommand extends Command{

    protected String note;
    protected int index;

    public AddNoteCommand(int index, String note) {
        this.note = note;
        this.index = index;
    }
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {
        String outputToDuke = "Okay I have added your notes to the task!";
        outputToDuke = outputToDuke + "\n" + taskList.addNoteToTask(index, note);
        outputToDuke = outputToDuke + "\n\nNotes:\n" + taskList.getTasks().get(index).getNote();

        ui.printText(outputToDuke);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String output = "addNote taskIndex: message" +
                "\nThis command adds a note to the task of the selected index" +
                "\nExample: addNote 3: Meeting location is at Utown";
        return output;
    }
}
