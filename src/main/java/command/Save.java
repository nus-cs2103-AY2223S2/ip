package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

import java.io.IOException;

public class Save implements Command {
    private final String path;

    public Save(String path) {
        this.path = path;
    }


    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        if (!path.equals("")) {
            try {
                storage.save(path, tasks);
                ui.print("done! i've written your list to: " + path);
            } catch (IOException ex) {
                ui.print("umm... i can't write on that!");
                ui.print("> " + ex.getMessage());
            }
        } else {
            ui.print("tell me where to write!!!");
        }
    }
}
