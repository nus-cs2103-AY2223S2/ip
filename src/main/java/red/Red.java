package red;

import red.command.Command;

import red.exception.RedException;

import red.parser.Parser;

import red.storage.Storage;

import red.task.TaskList;

import red.ui.UI;

/**
 * The main Red class to run
 */
public class Red {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    /**
     * The constructor
     */
    public Red() {
        this.tasks = new TaskList(100);
        this.ui = new UI();
        this.storage = new Storage(this.tasks,this.ui);

        this.storage.createStorage();
    }

    /**
     * The function that starts the whole program.
     */
    public void run() {
        ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RedException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        this.storage.saveToStorage();
    }

    public static void main(String[] args) {
        new Red().run();
    }

}
