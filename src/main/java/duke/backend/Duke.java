package duke.backend;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        TaskList taskManager = new TaskList();
        Ui ui = new Ui(taskManager);
        ui.welcome();
        //  Flag for program termination
        boolean isProgramEnded = false;

        while (!isProgramEnded) {
            isProgramEnded = ui.readInput();
        }
    }

    public String getResponse(String input) {
        return "Hello World!";
    }
}
