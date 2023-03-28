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
        this.storage = new Storage("tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) { // e should be EmptyTaskListException
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
            this.tasks = new TaskList();
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

    protected String getResponse(String input) {
        if (!input.equals("bye")) {
            try {
                Command c = Parser.parse(input);
                return c.execute(tasks);
            } catch (NoSuchTaskException e) {
                return "Sorry, I can't find this task.";
            } catch (TaskAlrMarkException e) {
                return "Sorry but this task is already marked.";
            } catch (TaskAlrUnmarkException e) {
                return "Sorry but this task is not marked.";
            } catch (DukeException e) {
                return "Sorry, I don't understand.";
            }
        }
        else {
            try {
                storage.write(tasks);
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
