public class Duke {
    private TaskList tl;
    private Storage storage;
    private UI ui;

    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);

        try {
            tl = new TaskList(storage.loadFromFile());
        } catch (DukeException exception) {
            ui.showLoadingError();
            tl = new TaskList();
        }
    }

    public void run() {
        ui.getWelcomeMessage();

        while (true) {
            try {
                String userInput = ui.readCommand();
                Command c = new Parser().parseCommand(userInput);
                c.execute(tl, ui, storage);
            } catch (DukeException exception) {
                ui.showError(exception.toString());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("tasks.txt");
        duke.run();
    }
}
