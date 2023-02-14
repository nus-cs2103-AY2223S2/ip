package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class UiTest {
    @Test
    public void testWelcome() {
        Ui ui = new Ui();
        assertEquals("Hey, why did you wake me up?\nI am the great Watt Toodu.\nDon't mess with me!\n", ui.getWelcome());
    }

    @Test
    public void testBye() {
        Ui ui = new Ui();
        assertEquals("Good riddance...\n", ui.showBye());
    }
    
    @Test
    public void testList() {
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.add(new Todo("test", false));
        list.add(new Deadline("test", LocalDate.parse("2020-01-01"), false));
        list.add(new Event("test", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"), false));
        assertEquals("Here! Now leave me alone...\n1. [T] [ ] test\n2. [D] [ ] test (by: Jan 1 2020)\n3. [E] [ ] test (from: Jan 1 2020 to: Jan 2 2020)\n", ui.showList(list));
    }

    @Test
    public void testMark() {
        Ui ui = new Ui();
        Task task = new Todo("test", false);
        task.setDone();
        assertEquals("Wow, reaaal impressive work.\n[T] [X] test\n", ui.showMark(task));
    }

    @Test
    public void testUnmark() {
        Ui ui = new Ui();
        Task task = new Todo("test", true);
        task.setNotDone();
        assertEquals("Wow, reaaal impressive work.\n[T] [ ] test\n", ui.showMark(task));
    }

    @Test
    public void testAdd() {
        Ui ui = new Ui();
        Task task = new Todo("test", false);
        assertEquals("Clearly, having good memory isn't one of your strengths...\n[T] [ ] test\nNow you have 1 tasks in the list.\n", ui.showAdd(task, 1));
    }

    @Test
    public void testRemove() {
        Ui ui = new Ui();
        Task task = new Todo("test", false);
        assertEquals("Please clean up after yourself:\n[T] [ ] test\nNow you have 0 tasks in the list.\n", ui.showRemove(task, 0));
    }

    @Test
    public void testFind() {
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.add(new Todo("test", false));
        list.add(new Deadline("test", LocalDate.parse("2020-01-01"), false));
        list.add(new Event("test", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"), false));
        assertEquals("Needle in a haystack...\n1. [T] [ ] test\n2. [D] [ ] test (by: Jan 1 2020)\n3. [E] [ ] test (from: Jan 1 2020 to: Jan 2 2020)\n", ui.showFind(list));
    }

    @Test
    public void testSet() {
        Ui ui = new Ui();
        assertEquals("Po-tay-toes, po-ta-toes, \"that\" is now the same as \"this\".\n", ui.showSet("this",  "that"));
    }
}
