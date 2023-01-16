package duke.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Task;

public class DukeRepoImplTest {

    DukeRepo db;

    @BeforeEach
    void setUp() {
        db = new DukeRepoImpl();
    }

    @Test
    void testAddTask() {
        Task t = new Task("test");
        db.addTask(t);
        assertTrue(db.count() == 1);
    }

    @Test
    void testClose() {

    }

    @Test
    void testCount() {
        assertTrue(db.count() == 0);
        Task t = new Task("test");
        db.addTask(t);
        assertTrue(db.count() == 1);
    }

    @Test
    void testGetAllTask() {
        Task t = new Task("test");
        db.addTask(t);
        assertTrue(db.getAllTask().get(0).equals(t));
    }

    @Test
    void testGetTask() {
        Task t = new Task("test");
        db.addTask(t);
        assertTrue(db.getTask(1).equals(t));
    }

    @Test
    void testRemoveTask() {
        Task t = new Task("test");
        db.addTask(t);
        assertTrue(db.count() == 1);
        db.removeTask(1);
        assertTrue(db.count() == 0);
    }

    @Test
    void testUpdateTask() {

    }
}
