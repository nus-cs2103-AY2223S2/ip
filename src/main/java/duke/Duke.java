package duke;

import exceptions.DukeException;
import exceptions.UnknownInputException;

import java.io.IOException;

public class Duke {
    private UIText ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        try {
            ui = new UIText();
            taskList = new TaskList();
            storage = new Storage("./data/tasks.txt");
            storage.initialize();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Duke().run();

    }

    /**
     * Method to run the Duke program.
     */
    public void run() {
        boolean isClosed = false;
        TaskHandler handler = new TaskHandler(taskList);

        System.out.println(ui.greet());
        while (!isClosed) {
            try {
                System.out.println(Parser.execute(handler, ui, storage));
                System.out.println(ui.separate());
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }
    
}