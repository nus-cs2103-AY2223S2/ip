import command.Parser;
import command.Storage;
import command.Ui;
import task.TaskList;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Duke object with the requisite Storage, TaskList, Ui and Parser objects.
     * @param filePath the path where a potential preexisting list can be read into the TaskList.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);
    }

    /**
     * Starts up Duke and accepts user inputs for processing until termination, where the end list is stored in a file.
     */
    public void run() {
        ui.startMessage();
        while(ui.isOpenForInput()) {
            String nextInput = ui.getInput();
            parser.processInput(nextInput);
        }
        storage.store(tasks);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/dukeData/duke.txt");
        duke.run();
    }



}
