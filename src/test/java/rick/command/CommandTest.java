package rick.command;

import rick.Storage;
import rick.TaskList;
import rick.Ui;

/**
 * Main template to test Commands.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public abstract class CommandTest {
    static final Storage STORAGE = Storage.create();
    static final Ui UI = new Ui();
    static final TaskList TASK_LIST = TaskList.create(STORAGE, UI);
}
