package treebot.commands;

import org.junit.jupiter.api.Test;
import treebot.interfaces.ITaskList;
import treebot.interfaces.IUndoable;
import treebot.tasks.TaskListStubForSingleTask;
import treebot.tasks.Todo;
import treebot.utils.StorageStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    void addTaskCommand_normalUsage_executesCorrectly() {
        Todo task = new Todo("add command test");
        ITaskList taskList = new TaskListStubForSingleTask();
        Command c = new AddCommand(task);
        c.injectContext(taskList, new StorageStub(), null);
        c.execute();

        assertEquals("[T][] add command test", taskList.getPrintableTasks());

    }

    @Test
    void undoAddTaskCommand_handledCorrectly() {
        ITaskList taskList = new TaskListStubForSingleTask();
        Todo task = new Todo("undo add command task");
        Command c = new AddCommand(task);
        c.injectContext(taskList, new StorageStub(), null);
        c.execute();

        ((IUndoable) c).undo();

        assertEquals(0, taskList.getSize());


    }
}
