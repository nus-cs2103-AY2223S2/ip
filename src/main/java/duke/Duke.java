package duke;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath); // create new storage object with file path
        try {
            this.tasks = new TaskList(storage.load()); //create new task list from data read from storage
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand, ui);
                c.execute(this.tasks, ui);
                isExit = c.isExit(ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.write(this.tasks);
    }

    public static void main(String[] args) {
        new Duke("saved_tasks_list.txt").run();
    }
}