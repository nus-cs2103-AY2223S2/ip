package treebot.commands;

import org.junit.jupiter.api.Test;
import treebot.interfaces.ITaskList;
import treebot.interfaces.IUndoable;
import treebot.tasks.TaskListStubForIndexableCommands;
import treebot.tasks.TaskListStubForSingleTask;
import treebot.tasks.Todo;
import treebot.utils.StorageStub;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {


    @Test
    void deleteTaskCommand_indexOutOfRange_exceptionHandled() {
        ITaskList taskList = new TaskListStubForIndexableCommands();
        int sizeLimit = taskList.getSize();
        Command c = new DeleteCommand(sizeLimit + 1);
        c.injectContext(taskList, new StorageStub(), null);

        String expected = "Given index is out of range of taskList!!";
        assertEquals(c.execute(), expected);

    }

    @Test
    void deleteTaskCommand_indexWithinRange_handledCorrectly() {
        ITaskList taskList = new TaskListStubForIndexableCommands();
        int sizeLimit = taskList.getSize();
        Command c = new DeleteCommand(sizeLimit - 1);
        c.injectContext(taskList, new StorageStub(), null);

        String expected = "Tree has removed the following task: \n" +
                "[T][] Dummy todo\n" +
                "Now you have 5 tasks remaining.";

        assertEquals(c.execute(), expected);

    }

    @Test
    void undoDeleteTaskCommand_handledCorrectly() {
        ITaskList taskList = new TaskListStubForSingleTask();
        taskList.addTask(new Todo("Dummy todo undo delete"));
        Command c = new DeleteCommand(0);
        c.injectContext(taskList, new StorageStub(), null);
        c.execute();

        assert taskList.getSize() == 0 : "delete command executes correctly";
        // save to history
        Deque<IUndoable> history = new ArrayDeque<>();
        history.addFirst((IUndoable) c);

        ((IUndoable) c).undo();

        assertEquals(taskList.getPrintableTasks(), "[T][] Dummy todo undo delete");


    }


}
