package panav;

import panav.command.Command;
import panav.command.ExitCommand;
import panav.command.ListCommand;
import panav.exception.DukeException;
import panav.parser.Parser;
import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Panav {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Panav(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
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
                if(c.toString().compareTo(ListCommand.COMMAND_WORD) != 0 ||
                        c.toString().compareTo(ExitCommand.COMMAND_WORD) != 0) {
                    storage.write(tasks);
                }
                isExit = c.isExit();
            } catch (DukeException e) {
               System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                continue;
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Panav("C:\\Users\\panav\\OneDrive\\Desktop\\CS2103T\\ip\\src\\main\\java\\data\\panav.txt").run();
    }
}

