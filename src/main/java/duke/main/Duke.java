package duke.main;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    private final Ui ui;
    private Tasklist tasks;
    private Storage storage;

    public Duke(String path) {
        ui = new Ui();
        try {
            storage = new Storage(path);
            tasks = new Tasklist(storage.load());
        } catch (IOException ie) {
            ui.printIOException(ie);
            System.exit(0);
        } catch (DukeException de) {
            ui.printDukeException(de);
            tasks = new Tasklist();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while(!isExit) {
            try {
                String stringCommand = ui.readCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printIOException(ie);
            }
        }
        ui.printBye();
        ui.closeScanner();
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
