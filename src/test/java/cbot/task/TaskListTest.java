package cbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.ArrayList;

public class TaskListTest {
    Task t = new Task("task");
    ArrayList<Task> tFive = new ArrayList<>(Arrays.asList(t, t, t, t, t));
    TaskList tlZero = new TaskList();
    TaskList tlFive = new TaskList(tFive);
    
    @Test
    public void testGetCountZero() {
        assertEquals(tlZero.getCount(), 0);
    }
    
    @Test
    public void testGetCountFive() {
        assertEquals(tlFive.getCount(), 5);
    }
    
    @Test
    public void testAddTask1() {
        TaskList tl = new TaskList();
        assertEquals(tl.addTask(t),
                "\"[T][ ] task\" added!");
    }
    
    @Test
    public void testAddTask2() {
        TaskList tl = new TaskList();
        tl.addTask(t);
        assertEquals(tl.getCount(), 1);
    }
    
    @Test
    public void testListTasksZero() {
        assertEquals(tlZero.listTasks(), new ArrayList<String>());
    }
    
    @Test
    public void testListTasksFive() {
        assertEquals(tlFive.listTasks(),
                new ArrayList<>(Arrays.asList(
                        "  1. [T][ ] task",
                        "  2. [T][ ] task",
                        "  3. [T][ ] task",
                        "  4. [T][ ] task",
                        "  5. [T][ ] task")));
    }
    
    @Test
    public void testListFilterEmpty() {
        assertEquals(tlFive.listFilter(t -> false), new ArrayList<String>());
    }
    
    @Test
    public void testNotInRangeTrue1() {
        assertTrue(tlFive.notInRange(0));
    }
    
    @Test
    public void testNotInRangeTrue2() {
        assertTrue(tlFive.notInRange(6));
    }
    
    @Test
    public void testNotInRangeFalse() {
        assertFalse(tlFive.notInRange(3));
    }
    
    @Test
    public void testRangeError1() {
        assertEquals(tlFive.getRangeErrorMsg(0), "wadahek pls");
    }
    
    @Test
    public void testRangeError2() {
        assertEquals(tlFive.getRangeErrorMsg(6), "Hm, you don't have that many tasks!");
    }
    
    @Test
    public void testRangeError3() {
        assertEquals(tlFive.getRangeErrorMsg(3), "All's good! That index is in range :D");
    }
    
    @Test
    public void testMark1() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("task"));
        assertEquals(tl.mark(1),
                "Woohoo! You've completed:\n   [T][X] task");
    }
    
    @Test
    public void testMark2() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("done", true));
        assertEquals(tl.mark(1),
                "You've already done:\n   [T][X] done");
    }
    
    @Test
    public void testUnmark1() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("done", true));
        assertEquals(tl.unmark(1),
                "Aw, okay :( I've unmarked:\n   [T][ ] done");
    }
    
    @Test
    public void testUnmark2() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("task"));
        assertEquals(tl.unmark(1),
                "Hm, you haven't yet done:\n   [T][ ] task");
    }
    
    @Test
    public void testDelTask() {
        TaskList tl = new TaskList();
        tl.addTask(t);
        assertEquals(tl.delTask(1),
                "Got it! Deleted:\n   [T][ ] task");
    }
    
    @Test
    public void testSort() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("b"));
        tl.addTask(new Task("c"));
        tl.addTask(new Task("a"));
        tl.sort();
        assertEquals(tl.listTasks(),
                new ArrayList<>(Arrays.asList(
                        "  1. [T][ ] a",
                        "  2. [T][ ] b",
                        "  3. [T][ ] c")));
    }
    
    @Test
    public void testMakeFileFriendly() {
        TaskList tl = new TaskList();
        tl.addTask(t);
        tl.addTask(new Task("done", true));
        assertEquals(tl.makeFileFriendly(),
                "T ;;   ;; task\nT ;; X ;; done");
    }
}