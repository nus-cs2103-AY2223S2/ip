package command;

import java.io.IOException;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command saving the contents of a <code>TaskList</code> to a file.
 */
public class Save implements Command {
    private final String path;

    /**
     * Creates a save command.
     *
     * @param path path of the file to save to.
     */
    public Save(String path) {
        this.path = path;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        if (!path.equals("")) {
            try {
                storage.save(path, tasks);
                ui.printMiki("done! i've written your list to: " + path);
            } catch (IOException ex) {
                ui.printMiki("umm... i can't write on that!");
                ui.printMiki("> " + ex.getMessage());
            }
        } else {
            ui.printMiki("tell me where to write!!!");
        }
    }
}
