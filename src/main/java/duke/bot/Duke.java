package duke.bot;

import duke.taskmanager.Tag;
import duke.taskmanager.TaskList;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main Driver
 */
public class Duke {
    ///////////////////
    private Storage storage;
    private TaskList tasks;
    private static final String FILE_PATH = Paths.get(Paths.get(System.getProperty("user.home")).toString(),
            "tasks.txt").toString();
    private Ui ui;


    /*constructor for duke bot with its storage*/
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        Tag.load();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public Duke() {
        storage = new Storage(FILE_PATH);
        Tag.load();
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
            Parser.parse(str, tasks);
        }
    }
    public String getResponse(String input)  {
        return "Duke: " + Parser.parse(input, tasks);
    }

    /*initialise duke bot with path of storage*/
    public static void main(String[] args) throws IOException {
        new Duke("duke/bot/data/tasks.txt").run();
    }


}
