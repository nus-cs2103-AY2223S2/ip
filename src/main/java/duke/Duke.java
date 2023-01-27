package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke object.
     *
     * @param filePath String path of data text file to load data from and save to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            storage.load(tasks);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Start the Duke task tracker software.
     */
    public void run() {
        ui.printGreeting();
        while (true) {
            String[] command = ui.receiveInput();
            if (command[0].equals("bye")) {
                ui.exitMessage();
                break;
            }

            ui.printResponse(Parser.execute(command[0], command[1], tasks));
        }

        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showSavingError();
        }
    }

    public static void main(String[] args) {
       new Duke("./data/duke.txt") .run();
    }
}