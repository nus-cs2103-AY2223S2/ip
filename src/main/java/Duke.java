public class Duke {

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private static final String filePath = "data/duke.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadFile();
    }

    public void run() {
        Parser parser = new Parser(taskList, ui, storage);
        while (ui.hasNextInput()) {
            parser.parse(ui.userInput());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

