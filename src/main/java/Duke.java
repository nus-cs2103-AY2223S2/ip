import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class Duke {
    static final String STR = "------------------------------------------------------------";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
            ui.successfulLoadResponse();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui.loadingErrorMessage();
        }
    }

    public void run() {
        // Introduction
        ui.welcomeResponse();

        // Load data
        Duke duke = new Duke("./data/duke.txt");
        ui.listTaskResponse(tasks);

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.askForTaskResponse();
                String fullCommand = ui.nextInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ArrayIndexOutOfBoundsException e1) {
                ui.unreadableCommandErrorMessage();
            } catch (DateTimeParseException e2) {
                System.out.println("this");
                ui.unreadableCommandErrorMessage();
            } catch (IllegalArgumentException e3) {
                ui.incompleteCommandErrorMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }


}
