package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;

import duke.exceptions.DukeExceptions;
import duke.tasks.TaskList;

import java.util.Scanner;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks(parser));
        } catch (DukeExceptions e) {
            ui.showError(e);
            taskList = new TaskList();
        }
        this.ui = new Ui();
    }

    public void run() {
        this.ui.showWelcome();
        Scanner s = new Scanner(System.in);
        while(s.hasNext()) {
            try {
                String input = s.nextLine();
                Command command = this.parser.handleCommand(input);

                if (command instanceof ByeCommand) {
                    command.execute(taskList, ui, storage);
                    break;
                } else {
                    command.execute(taskList, ui, storage);
                }
            } catch (DukeExceptions e) {
                ui.showError(e);
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}