package duke;

import util.*;

import java.io.IOException;

public class Duke {
    private static TaskList tasks = new TaskList();
    private static String divider = "    ____________________________________________________________";
    private Ui ui;
    private Storage storage;

    public void run(){
        boolean repeat = true;
        while (repeat) {
            String command = ui.nextInput();
            System.out.println(divider);
            try {
                repeat = Parser.handleGeneralCommand(command, tasks);
            } catch (DukeException e) {
                System.out.println(e);
            }
            if (!repeat) {
                try {
                    storage.saveTasks(tasks);
                } catch (IOException e) {
                    System.out.println("Unable to save! Creating new save file!");
                }
                ui.closeCommand();
            }
            System.out.println(divider);
        }

    }

    public static void main(String[] args) {
        new Duke("src/main/data/", "src/main/data/seedu.duke.txt").run();
    }

    public Duke(String fileDir, String filePath) {
        ui = new Ui();
        storage = new Storage(fileDir, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
}
