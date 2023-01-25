package dukes.engine;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;
import dukes.util.Parser;

import dukes.command.Command;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showBye();
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
