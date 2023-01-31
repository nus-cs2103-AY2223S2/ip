
public class Duke {
    private final static String FILE_PATH = "src/data/tasks.txt";
    private final static String DIRECTORY_PATH = "src/data";
    private final Ui ui;
    private final Storage storage;
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH, DIRECTORY_PATH);

    }
    public void run() {
        ui.printWelcomeMsg();
        ui.printLine();
        TaskList taskList = storage.readData();
        boolean isBye = false;
        while (!isBye) {
            try {
                DateTimeUtils.dateFormatter("2023-12-03");
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                if (command != null) {
                    command.execute(taskList, ui, storage);
                    isBye = command.isExit();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.printByeMsg();
    }
    public static void main(String[] args) {
        new Duke().run();
    }

}
