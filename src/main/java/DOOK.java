import java.io.IOException;

import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Utilities.*;
import DukeHelpfulCode.Commands.*;


/**
 * The DOOK program is adapted DUKE from NUS SoC CS2103
 * DOOK is a glorified to-do list.
 *
 * @author  Yuan Hao
 * @version who knows
 * @since   11 Feb 2023
 */

public class DOOK {

    private static String LINEBREAK = "_________________________________________________________________\n";
    private static TaskList USERLIST = new TaskList();

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    public DOOK(){
        this.ui = new UI();
        this.storage = new Storage("./src/main/resources/data/tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) { // e should be EmptyTaskListException
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeDOOK(String filePath) {
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

    /**
     * Runs DOOK.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        if (!input.equals("bye")) {
            try {
                Command c = Parser.parse(input);
                return c.execute(tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        else {
            try {
                storage.write(this.tasks);
                return new ExitCommand().execute(tasks);
            } catch (IOException e) {
                return "lol";
            }
        }

    }

    public static void main(String[] args) {
        new DOOK().run();
    }


}
