package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;

/**
 * ExitCommand helps call the function to update the file once the command is executed
 */
public class ExitCommand extends Command{

    /**
     * executes the purpose of the ExitCommand
     * @param taskList is where the list of task is to be stored
     * @param storage updates the file
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {
        storage.updateFile(taskList.getTasks());
        ui.printText("Thanks for using me! Seeya! :)");
        ui.exitProgram();
    }

    /**
     * indication if the command should end the program
     * @return true as addCommand is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        String output = "bye\nThis command exits out of the program";
        return output;
    }
}
