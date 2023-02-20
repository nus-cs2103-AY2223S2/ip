package duke;

import duke.commands.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;


/**
 * The main duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        loadFile();
    }

    /**
     * Begins the execution of the Duke program.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        //start of bot
        while (!isExit) {
            String fullCommand = sc.nextLine();
            try {
                ui.showLine();
                Command c = Parser.parse(fullCommand, this.storage);
                c.execute(taskList);

                if(c.getTag() == "CHANGE_FILE_LOCATION") {
                    loadFile();
                }

                isExit = c.isExit();
            } catch (DukeExceptions exceptions) {
                ui.showError(exceptions);
            } finally {
                ui.showLine();
            }
        }

        storage.save(taskList);
    }

    public void loadFile(){
        this.taskList = storage.loadFile();
    }

    /**
     * Parses, executes and return a string response to a request by the user
     *
     * @return reply to the request
     */
    public String getResponse(String request) {
        try {
            Command com = Parser.parse(request, this.storage);
            String response = com.execute(this.taskList);

            //load task list from new filepath
            if(com.getTag() == "CHANGE_FILE_LOCATION") {
                loadFile();
            }

            if (com.isExit()) {
                storage.save(this.taskList);
            }
            return response;
        } catch (DukeExceptions e) {
            return e.getMessage();
        }

    }

    public static void main(String[] args) {
        new Duke("./data/").run();
    }

}
