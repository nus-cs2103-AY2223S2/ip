/**
 * Project Name: Duke
 *
 * @author Darius Ng Teng Wee
 */
package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Duke is a personal chatBot that tracks tasks to do.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User2.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/testDuke.png"));

    /**
     * A constructor for Duke.
     *
     * @param stage The Stage of the Application.
     */
    public Duke(Stage stage) {
        this.ui = new Ui(stage);
        String filepath = System.getProperty("user.home") + "/data/data.txt";
        new File(filepath).getParentFile().mkdirs();
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
     * Generates response from Duke.
     *
     * @param command Command inputted by User.
     * @return Response from Duke.
     */
    public String getResponse(String command) {
        assert command != "" : "Command should not be empty!";
        try {
            Command userCommand = Parser.parse(command);
            return userCommand.execute(tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
