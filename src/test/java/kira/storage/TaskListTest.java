package kira.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import kira.exception.KiraException;
import kira.task.Task;
import kira.task.TaskStub;

public class TaskListTest {

    public static ArrayList<Task> testList() {
        ArrayList<Task> test = new ArrayList<>();
        test.add(new TaskStub(1));
        test.add(new TaskStub(2));
        test.add(new TaskStub(3));
        return test;
    }

    @Test
    public void mark_correctIndex_success() throws KiraException {
        TaskList taskList = new TaskList(testList());
        assertEquals(1, ((TaskStub) taskList.mark(1)).getIndex());
        assertEquals(2, ((TaskStub) taskList.mark(2)).getIndex());
        assertEquals(3, ((TaskStub) taskList.mark(3)).getIndex());
    }

    @Test
    public void unmark_correctIndex_success() throws KiraException {
        TaskList taskList = new TaskList(testList());
        assertEquals(1, ((TaskStub) taskList.unmark(1)).getIndex());
        assertEquals(2, ((TaskStub) taskList.unmark(2)).getIndex());
        assertEquals(3, ((TaskStub) taskList.unmark(3)).getIndex());
    }

    @Test
    public void delete_correctIndex_success() throws KiraException {
        TaskList taskList = new TaskList(testList());
        assertEquals(1, ((TaskStub) taskList.delete(1)).getIndex());
    }

    @Test
    public void delete_removeTask_success() throws KiraException {
        TaskList taskList = new TaskList(testList());
        taskList.delete(2);
        assertEquals(2, taskList.getList().size());
    }

    @Test
    public void findToday_noDeadlineOrEvents_emptyListSuccess() {
        TaskList taskList = new TaskList(testList());
        assertEquals(new ArrayList<>(), taskList.findToday());
    }

}
