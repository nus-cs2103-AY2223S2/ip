package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskListTest {

    @Test
    public void get_populatedList_taskListReturned() {
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(new Todo("1"));
        expectedTaskList.add(new Deadline("2", getFirstDateTime()));
        expectedTaskList.add(new Event("3", getFirstDateTime(), getSecondDateTime()));

        TaskList taskList = getPopulatedTaskList();
        ArrayList<Task> actualTaskList = taskList.get();

        assertEquals(expectedTaskList.toString(), actualTaskList.toString());
    }

    @Test
    public void add_populatedList_autoSorted() {
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(new Todo("1"));
        expectedTaskList.add(new Todo("4"));
        expectedTaskList.add(new Deadline("2", getFirstDateTime()));
        expectedTaskList.add(new Event("3", getFirstDateTime(), getSecondDateTime()));

        TaskList taskList = getPopulatedTaskList();
        taskList.add(new Todo("4"));
        ArrayList<Task> actualTaskList = taskList.get();

        assertEquals(expectedTaskList.toString(), actualTaskList.toString());
    }

    @Test
    public void delete_populatedList() {
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(new Todo("1"));
        expectedTaskList.add(new Deadline("2", getFirstDateTime()));

        TaskList taskList = getPopulatedTaskList();
        taskList.delete(2);
        ArrayList<Task> actualTaskList = taskList.get();

        assertEquals(expectedTaskList.toString(), actualTaskList.toString());
    }

    @Test
    public void mark_populatedList_isMarkReturnTrue() {
        Boolean expectedMark = true;
        TaskList taskList = getPopulatedTaskList();
        taskList.mark(1);
        Boolean actualMark = taskList.getTask(1).isMark();
        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void unmark_populatedList_isMarkReturnFalse() {
        Boolean expectedMark = false;
        TaskList taskList = getPopulatedTaskList();
        taskList.unmark(1);
        Boolean actualMark = taskList.getTask(1).isMark();
        assertEquals(expectedMark, actualMark);
    }

    @Test
    public void getTask_populatedList() {
        Task expectedTask = new Todo("1");
        TaskList taskList = getPopulatedTaskList();
        Task actualTask = taskList.getTask(0);
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void getSize_populatedList() {
        int expectedSize = 3;
        TaskList taskList = getPopulatedTaskList();
        int actualSize = taskList.getSize();
        assertEquals(expectedSize, actualSize);
    }

    private TaskList getPopulatedTaskList() {
        ArrayList<String> tasksStringFormat = new ArrayList<>();
        tasksStringFormat.add("T |   | 1");
        tasksStringFormat.add("D |   | 2 | 2024-01-01T01:01");
        tasksStringFormat.add("E |   | 3 | 2024-01-01T01:01 | 2024-02-02T02:02");
        return new TaskList(tasksStringFormat);
    }

    private LocalDateTime getFirstDateTime() {
        String dateTime = "2024-01-01 01:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private LocalDateTime getSecondDateTime() {
        String dateTime = "2024-02-02 02:02";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
