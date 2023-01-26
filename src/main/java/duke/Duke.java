package duke;

import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
