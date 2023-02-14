package panav;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Label;


import panav.command.Command;
import panav.command.ExitCommand;
import panav.command.ListCommand;
import panav.exception.DukeException;
import panav.parser.Parser;
import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;



/**
 * The class where Panav starts. It contains the main() and all functionality starts here.
 */
public class Panav {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to initialise the various classes in program.
     * @param filePath the path of the file to be read from.
     */
    public Panav() throws IOException {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }


    }



    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    /**
     * Generates Panav's response to the user's input.
     *
     * @param input user's input.
     * @return response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);

            String res = c.execute(tasks, ui, storage);
            boolean isNotListCommand = c.toString().compareTo(ListCommand.COMMAND_WORD) != 0;
            boolean isNotExitCommand = c.toString().compareTo(ExitCommand.COMMAND_WORD) != 0;
            if (isNotListCommand || isNotExitCommand) {
                storage.write(tasks);
            }
            return res;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }



}

