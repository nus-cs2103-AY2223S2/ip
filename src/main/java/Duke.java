import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Tasklist;
import duke.Ui;

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;

    public Duke(String filepath) throws IOException {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    public void run() throws DukeException, FileNotFoundException {
        this.ui.welcome();
        Scanner userInputObj = new Scanner(System.in);
        String userInput = "";
        Parser parser = new Parser();
        while (!userInput.equals("bye")) {
            userInput = userInputObj.nextLine();
            parser.parse(userInput, tasks, ui, storage);
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        new Duke("data/tasks.txt").run();
    }
}
