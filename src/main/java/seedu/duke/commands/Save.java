package seedu.duke.commands;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.tasks.Task;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save implements Command {

    /**
     * Executes the command with the specified TaskList, Ui, and, Storage
     * @param tasks The TaskList object containing the tasks
     * @param ui The Ui object handling input/output
     * @param storage The Storage object handling persistent storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            File dest = new File("data");
            dest.mkdir();
            File saveFile = new File(dest, "duke.tasks.txt");
            if (saveFile.createNewFile()) {
                ui.showSaveFileCreated(saveFile.getName());
            } else {
                ui.showOverwrite();
                saveFile.delete();
                saveFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(saveFile);
            for (Task task: tasks.getTaskList()) {
                fileWriter.write(task.save());
            }
            fileWriter.close();
            ui.showSaveComplete();
        } catch (IOException e) {
            ui.showErrorMessage(e);
        }
    }

    /**
     * Returns whether the command is an Exit command
     * @return False
     */
    public boolean isExit() {
        return false;
    }
}
