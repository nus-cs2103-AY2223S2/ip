package command;

import java.io.IOException;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command loading the contents of a file to a <code>TaskList</code>.
 */
public class Load implements Command {
    private final String path;

    /**
     * Creates a load command.
     *
     * @param path path of the file to load from.
     */
    public Load(String path) {
        this.path = path;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        if (!path.equals("")) {
            try {
                storage.load(path, tasks);
                ui.print("done! i've taken your list from: " + path);
            } catch (IOException ex) {
                ui.print("umm... i can't read from that!");
                ui.print("> " + ex.getMessage());
            } catch (Storage.MikiLoadException ex) {
                ui.print(ex.getMessage());
            }
        } else {
            ui.print("tell me what to read!!!");
            try {
                String[] saves = storage.listSaves();
                for (int i = 0; i < saves.length; i++) {
                    ui.print("> " + saves[i]);
                }
            } catch (Storage.MikiLoadException ex) {
                ui.print(ex.getMessage());
            }
        }
    }
}
