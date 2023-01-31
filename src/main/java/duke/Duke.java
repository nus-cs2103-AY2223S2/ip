/**
 * Project Name: Duke
 * Duke is a Chatbot that helps to keep track of stuff to do.
 *
 * @author Darius Ng Teng Wee
 */
package duke;

import duke.command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Duke {
    private Storage storage;
    private Ui ui;
    private static boolean isExit;
    private TaskList tasks;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * A constructor for Duke.
     *
     * @param stage The Stage of the Application.
     */
    public Duke(Stage stage) {
        this.ui = new Ui(stage);
        String filepath = System.getProperty("user.home") + "/data/data.txt";
        File file = new File(filepath);
        try {
            file.createNewFile();
            this.storage = new Storage(filepath);
            this.tasks = new TaskList(storage.loadTaskListFromFile());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets response from Duke.
     *
     * @param command Command inputted by User.
     * @return Response from Duke.
     */
    public String getResponse(String command) {
        try {
            Command userCommand = Parser.parse(command);
            return userCommand.execute(tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
