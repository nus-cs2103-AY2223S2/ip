package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.command.Parser;
import main.java.duke.task.Task;
import main.java.duke.task.TaskList;
import main.java.duke.ui.Ui;
import main.java.duke.storage.Storage;

import java.util.LinkedList;

public class Duke {
    Storage storage;
    Ui ui;
    TaskList tasks;

    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        this.ui = new Ui();
        this.tasks = new TaskList(new LinkedList<Task>());
    }

    private void chat() {
        this.ui.greetings();
        Parser p = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String command = this.ui.getLine();
            Command c = p.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        ui.endUi();
    }

    public static void main(String[] args) {
        new Duke("duke").chat();
    }
}
