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
                // Do nothing
            } catch (Storage.MikiLoadException ex) {
                ui.printMiki("the autosave is corrupted... not using that!");
                ui.printDiv();
            }
        }
        listFilter.run(tasks, ui, storage);
    }

    public void respond(String cmdLine) {
        Command cmd = Parser.parse(cmdLine);
        ui.printUser(cmdLine);
        ui.printDiv();
        cmd.run(tasks, ui, storage);
        ui.printDiv();
        ui.clearInput();

        if (Parser.isListCommand(cmdLine)) {
            listFilter = cmd;
        } else {
            listFilter.run(tasks, ui, storage);
        }

        try {
            storage.save("autosave.txt", tasks);
        } catch (IOException ex) {
            // Do nothing
        }
    }
}
