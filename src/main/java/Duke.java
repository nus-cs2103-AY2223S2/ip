import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);
    }

    public void run() {
        ui.startMessage();
        while(ui.isOpenForInput()) {
            String nextInput = ui.getInput();
            parser.processInput(nextInput);
        }
        storage.store(tasks);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/dukeData/duke.txt");
        duke.run();
    }



}
