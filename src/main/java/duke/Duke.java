package duke;

import duke.functions.Functions;
import duke.gui.Window;
import duke.storage.Storage;
import duke.storage.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <h1>Duke task checklist</h1>
 * The Duke program helps keep track of your ongoin task.
 * <p>
 *
 * @author Chin Jun An
 * @version 1.0
 * @since 2023
 */
public class Duke extends Application {
    /**
     * Represents a Duke program.
     */
    private Functions fn;
    public boolean hasError = false;
    public String error;

    public Duke() {

    }
    public Duke(String fp, boolean hasError) {
        if (hasError) {
            try {
                this.fn = new Functions(new TaskList(), new Storage(fp));
            } catch (IOException e) {}
        }
    }

    /**
     * Constructor for a Duke instance. Load tasks previously saved.
     *
     * @param fp Indicate the file path to save task scheduled
     */
    public Duke(String fp) {
        try {
            Storage st = new Storage(fp);
            TaskList tl = st.load();
            this.fn = new Functions(tl, st);
        } catch (IOException e) {
            this.hasError = true;
            this.error = "task.txt was not found";
        }
    }

    public boolean hasError() {
        return this.hasError;
    }

    public Functions getFn() {
        return this.fn;
    }

    @Override
    public void start(Stage stage) {
        Duke d = new Duke("tasks.txt");
        Pane p = Window.makeWindow(d);
        Scene scene = new Scene(p);
        Window.setStage(stage, scene);
    }
}
