package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final String LINE_BREAK = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    private TaskList taskList;
    private Storage storage;

    private Ui ui;
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        Scanner usr_in = new Scanner(System.in);
        Parser parser = new Parser(usr_in);
        while (usr_in.hasNextLine()) {
            try {
                parser.parse_cmds(taskList, ui);
                storage.writeToData(taskList.itemsToData());
            } catch (Exception e) {
                System.out.println(LINE_BREAK + "\t " + e.getMessage() + "\n" + LINE_BREAK);
            }
        }
    }
}
