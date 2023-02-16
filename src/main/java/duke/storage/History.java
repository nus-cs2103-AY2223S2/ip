package duke.storage;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * The History class represents the past states of the tasklist.
 */
public class History {
    /** Max save states */
    private static final int MAX_HISTORY = 30;
    /** Single instance */
    private static History history = new History();
    /** Task save states */
    private ArrayList<TaskList> previousTaskList;
    private int currentTask;
    /** Gui save states */
    private ArrayList<ObservableList<Node>> previousGui;
    private int currentGui;
    /** Command Histories */
    private ArrayList<String> previousCommand;

    private History() {
        previousTaskList = new ArrayList<>(30);
        currentTask = -1;
        previousGui = new ArrayList<>(30);
        currentGui = -1;
        previousCommand = new ArrayList<>(60);
    }

    /**
     * Retrives the single instance of history.
     *
     * @return The instance of history.
     */
    public static History getInstance() {
        return history;
    }

    /**
     * Adds the currentTask state of the tasklist to history.
     *
     * @param state The state of the tasklist to add.
     */
    public void addTaskState(TaskList state) {
        if (currentTask == MAX_HISTORY) {
            previousTaskList.remove(0);
            currentTask -= 1;
        }
        previousTaskList.add(new TaskList(state));
        previousCommand.add("TASK");
        currentTask += 1;
    }

    /**
     * Retrieves the direct previous state of the Tasklist.
     *
     * @return The previous state of the tasklist.
     * @throws DukeException if there is nothing to undo.
     */
    public TaskList undoTaskState() throws DukeException {
        if (previousCommand.get(previousCommand.size() - 2) != "TASK") {
            return previousTaskList.get(currentTask);
        }
        if (currentTask <= 0) {
            throw new DukeException("There is nothing to undo!");
        }
        previousTaskList.remove(currentTask);
        currentTask -= 1;
        previousCommand.remove(previousCommand.size() - 2);
        return previousTaskList.get(currentTask);
    }

    /**
     * Adds the currentTask state of the tasklist to history.
     *
     * @param state The state of the tasklist to add.
     */
    public void addGuiState(ObservableList<Node> state) {
        if (currentGui == MAX_HISTORY) {
            previousGui.remove(0);
            currentGui -= 1;
        }
        previousGui.add(FXCollections.observableArrayList(state));
        previousCommand.add("GUI");
        currentGui += 1;
    }

    /**
     * Retrieves the direct previous state of the Tasklist.
     *
     * @return The previous state of the tasklist.
     * @throws DukeException if there is nothing to undo.
     */
    public ObservableList<Node> undoGuiState() throws DukeException {
        if (currentGui <= 0) {
            throw new DukeException("There is nothing to undo!");
        }
        previousGui.remove(currentGui);
        currentGui -= 1;
        previousCommand.remove(previousCommand.size() - 1);
        return previousGui.get(currentGui);
    }
}
