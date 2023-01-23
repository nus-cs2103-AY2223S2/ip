package duke;
public class Duke {

//    private final ArrayList<Task> storage = new ArrayList<>();
    private TaskList tasklist;

    private final Storage storage;
    private final Ui ui;

    public Duke() {
        ui = new Ui();

        storage = new Storage("data/duke.txt");
        try {
            storage.checkFileExists();
            tasklist = storage.loadFile();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public void run() {
        Parser parser = new Parser(tasklist, ui, storage);
        while (ui.hasUserInput()) {
            parser.parse(ui.userInput());
        }
    }

    public static void main(String[] args) {
        Duke dukeList = new Duke();
        dukeList.run();
    }
}
