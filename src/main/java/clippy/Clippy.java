package clippy;

import clippy.command.Command;
import clippy.command.Parser;
import clippy.exception.ClippyException;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.GuiLinker;
import clippy.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;


/**
 * The friendly task manager, Clippy!
 *
 * @author chunzkok
 */
public class Clippy {
    private static final String logo = "   _____  _  _                      \n"
            + "  / ____|| |(_)                     \n"
            + " | |     | | _  _ __   _ __   _   _ \n"
            + " | |     | || || '_ \\ | '_ \\ | | | |\n"
            + " | |____ | || || |_) || |_) || |_| |\n"
            + "  \\_____||_||_|| .__/ | .__/  \\__, |\n"
            + "               | |    | |      __/ |\n"
            + "               |_|    |_|     |___/ ";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Sets up the UI, storage and TaskList components for Clippy to run.
     */
    public Clippy(Ui ui) {
        this.ui = ui;
        this.storage = new Storage(this.ui);
        this.taskList = new TaskList(storage.loadState());
    }

    /**
     * Drives the rest of the code.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(GuiLinker.class, args);
    }

    /**
     * Performs the appropriate command handler.
     *
     * @param input The command to be parsed.
     */
    public void handleCommand(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(ui, taskList, storage);
            if (command.shouldContinue() == false) {
                Platform.exit();
            }
        } catch (ClippyException e) {
            ui.prettyPrint(e.toString());
        } catch (Exception e) {
            ui.systemPrint(e.toString());
            Platform.exit();
        }
    }

}
