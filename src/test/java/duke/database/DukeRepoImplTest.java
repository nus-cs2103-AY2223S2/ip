package duke.database;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DatabaseCloseException;
import duke.task.Task;

public class DukeRepoImplTest {

    private DukeRepo db;
    private Task task;

    @BeforeEach
    void setUp() {
        db = new DukeRepoImpl(true);
        task = new Task("test"); 
    }

    @Test
    void testAddTask() {
        try {
            db.addTask(task);
            assertTrue(db.count() == 1);
        } catch (DatabaseCloseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCount() {
        try {
            assertTrue(db.count() == 0);
            db.addTask(task);
            assertTrue(db.count() == 1);
        } catch (DatabaseCloseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllTask() {
        try {
            db.addTask(task);
            assertTrue(db.getAllTask().get(0).equals(task));
        } catch (DatabaseCloseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTask() {
        try {
            db.addTask(task);
            assertTrue(db.getTask(1).equals(task));
        } catch (DatabaseCloseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemoveTask() {
        try {
            db.addTask(task);
            assertTrue(db.count() == 1);
            db.removeTask(1);
            assertTrue(db.count() == 0);
        } catch (DatabaseCloseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testClose() {
        db.close();
        assertThrowsExactly(DatabaseCloseException.class, () -> { db.addTask(task); });
    }

    @Test
    void testUpdateTask() {
        try {
            db.addTask(task);
            assertTrue(db.count() == 1);
            task.markAsDone();
            db.updateTask(1, task);
            assertTrue(db.count() == 1);
            assertTrue(db.getTask(1).toCsv().equals(task.toCsv()));
        } catch (DatabaseCloseException e) {
            e.printStackTrace();
        }
    }
}
