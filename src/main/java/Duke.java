import java.io.IOException;

import Commands.Command;
import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }
    
    public void run() throws IOException {
        boolean isContinueConvo = true;
        while (isContinueConvo) {
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = new Parser().parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isContinueConvo = c.isContinueConvo();
            } catch (DukeException e) {
                ui.showError(e);
            } 
            ui.showLine();
        }
    }
}
