package duke;

import duke.ui.*;
import duke.storage.FileManagement;
import duke.task.TaskList;
import duke.exceptions.DukeException;

public class Duke {
    private FileManagement fileManager;
    private TaskList tasks;
    private UI ui;


    public Duke() {
        this.fileManager = new FileManagement(); // to manage saved data
        this.tasks = new TaskList(fileManager.retrieve()); // load existing list of tasks; creates empty if does not exist
        this.ui = new UI(this.tasks); // receives user input and run parser
    }

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
            this.fileManager.save(tasks); // save regardless
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
