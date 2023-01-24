public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.loadSaveFile());
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        this.ui.greet();
        String input = "";
        do {
            try {
                input = this.ui.getUserInput();
                this.ui.printResponse(parser.parseAndExecute(input));
            } catch (DukeException e) {
                this.ui.printException(e);
            }
        } while (!input.equals("bye"));

        try {
            this.storage.save(tasks.getTasks());
        } catch (DukeException e){
            this.ui.printException(e);
        }
    }
}

