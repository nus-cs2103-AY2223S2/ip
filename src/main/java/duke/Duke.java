package duke;

import duke.storage.FileManagement;
import duke.exceptions.DukeException;
import duke.ui.UI;
import duke.task.TaskList;
import duke.ui.Span;

/**
 * Duke is a personal assistant bot that helps user to track tasks.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class Duke {
    private FileManagement fileManager;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        // to manage saved data
        this.fileManager = new FileManagement();
        // load existing list of tasks; creates empty if does not exist
        this.tasks = new TaskList(fileManager.retrieve());
        // receives user input and run parser
        this.ui = new UI(this.tasks);
    }

    /**
     * Runs the user interface and accepts users' input for processing.
     */
    public void run() {
        this.ui.greetUser();
        boolean exit = false;
        while (!exit) {
            try {
                exit = this.ui.processInput();
            } catch (DukeException e) {
                System.out.println(Span.format(e.toString()));
            }
            if (exit) {
                this.ui.byeUser();
            }
            // save regardless
            this.fileManager.save(tasks);
        }
    }

    /**
     * Entry point to Duke application where Duke is initialized.
     * @param args Unused arguments that user optionally provides when Duke is launched
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
