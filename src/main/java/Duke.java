import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static final String PATH = "data/Duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();
        boolean isBye = false;
        while (!isBye) {
            try {
                String commandInput = sc.nextLine();
                Command c = Parser.parse(commandInput);
                c.execute(tasks, ui, storage);
                isBye = c.isBye();
            } catch (DukeException e) {
                ui.showExceptionError(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseError();
            }
        }

    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }
}
