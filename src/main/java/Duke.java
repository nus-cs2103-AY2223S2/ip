import java.util.Scanner;
import java.io.IOException;
/**
 * Duke is a personal assistant chatbot that help to keep track of various stuff.
 */
public class Duke {
    // Attribute
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() throws IOException {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                Command c = Parser.read(sc);
                c.execute(ui, taskList, storage);
                if (c.isExit()) {
                    return;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    // Driver function
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke1 = new Duke("./data/list.txt");
        duke1.run();
    }
}
