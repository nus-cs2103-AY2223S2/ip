package duke;
import java.util.Scanner;

import Command.Command;
import DukeException.DukeException;
import Task.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Github id: adam07018
 *
 * @author Lu Chenyu
 */
public class Duke {
    private TaskList list;
    private Storage s;
    private Parser parser;
    private Ui ui;
    private boolean isExit;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * default constructor
     */
    public Duke() {
        list = new TaskList();
        s = new Storage();
        parser = new Parser();
        ui = new Ui();
        isExit = false;
    }

    /**
     * Starting main method
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke a = new Duke();
        a.begin();
    }

    /**
     * Starts chat with user
     * Format for tasks:
     * 1. todo {description}
     * eg. todo buy lunch
     * 2. deadline {description} /by {time}
     * eg. deadline return book /by 2019-10-15 1530
     * 3. event {description} /from {time} /to {time}
     * eg. event read book /from 2019-10-15 1530 /to 2020-12-11 1200
     *
     * @throws DukeException
     */
    public void begin() {
        Scanner sc = new Scanner(System.in);
        ui.sayHello();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                Command cmd = parser.parse(input);
                cmd.execute(list);
                if (cmd.isExit()) {
                    sc.close();
                    return;
                }
            } catch (DukeException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            isExit = cmd.isExit();
            return cmd.execute(list);
        } catch (DukeException e) {
            return e.toString();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean getIsExit() {
        return isExit;
    }


    public Ui getUi() {
        return ui;
    }
}
