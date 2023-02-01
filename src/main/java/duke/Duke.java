package duke;

import duke.command.Command;

import java.io.FileNotFoundException;

public class Duke { // a guy who receives user input, and gives duke reply

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String welcomeMsg;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            this.welcomeMsg = ui.showLoadingError(); // prolly wont run!!!!
            tasks = new TaskList();
        }
        this.welcomeMsg = ui.showWelcome();
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String process(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
//            isExit = c.isExit();
        } catch (Exception e) {
            return ui.showError(e); // or e.getMessage()
        } finally {
        }
    }

}