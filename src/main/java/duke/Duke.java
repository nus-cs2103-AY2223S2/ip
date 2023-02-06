package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;

public class Duke {

    private TaskManagement taskManager; // to manage saved data
    private TaskStorage taskStorage;
    private Ui ui;
    public Duke(String filepath) {
        ui = new Ui();
        try {
            taskStorage = new TaskStorage();
            taskManager = new TaskManagement(filepath);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskManager = new TaskManagement();
        }
    }
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();

        ui.showWelcome();
        String line = "-------------------------------";
        String inp;

        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */

        boolean isExit = false;
        while (!isExit) {
            inp = ui.readCommand();
            ui.showLine();
            Parser parser = new Parser(this.taskStorage);
            isExit = parser.execute(inp);
            taskManager.save(parser.getTaskStorage());
            ui.showLine();
        }
    }
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("./data/tasks.txt").run();
    }
}
