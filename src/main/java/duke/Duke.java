package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Gui;
import duke.ui.Ui;

/**
 * The Duke class represents a CLI chatbot that performs operations based on CLI user input.
 * <p>
 * Currently, Duke accepts the commands: {@code echo, list, mark, unmark, todo, deadline, event, bye}
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage store;
    private Gui gui;

    /**
     * Constructs Duke.
     */
    public Duke() {
        ui = Ui.getInstance();
        try {
            store = new Storage("src/main/resources/duke.txt");
            tasks = store.loadFromFile();
            tasks = tasks == null ? new TaskList() : tasks;
        } catch (Exception e) {
            ui.produceExceptionOutput(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main function of Duke.
     *
     * @param args There are no options available for now.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs Duke as a CLI chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.getCommand();
                Command c = Parser.parseCommand(command);
                c.execute(tasks, ui, store);
                isExit = c.canExit();
            } catch (DukeException e) {
                ui.produceExceptionOutput(e.getMessage());
            }
        }
    }

    /**
     * Gets Duke to respond once to an input.
     *
     * @param input The user input to respond to.
     * @return Duke's response to the user input.
     */
    public String getResponse(String input) {
        try {
            gui = new Gui(input);
            String command = gui.getCommand();
            Command c = Parser.parseCommand(command);
            String response = c.execute(tasks, gui, store);

            // from https://stackoverflow.com/questions/21974415
            if (c.canExit()) {
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        System.exit(0);
                    }
                }, 1300); // 1.3s
            }
            assert response != null : "Response should not be null";
            assert response != "" : "Response should not be empty";
            return response;
        } catch (DukeException e) {
            return gui.produceExceptionOutput(e.getMessage());
        }
    }

}
