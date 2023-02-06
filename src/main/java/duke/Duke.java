package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * The class Duke encapsulates a Duke object, a chatbot which takes note
 * of the various details of the tasks of the user.
 */
public class Duke {

    private TaskManagement taskManager; // to manage saved data
    private TaskStorage taskStorage;
    private Ui ui;

    /**
     * Constructs a Duke object with the given filepath. The file which
     * stores all the tasks of a user will be stored in that filepath.
     * @param filepath The given filepath.
     */
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

    /**
     * This method runs the Duke object. Various changes will be made in the Duke object.
     * @throws IOException When there is invalid input.
     */
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();

        ui.showWelcome();
        String line = "-------------------------------";
        String inp;

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

    /**
     * The main method of the program.
     * @param args Not used.
     * @throws IOException Not used.
     * @throws DukeException Not used.
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("./data/tasks.txt").run();
    }
}
