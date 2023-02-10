package duke;
import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private static final Ui ui = new Ui();
    private TaskList tasks;

    public Duke(String filePath) throws DukeException {
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

    public void run(){
        ui.showWelcome();
        String cmd = ui.readCommand();
        boolean isTerminated = false;
        while (!isTerminated) {
            if (!cmd.equals("bye")) {
                try {
                    Parser.parse(cmd, tasks);
                    cmd = ui.readCommand();
                } catch (DukeException e) {
                    System.out.println("something went wrong.");
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