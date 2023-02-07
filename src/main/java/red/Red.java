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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            this.ui.clear();
            Command command = Parser.parse(input);
            command.execute(this.tasks, this.ui, this.storage);
            return this.ui.getCurrentReply();
        } catch (RedException e) {
            // append the error message to the ui's response
            this.ui.addCurrentReply(e.getMessage());
            return this.ui.getCurrentReply();
        }
    }


    public static void main(String[] args) {
        new Red().run();
    }

}
