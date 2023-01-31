
import Duke.TaskList;
import Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.Ui;
import Duke.Parser;

import Exceptions.CommandNotFoundException;

import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    public Duke(String filePath) throws IOException {
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser(taskList, storage);


        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException | DukeMainExceptions errMsg) {
            System.out.println(errMsg);
        }
    }

    public void run() {
        ui.greet();
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        while (!input.equals("bye")) {
            parser.parse(input);
            input = scanner.nextLine();
        }
        ui.bye();
    }

    public static void main(String[] args) throws CommandNotFoundException, IOException {
        new Duke("data/duke.txt").run();

    }
}
