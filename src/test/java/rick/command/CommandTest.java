package rick.command;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import rick.Storage;
import rick.TaskList;
import rick.Ui;
import rick.exceptions.TaskListFullException;
import rick.task.RickTask;


/**
 * Represents the main template to test Commands.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public abstract class CommandTest {
    static final Storage STORAGE = Storage.create();
    static final Ui UI = new Ui();
    static final TaskList TASK_LIST = TaskList.create(STORAGE, UI);
    private static final ArrayList<RickTask> currentTasks = new ArrayList<>();

    /**
     * Stores existing tasks in the Storage temporarily to isolate them from
     * the test environment.
     */
    @BeforeAll
    public static void clearStorage() {
        int size = Integer.parseInt(String.valueOf(STORAGE.size()));
        while (size > 0) {
            currentTasks.add(STORAGE.delete(size - 1));
            size -= 1;
        }
    }

    /**
     * Repopulates the storage and reinstates its original state.
     */
    @AfterAll
    public static void repopulate() {
        while (currentTasks.size() > 0) {
            try {
                TASK_LIST.add(currentTasks.remove(currentTasks.size() - 1));
            } catch (TaskListFullException e) {
                return;
            }
        }
    }
}
