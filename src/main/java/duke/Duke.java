package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;
import duke.tool.Storage;
import duke.tool.Parser;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Duke(String dirPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        this.tasks = new ArrayList<>(100);
    }

    public void run() {
        System.out.println(this.ui.print_greet_msg());
        Scanner sc = new Scanner(System.in);
        Parser.process_input(this.tasks, sc, this.ui);
        this.storage.save_to_file(this.tasks);
    }

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

    public Ui getUi() {
        return this.ui;
    }

    public static void main(String[] args) {
        new Duke("", "").run();
    }
}
