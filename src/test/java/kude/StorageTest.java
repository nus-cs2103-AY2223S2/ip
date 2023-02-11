package kude;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import kude.models.Deadline;
import kude.models.Event;
import kude.models.TaskList;
import kude.models.Todo;

public class StorageTest {
    private String testFilePath = "./test_duke.txt";

    @Test
    public void saveTaskList_retrieveEmptyList() throws IOException, ClassNotFoundException {
        var task = new TaskList();
        var storage = new Storage(testFilePath);
        storage.writeTaskList(task);
        var ntask = storage.readTaskList();
        assertEquals(0, ntask.list().count());
    }

    @Test
    public void saveTaskList_retrieveOneTodo() throws IOException, ClassNotFoundException {
        var task = new TaskList();
        var todo = new Todo("i am todo");
        task.add(todo);
        todo.setIsDone(true);
        var storage = new Storage(testFilePath);
        storage.writeTaskList(task);
        var ntask = storage.readTaskList();
        assertEquals(1, ntask.list().count());
        var ntodo = (Todo) ntask.get(0).get();
        assertEquals("i am todo", ntodo.getContent());
        assertEquals(true, ntodo.getIsDone());
    }

    @Test
    public void saveTaskList_retrieveOneDeadline() throws IOException, ClassNotFoundException {
        var task = new TaskList();
        var now = LocalDateTime.now();
        task.add(new Deadline("i am deadline", now));
        var storage = new Storage(testFilePath);
        storage.writeTaskList(task);
        var ntask = storage.readTaskList();
        assertEquals(1, ntask.list().count());
        var deadline = (Deadline) ntask.get(0).get();
        assertEquals("i am deadline", deadline.getContent());
        assertEquals(now, deadline.getDeadline());
        assertEquals(false, deadline.getIsDone());
    }

    @Test
    public void saveTaskList_retrieveOneEvent() throws IOException, ClassNotFoundException {
        var task = new TaskList();
        var now = LocalDateTime.now();
        task.add(new Event("i am event", now, now.plusSeconds(100)));
        var storage = new Storage(testFilePath);
        storage.writeTaskList(task);
        var ntask = storage.readTaskList();
        assertEquals(1, ntask.list().count());
        var event = (Event) ntask.get(0).get();
        assertEquals("i am event", event.getContent());
        assertEquals(now, event.getFrom());
        assertEquals(now.plusSeconds(100), event.getTo());
        assertEquals(false, event.getIsDone());
    }

    @Test
    public void saveTaskList_retrieveMultiple() throws IOException, ClassNotFoundException {
        var task = new TaskList();
        var now = LocalDateTime.now();
        task.add(new Todo("i am todo"));
        task.add(new Deadline("i am deadline", now));
        task.add(new Event("i am event", now, now.plusSeconds(100)));
        task.add(new Todo("i am todo #2"));

        var storage = new Storage(testFilePath);
        storage.writeTaskList(task);
        var ntask = storage.readTaskList();
        assertEquals(4, ntask.list().count());

        var ntodo = (Todo) ntask.get(0).get();
        assertEquals("i am todo", ntodo.getContent());
        assertEquals(false, ntodo.getIsDone());

        var deadline = (Deadline) ntask.get(1).get();
        assertEquals("i am deadline", deadline.getContent());
        assertEquals(now, deadline.getDeadline());
        assertEquals(false, deadline.getIsDone());

        var event = (Event) ntask.get(2).get();
        assertEquals("i am event", event.getContent());
        assertEquals(now, event.getFrom());
        assertEquals(now.plusSeconds(100), event.getTo());
        assertEquals(false, event.getIsDone());


        var ntodo2 = (Todo) ntask.get(3).get();
        assertEquals("i am todo #2", ntodo2.getContent());
        assertEquals(false, ntodo2.getIsDone());
    }

    @AfterEach()
    public void tearDown() {
        new File(testFilePath).delete();
    }
}
