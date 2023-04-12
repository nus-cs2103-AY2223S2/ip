package commands;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            File dest = new File("data");
            dest.mkdir();
            File saveFile = new File(dest, "save.txt");
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

    public boolean isExit() {
        return false;
    }
}
