package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static String line_break = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor method for Duke Chatbot.
     *
     * @param filePath a string representation of the filePath of
     *                 data stored when application is run.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.greet();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.loadData(taskList);
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    /**
     * Main Method to call Duke.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Run method to start Duke. Initializes scanner and parser
     * and contains main loop for commands.
     */
    private void run() {
        Scanner usr_in = new Scanner(System.in);
        Parser parser = new Parser(usr_in);
        while (usr_in.hasNextLine()) {
            try {
                parser.parse_cmds(taskList, ui);
                storage.writeToData(taskList.itemsToData());
            } catch (Exception e) {
                System.out.println(line_break + "\t " + e.getMessage() + "\n" + line_break);
            }
        }
    }
}
