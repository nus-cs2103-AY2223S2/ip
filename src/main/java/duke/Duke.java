package duke;

import java.io.IOException;

import duke.exceptions.CommandException;
import duke.exceptions.DukeException;
import duke.exceptions.GlobalExceptionHandler;
import duke.interfaces.CommandEventListener;
import duke.interfaces.InputEventListener;
import duke.model.TaskModel;
import duke.presenter.TaskPresenter;
import duke.view.gui.DukeGui;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * The main class containing Duke's event loop.
 */
public class Duke {
    /**
     * The entry point to the Duke chatbot. It initializes the GUI, Model and Presenter.
     * @param args The arguments to Duke, none are expected.
     * @throws Exception If FXML resources or image assets are not found.
     */
    public static void main(String[] args) throws Exception {
        DukeGui dukeGui = new DukeGui();
        TaskModel taskModel = new TaskModel();
        dukeGui.setTasks(taskModel.getTasks(), false);

        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler(dukeGui);
        CommandEventListener exitEventListener = command -> {
            dukeGui.stop();
        };
        TaskPresenter presenter = new TaskPresenter(taskModel, dukeGui, exitEventListener);
        InputEventListener inputEventListener = input -> {
            try {
                presenter.handleInput(input);
            } catch (DukeException e) {
                exceptionHandler.handleException(e);
            }
        };
        dukeGui.registerInputEventListener(inputEventListener);

        // Solution adapted from:
        // fabian. "How to pass parameters to JavaFX application?" Stack Overflow, Stack Exchange Inc.,
        // 22 Sep 2017, https://stackoverflow.com/a/46370465. CC-BY-SA 3.0. Accessed 20 Feb 2023.
        dukeGui.init();
        Platform.startup(() -> {
            // create primary stage
            Stage stage = new Stage();
            try {
                dukeGui.start(stage);
                presenter.greetUser();
            } catch (IOException | CommandException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
