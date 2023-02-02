import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        }
    }

    public void run() throws DukeException{
        ui.showWelcome();
        String cmd = ui.readCommand();
        boolean isTerminated = false;
        while (!isTerminated) {
            if (!cmd.equals("bye")) {
                try {
                    Parser.parse(cmd, tasks);
                    cmd = ui.readCommand();
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else {
                isTerminated = true;
                ui.showBye();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}