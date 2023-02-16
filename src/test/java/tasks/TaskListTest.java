package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test1() {
        TaskList input = new TaskList();
        input.add(new ToDo("item1"));
        input.add(new Deadline("item2", " 2021-12-12"));
        input.add(new Event("item3", " 2021-12-12", " 2021-12-13"));
        int size = input.getSize();
        int expected = 3;
        assertEquals(expected, size);
    }

    @Test
    public void test2() {
        TaskList input = new TaskList();
        input.add(new ToDo("item1"));
        input.add(new Deadline("item2", " 2021-12-12"));
        input.add(new Event("item3", " 2021-12-12", " 2021-12-13"));
        input.add(new ToDo("item4"));
        Task newItem = new ToDo("item5");
        input.set(2, newItem);
        Task in = input.get(2);
        Task expected = newItem;
        assertEquals(expected, in);
    }

    @Test
    public void test3() {
        TaskList input = new TaskList();
        input.add(new ToDo("item1"));
        input.add(new Deadline("item2", " 2021-12-12"));
        input.add(new Event("item3", " 2021-12-12", " 2021-12-13"));
        input.add(new ToDo("item4"));
        Task newItem = new ToDo("item5");
        input.set(2, newItem);
        input.delete(1);
        int actualSize = input.getSize();
        int expected = 3;
        assertEquals(expected, actualSize);
    }
}
