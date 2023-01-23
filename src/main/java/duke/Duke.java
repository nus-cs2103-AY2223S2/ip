package duke;
import duke.commands.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTaskList();
        } catch (Exception e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public void run() {
        this.ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            this.ui.showLine();

            try {
                String command = this.ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                System.out.println("    input is not of the correct format!");
            }
        }
    }
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
