package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;
import duke.tool.Storage;
import duke.tool.Parser;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a task manager bot class.
 */
public class Duke {

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a task manager Duke object.
     * @param dirPath The directory to write tasks into.
     * @param filePath The file to write tasks into.
     */
    public Duke(String dirPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Run the Duke object that handles input stream from command line.
     */
    public void run() {
        System.out.println(this.ui.print_greet_msg());
        Scanner sc = new Scanner(System.in);
        Parser.process_input(this.tasks, sc, this.ui);
        this.storage.save_to_file(this.tasks);
    }

    /**
     * Gets response in an interactive manner with extracted user input string.
     * @param input User-input string.
     * @return A string of reponse.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            output = Parser.switch_input(this.tasks, input, this.ui);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        if (output.isBlank()) {
            return this.ui.print_empty_msg();
        } else {
            return output;
        }
    }

    /**
     * Gets ui object saved in Duke.
     * @return A Ui object
     */
    public Ui getUi() {
        return this.ui;
    }

    public static void main(String[] args) {
        new Duke("", "").run();
    }
}
