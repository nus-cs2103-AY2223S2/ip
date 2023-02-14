
import java.io.IOException;

import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Utilities.*;
import DukeHelpfulCode.Commands.*;


/**
 * The Duke program is adapted DUKE from NUS SoC CS2103
 * Duke is a glorified to-do list.
 *
 * @author  Yuan Hao
 * @version who knows
 * @since   11 Feb 2023
 */

public class Duke {

    private static String LINEBREAK = "_________________________________________________________________\n";
    private static TaskList USERLIST = new TaskList();

    UI ui;
    Storage storage;
    TaskList tasks;

    public Duke(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) { // e should be EmptyTaskListException
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        /**
         * Runs Duke.
         *
         * @param none
         * @return none
         */
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/resources/data/tasks.txt").run();
    }


}
