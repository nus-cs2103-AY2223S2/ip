package duke;

import duke.exception.InvalidFormatException;
import duke.exception.UnrecognisedCommandException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private final Parser p;
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data", "duke.txt");
        tasks = new TaskList(ui, storage);
        p = new Parser(
                ui,
                tasks,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy (EEE)")
        );
        storage.p = p;
        tasks.init(p);
    }

    public void run() {
        while (true) {
            try {
                if (!p.processInput(ui.nextLine())) {
                    break;
                }
            } catch (InvalidFormatException e) {
                ui.print(e.getMessage());
            } catch (UnrecognisedCommandException e) {
                ui.print("Command not recognised. Please try again.");
            } catch (DateTimeParseException e) {
                ui.print("Wrong date time format");
            }
        }
    }

    public static void main (String[] args) {
        new Duke().run();
    }
}

