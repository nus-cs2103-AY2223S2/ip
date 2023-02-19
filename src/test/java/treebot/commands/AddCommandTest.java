package treebot.commands;

import org.junit.jupiter.api.Test;
import treebot.interfaces.ITaskList;
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

        assertEquals(taskList.getPrintableTasks(), "[T][] add command test");

    }
}
