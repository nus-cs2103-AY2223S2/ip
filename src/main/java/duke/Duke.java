package duke;

import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private String horizontalLine = "************************";
    private TaskList taskList;

    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        scanner = new Scanner(System.in);

        storage = new Storage("list_storage.txt");
        parser = new Parser();
        taskList = new TaskList(storage);
        ui = new Ui(taskList);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        // Show intro
        ui.showIntro();

        boolean isExit = false;
        while (!isExit) {
            try {
                String rawInput = ui.readInput();
                Command c = parser.parseInput(rawInput);
                c.execute(taskList, ui);
                isExit = c.getExit();
            } catch (DukeException e) {
                ui.showUnknown(e.getMessage());
            }
        }
    }


}
