package duke;

import exceptions.DukeException;

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

    public String getResponse(String input) {
        try {
            TaskHandler handler = new TaskHandler(taskList);
            return Parser.execute(input, handler, ui, storage);
        } catch (DukeException e) {
            return e.getMessage() + "\n";
        }
    }

    public String getWelcome() {
        return "Peace in our time. ";
    }
}