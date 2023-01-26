package duke;

import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.TaskList;
import duke.helper.Ui;
import duke.storage.FileSystem;

import java.io.IOException;

public class Duke {
    private final Ui ui;
    private Parser parser;
    private FileSystem db;
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();

        try {
            db = new FileSystem(filePath);
            this.tasks = new TaskList(db.loadFromFile());
            this.parser = new Parser(tasks);
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    public void run() {
        ui.showWelcome();
        String[] splitStr = ui.getNextLine();

        while (!splitStr[0].equals("bye")) {
            try {
                this.parser.parseInputs(splitStr);
            } catch (DukeException | IOException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                ui.showErrorMsg(splitStr[0]);
            } catch (IndexOutOfBoundsException e) {
                ui.showErrorMsg(tasks.getTasks().size());
            } finally {
                splitStr = ui.getNextLine();
            }
        }
        db.updateFile(tasks);
        ui.showExit();
    }

    public static void main(String[] arg) {
        new Duke("data/dukeTasks.txt").run();
    }
}
