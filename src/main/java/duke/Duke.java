package duke;

import duke.command.Command;
import duke.command.Commands;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.Parser;
import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
        try {
            storage.loadFromSave(taskList);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    public void run() {
        Scanner s = new Scanner(System.in);
        String text;
        boolean isExit = false;

        ui.printWelcome();

        while (!isExit) {
            text = s.nextLine();
            try {
                Command c = parser.parse(text);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
