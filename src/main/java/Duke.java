import java.io.IOException;

public class Duke {
    private final Ui ui;
    private final Parser parser;
    private Database db;
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            this.db = new Database(filePath);
            this.tasks = new TaskList(this.db, this.ui);
        } catch (IOException e) {
            ui.showErrorMsg("IO", e, this.tasks.getSize());
        } catch (DukeException e) {
            ui.showErrorMsg("Duke", e, this.tasks.getSize());
        }
    }

    public void run() {
        String currInput = ui.getNextLine();
        String[] splitStr = currInput.split(" ", 2);

        ui.showWelcome();
        ui.showLine();

        while(!currInput.equals("bye")) {
            try {
                this.parser.parseInputs(splitStr, tasks);
            } catch (DukeException e) {
                ui.showErrorMsg("Duke", e, this.tasks.getSize());
            } catch (NumberFormatException e) {
                ui.showErrorMsg("NAN", e, this.tasks.getSize());
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorMsg("OutOfBounds", e, this.tasks.getSize());
            } catch (IOException e) {
                ui.showErrorMsg("IO", e, this.tasks.getSize());
            } finally {
                ui.showLine();
                currInput = ui.getNextLine();
                splitStr = currInput.split(" ", 2);
                ui.showLine();
            }
        }
        ui.showExit();
        ui.closeScanner();
    }

    public static void main(String[] arg) {
        new Duke("data/dukeTasks.txt").run();
    }
}
