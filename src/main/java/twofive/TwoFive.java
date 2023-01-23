package twofive;

import twofive.command.Command;
import twofive.data.TaskList;
import twofive.exception.TwoFiveException;
import twofive.storage.Storage;
import twofive.ui.Ui;
import twofive.utils.Parser;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class TwoFive {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public TwoFive(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException | TwoFiveException e) {
            ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showError("Deadline/start time/end time must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TwoFiveException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(":( OOPS!!! The task number provided must be a number.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new TwoFive("data/twofive.txt").run();
    }
}
