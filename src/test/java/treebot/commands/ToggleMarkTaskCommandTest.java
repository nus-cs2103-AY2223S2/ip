package treebot.commands;

import org.junit.jupiter.api.Test;
import treebot.interfaces.ITaskList;
import treebot.interfaces.IUndoable;
import treebot.tasks.TaskListStub;
import treebot.tasks.TaskListStubForIndexableCommands;
import treebot.tasks.TaskListStubForSingleTask;
import treebot.tasks.Todo;
import treebot.utils.StorageStub;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToggleMarkTaskCommandTest {

    @Test
    void markTaskAsDone_normalUsage_handledCorrectly() {
        ITaskList taskList = new TaskListStub();
        assert taskList.getSize() > 0;

        Command c = new ToggleMarkTaskCommand(0, true);
        c.injectContext(taskList, new StorageStub(), null);


        assertEquals("I have marked the following task as done: \n" +
                "[T][X] task 1", c.execute());

    }

    @Test
    void markTask_indexOutOfBounds_exceptionThrown() {
        ITaskList taskList = new TaskListStubForIndexableCommands();

        Command c = new ToggleMarkTaskCommand(taskList.getSize() + 1, true);
        c.injectContext(taskList, new StorageStub(), null);

        String expected = "Given index is out of range of taskList!!";

        assertEquals(expected, c.execute());
    }

    @Test
    void undoMarkTaskCommand_handledCorrectly() {
        ITaskList taskList = new TaskListStubForSingleTask();
        taskList.addTask(new Todo("Dummy Todo Task"));
        Command c = new ToggleMarkTaskCommand(0, true);
        c.injectContext(taskList, new StorageStub(), null);
        c.execute();
        assertEquals("[T][X] Dummy Todo Task", taskList.getPrintableTasks());
        ((IUndoable) c).undo();

        assertEquals("[T][] Dummy Todo Task", taskList.getPrintableTasks());


    }






}
