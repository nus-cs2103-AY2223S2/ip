package main;

import command.Command;
import exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList list;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.list = new TaskList();
        this.ui = new Ui();
    }

    public void start() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.readCommand();
                Command command = this.parser.parse(input);
                command.execute(list, ui, storage);
                isExit = command.isExit();
                if(command.isExit()) {
                    ui.close();
                    return;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.start();
    }
}
