import java.io.*;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exception.DukeException;

import java.util.Scanner;

public class Duke {
    private Ui ui;
    private static TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath, String directoryPath) {
        ui = new Ui();
        storage = new Storage(filePath, directoryPath);
        tasks = new TaskList();
        parser = new Parser(tasks, storage);
        try {
            tasks = storage.loadTasks();
        } catch (DukeException | IOException e) {
            Ui.print(String.valueOf(e));
        }
    }

    public void run() {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            parser.parse(command);
            command = sc.nextLine();
        }
        Ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt", "/data").run();
    }

}
