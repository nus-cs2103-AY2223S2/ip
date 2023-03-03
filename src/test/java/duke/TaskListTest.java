package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    void testNumberOfTasks() {
        TaskList<Task> taskList = new TaskList<Task>();
        Task borrowBook = new Task("Borrow Book");
        Task returnBook = new Task("Return Book");
        taskList = taskList.add(borrowBook);
        taskList = taskList.add(returnBook);
        int expected = 2;
        int result = taskList.numberOfTasks();
        assertEquals(expected, result);
    }

    @Test
    void testListAllTasks() {
        TaskList<Task> taskList = new TaskList<Task>();
        Task borrowBook = new Task("Borrow Book");
        Task returnBook = new Task("Return Book");
        taskList = taskList.add(borrowBook);
        taskList = taskList.add(returnBook);
        int expected = 2;
        int result = taskList.listAllTasks().numberOfTasks();
        assertEquals(expected, result);
    }

    @Test
    void testGet() {
        TaskList<Task> taskList = new TaskList<Task>();
        Task borrowBook = new Task("Borrow Book");
        Task returnBook = new Task("Return Book");
        taskList = taskList.add(borrowBook);
        taskList = taskList.add(returnBook);
        Task expected = returnBook;
        Task result = taskList.get(1);
        assertEquals(expected, result);
    }

    @Test
    void testAdd() {
        TaskList<Task> taskList = new TaskList<Task>();
        Task borrowBook = new Task("Borrow Book");
        Task returnBook = new Task("Return Book");
        taskList = taskList.add(borrowBook);
        taskList = taskList.add(returnBook);
        int expected = 2;
        int result = taskList.numberOfTasks();
        assertEquals(expected, result);
    }

    @Test
    void testAddMultipleTasks() {
        TaskList<Task> taskList = new TaskList<Task>();
        Task borrowBook = new Task("Borrow Book");
        Task returnBook = new Task("Return Book");
        Task borrowNewBook = new Task("Borrow New Book");
        taskList = taskList.add(borrowBook);
        taskList = taskList.add(returnBook);
        taskList = taskList.add(borrowNewBook);
        int expected = 3;
        int result = taskList.numberOfTasks();
        assertEquals(expected, result);
    }

}
