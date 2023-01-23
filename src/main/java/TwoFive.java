import java.io.IOException;

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
