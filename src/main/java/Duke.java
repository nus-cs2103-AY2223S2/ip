import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.List;
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList list;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = this.storage.loadFile();
        } catch (DukeException e) {
            this.ui.display(e.getMessage());
            temp = new TaskList();
        }
        this.list = temp;

    }

    public static void main(String[] args) {
        String txtDir = System.getProperty("user.dir") + "/data/tasks.txt";

        Duke instance = new Duke(txtDir);
        instance.run();
    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();
        boolean isBye = false;
        while (!isBye) {
            try {
                String[] line = ui.readLine();
                Command c = parser.parse(line);
                c.execute(list, ui, storage);
                isBye = c.isBye();
            } catch (Exception err) {

                ui.showError(err);
            }
        }
    }
}