package duke;

import java.util.Objects;

import command.Command;
import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Parser;
import userinteraction.Ui;

/**
 * Runs the whole application for users to store and track tasks.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Public constructor for Duke.
     */
    public Duke() {
    }

    /**
     * Runs entire program
     */
    public void run() {
        ui.printWelcomeMsg();
        ui.printLineString();
        boolean isBye = false;
        boolean isChangeSource = false;
        while (!isBye) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                if (command != null) {
                    command.execute(tasks, ui, storage);
                    isBye = command.isExit();
                    isChangeSource = command.isDataSourceChanged();
                }
                if (isChangeSource) {
                    tasks = storage.readData();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String setUp() {
        ui = new Ui();
        String msg = "";
        try {
            storage = new Storage();
            tasks = storage.readData();
        } catch (DukeException e) {
            msg = e.getMessage();
            System.out.println(msg);
        }
        return msg;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return Objects.requireNonNull(command).execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            return "Something is missing!";
        } catch (Exception e) {
            return "Unknown error, please try again!";
        }
    }

    public Ui getUi() {
        return ui;
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
