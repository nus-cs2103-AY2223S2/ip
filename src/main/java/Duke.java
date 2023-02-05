import java.io.IOException;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {

    private static final String SAVE_PATH = "./taskList.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    public Duke() {
        try {
            this.storage = new Storage(SAVE_PATH);
            this.taskList = storage.loadData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws IOException, DukeException{
        ui.start();
        String[] input = ui.readLine();
        while (!input[0].equals("bye")) {
            try {
                ui.displayLine();
                parser.readInput(input, taskList);
                ui.displayLine();  
                input = ui.readLine();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                ui.displayLine();
                input = ui.readLine();
            }
        }
        ui.goodbye();
        storage.storeData(this.taskList.getTasks());
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }
}
