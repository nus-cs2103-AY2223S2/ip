package duke;

import duke.command.Command;

public class Duke {
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.reply(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/duke.txt").run();
    }
}
