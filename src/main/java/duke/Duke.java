package duke;

import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.readData();
    }

    public void run() {
        Ui.welcomeMessage();
        Parser parser = new Parser(new Scanner(System.in));
        try {
            while (parser.notDone()) {
                tasks = parser.parse(tasks);
            }
        } catch (DukeException e) {
            System.out.println("ParseError: " + e);
        } finally {
            storage.writeData();
            Ui.farewellMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
