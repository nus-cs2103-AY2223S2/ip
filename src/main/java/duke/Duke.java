package duke;
import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a todo-list task tracker bot
 *
 * @author Pearl Twe
 * @version CS2103T AY22/23 Semester 2
 */

public class Duke {
    private final Parser parser;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;


    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui(taskList);
        this.parser = new Parser(taskList);
    }

    /**
     * Runs the user interface for abstraction
     */
    public void run() {
        ui.greet();
        parser.getTaskType();
        storage.save(taskList);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}


