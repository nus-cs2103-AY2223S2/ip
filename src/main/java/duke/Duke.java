package duke;

import java.util.Scanner;
import java.io.IOException;

import duke.command.*;
import duke.storage.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.exception.DukeException;

public class Duke {
    static TaskList list;
    private static final Ui ui = new Ui();
    private static final Parser logic = new Parser();

    Storage storage;


    public Duke(String data, String dataPath) {
        this.storage = new Storage(data, dataPath);
        try {
            list = storage.loadData();
        } catch (DukeException | IOException e) {
            list = new TaskList();
        }
    }
    public void run() throws DukeException, IOException {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(ui.printWelcomeMessage());
        System.out.println(ui.showLine());
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        boolean isExit = false;
        while (!isExit) {
            Command cmd = logic.parse(input);
            System.out.println(cmd.execute(list, ui));
            System.out.println(ui.showLine());
            isExit = cmd.isExit();
            if (!isExit) {
                input = sc.nextLine();
            }
        }
        Storage.saveData(list);
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt", "./data").run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command cmd = logic.parse(input);
        assert cmd != null : "command must not be null";
        return cmd.execute(list, ui);
    }

}
