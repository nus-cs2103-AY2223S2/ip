package duke;
import duke.commands.Command;

public class Duke{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = storage.load();
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
                String fullCommand = this.ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    // public static void main(String[] args) {
    //     new Duke("data/tasks.txt").run();
    // }
    
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
