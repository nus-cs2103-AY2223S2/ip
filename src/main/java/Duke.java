import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Utilities.*;
import DukeHelpfulCode.Commands.*;
import DukeHelpfulCode.Tasks.*;



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
        new Duke("data/tasks.txt").run();
    }


}
