package duke;

import duke.command.Command;


/**
 * Represents a Duke object, Main Class of this application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage("saved_tasks_list.txt"); // create new storage object with file path
        try {
            this.tasks = new TaskList(storage.load()); //create new task list from data read from storage
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Start the programme
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.write(this.tasks);
            return "Bye~ Hope to see you next time! >v<\n"
                    + "Please click the X on the top right to close the program.";
        }
        try {
            Command c = Parser.parse(input, ui);
            return c.execute(this.tasks, ui);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
