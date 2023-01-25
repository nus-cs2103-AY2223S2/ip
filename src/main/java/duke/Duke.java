package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static boolean exitApp = false;
    private static Storage storage = new Storage("data.txt");
    private static Parser parser = new Parser();
    private static Ui ui = new Ui();

    public static void main(String[] args) {
        init();

        ui.showWelcome();

        // App loop
        while (!exitApp) {
            update();
        }

        // Exit message
        try {
            storage.save(taskList.toStorageString());
        } catch (IOException e) {
            ui.showMessage("☹ OOPS!!! Something went wrong when saving your tasks!");
        }
        ui.showMessage("Bye! Hope to see you again soon!");
    }

    private static void init() {
        try {
            storage.read(taskList, ui);
        } catch (FileNotFoundException e) {
            ui.showMessage("☹ OOPS!!! Could not find the storage file.");
        }
    }

    private static void update() {
        String input = ui.readInput();
        if (input.isEmpty()) {
            return;
        }

        exitApp = parser.parseAndExecute(input, taskList, ui);
    }

}
