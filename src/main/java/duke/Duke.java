package duke;

import duke.command.Command;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList();

        try {
            storage.loadTasks(taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.greet();
        boolean ongoing = true;
        while (ongoing) {
            try {
                String fullCommand = ui.getCommand();
                Command c = Parser.stringToCommand(fullCommand);
                c.execute(ui, storage, taskList);
                ongoing = c.isExit();
                storage.saveTasks(taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }

}
