package core;

import java.io.IOException;

import command.Command;
import command.RefreshTasks;
import shigure.Ui;
import storage.Storage;
import task.Parser;
import task.TaskList;

/**
 * Core class of the Miki personal-task manager project.
 * In honor of Fuzuki Miki.
 */
public class Miki {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private Command listFilter;

    /**
     * Creates a Miki core with the specified arguments.
     *
     * @param ui            UI for user interaction.
     * @param hasNoAutoload whether this Miki shall not attempt an initial load from the auto-save path.
     */
    public Miki(Ui ui, boolean hasNoAutoload) {
        this.ui = ui;
        storage = new Storage("./data/");
        tasks = new TaskList();
        listFilter = new RefreshTasks();
        ui.printIntro();

        if (!hasNoAutoload) {
            try {
                storage.load("autosave.txt", tasks);
            } catch (IOException ex) {
                // Do nothing - assume this is the first run, and the autosave has simply not been created
            } catch (Storage.MikiLoadException ex) {
                ui.printMiki("the autosave is corrupted... not using that!");
                ui.printDiv();
            }
        }
        listFilter.run(tasks, ui, storage);
    }

    /**
     * Responds to a given user input.
     *
     * @param cmdLine input to respond to.
     */
    public void respond(String cmdLine) {
        Command cmd = Parser.parse(cmdLine);
        ui.printUser(cmdLine);
        ui.printDiv();
        cmd.run(tasks, ui, storage);
        ui.printDiv();
        ui.clearInput();

        if (Parser.isListCommand(cmd)) {
            listFilter = cmd;
        } else {
            assert listFilter != null : "Task filter should be non-null";
            listFilter.run(tasks, ui, storage);
        }

        try {
            storage.save("autosave.txt", tasks);
        } catch (IOException ex) {
            // Do nothing
        }
    }
}
