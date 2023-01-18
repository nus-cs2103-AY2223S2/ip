package dude;

import dude.command.Command;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeMissingCommandException;
import dude.parser.Parser;
import dude.storage.Storage;
import dude.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dude {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public Dude(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
//          ui.showLoadingError();
            tasks = new TaskList();

        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
//            catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
            finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dude("data/tasks.txt").run();
    }

}
