package Main;

import Command.Command;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    private Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.outputError(e.getMessage());
        }
    }

    private void run() {
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.outputError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}