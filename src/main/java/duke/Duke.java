package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() {
        ui.greet();
        Scanner scn = new Scanner(System.in);

        while (true) {
            String userInput = scn.nextLine();

            try {
                Parser.parse(userInput, tasks);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

            try {
                storage.save(tasks);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

            if (userInput.equals("bye")) {
                break;
            }

        }

        scn.close();

    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
