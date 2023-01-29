package duke;

import duke.command.Command;
import duke.exceptions.DirectoryNotFoundException;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.*;


public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DirectoryNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showError("wrong");
            } catch (DirectoryNotFoundException e) {
                ui.showError(e.toString());
            } catch(FileNotFoundException e) {
                ui.showError(e.getMessage());
            } catch(IOException e) {
                ui.showError(e.getMessage());
            } catch (DukeException e) {
                ui.showError(e.toString());
            }
            finally {

            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
