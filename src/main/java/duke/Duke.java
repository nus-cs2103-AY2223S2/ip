package duke;

import duke.command.Command;
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.getNextCommand();
                ui.printLine();
                Command c = Parser.parse(cmd);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data").run();
    }
}
