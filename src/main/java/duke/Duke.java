package duke;

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
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        Parser.process_input(this.tasks, sc);
        this.storage.save_to_file(this.tasks);
    }

    public static void main(String[] args) {
        new Duke("", "").run();
    }
}
