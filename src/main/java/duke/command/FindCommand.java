package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

public class FindCommand extends Command{
    protected String word;

    /**
     * constructor for FindCommand class
     * @param word to find the task with it
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * executes the purpose of the FindCommand
     * @param taskList use to find the tasks with the word
     * @param storage can be ignored but is required due to the abstract class
     * @param ui use to print out tasks with word
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui)  {
        ui.printText(taskList.findTaskWithWord(word));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String output = "find word" +
                "\nThis command helps to find all task that contains the word entered" +
                "\nExample: find homework";
        return output;
    }
}
