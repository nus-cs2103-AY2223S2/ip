import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line_break = "\n\t ^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
    private TaskList taskList;

    private Storage storage;

    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
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
                System.out.println(line_break + "\t " + e.getMessage() + "\n" + line_break);
            }
        }
    }
}
