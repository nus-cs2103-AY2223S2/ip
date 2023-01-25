package duke.bot;

import duke.taskmanager.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main Driver
 */
public class Duke {
    ///////////////////
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*constructor for duke bot with its storage*/
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /*main driver code that parses user input, and prints an output and saves input where applicable*/
    public void run() {
        Scanner instr = new Scanner(System.in);
        while (instr.hasNextLine()) {
            String str = instr.nextLine();
            try {
                Parser.parse(str, tasks);
            } catch (IOException ignored) {

            }
        }
    }

    /*initialise duke bot with path of storage*/
    public static void main(String[] args) throws IOException {
        new Duke("duke/bot/data/tasks.txt").run();
    }


}
