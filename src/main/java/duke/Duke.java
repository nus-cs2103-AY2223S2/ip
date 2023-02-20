package duke;

import duke.exceptions.CommandException;
import duke.interfaces.InputEventListener;

import duke.exceptions.DukeException;
import duke.exceptions.GlobalExceptionHandler;
import duke.interfaces.CommandEventListener;
import duke.model.TaskModel;
import duke.presenter.TaskPresenter;
import duke.view.gui.DukeGui;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class containing Duke's event loop.
 */
public class Duke {
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
