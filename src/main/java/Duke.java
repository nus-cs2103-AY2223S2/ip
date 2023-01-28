public class Duke {

    private Storage storage;
    private Storage tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new Storage(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new Storage();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showLoadingError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                storage.updateStorage();
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
