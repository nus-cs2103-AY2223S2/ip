package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.image.Image;


import duke.command.Command;
import duke.exceptions.DirectoryNotFoundException;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;

    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));



    /**
     * Initialises the object
     *
     */
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


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input){
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            if (isExit) {
                assert isExit;
                String dukeText = "bye";
                return dukeText;

            }
            return (c.execute(tasks, ui, storage));
        } catch (IllegalArgumentException e) {
            return ui.showError("wrong");
        } catch (DirectoryNotFoundException e) {
            return  ui.showError(e.toString());
        } catch (FileNotFoundException e) {
            return  ui.showError(e.getMessage());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        } catch (DukeException e) {
            return ui.showError(e.toString());
        }

    }






}
