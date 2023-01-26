import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Boolean.parseBoolean;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(java.nio.file.Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
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
                Command c = Parser.parse(fullCommand, ui, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
            ui.showEnter();
        }
    }
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "duke.txt");
        new Duke(path).run();
    }
}
