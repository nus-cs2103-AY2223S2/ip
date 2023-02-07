package seedu.duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private static TaskList ls = new TaskList();

    public Duke() {
        if (Storage.saveExists()) {
            Storage.loadSave(ls);
        } else {
            Storage.createSave();
        }
    }

    public String getResponse(String input) {
        return Ui.respond(input, ls);
    }
}
