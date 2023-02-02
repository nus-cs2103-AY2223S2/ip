package duke;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean toContinue = true;

        while (toContinue) {
            String nextCommand = ui.readCommand();
            DukeCommand c = Parser.parseInput(nextCommand);
            c.execute(tasks, ui, storage);
            toContinue = c != DukeCommand.BYE;
        }

    }

    /**
     * The main function.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
