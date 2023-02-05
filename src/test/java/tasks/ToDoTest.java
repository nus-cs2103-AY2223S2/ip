package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ToDoTest {
    private String[] commands = {"todo", "ip 7"};
    private ToDo simpleTask;
    @Test
    void createTask_simpleTask() {
        ToDo simpleTask = ToDo.create(commands);
        assertNotNull(simpleTask);
    }

    @Test
    void testCreateFromFile_simpleTask() {
        ToDo simpleTask = ToDo.create("ip 7", "1");
        assertNotNull(simpleTask);
        assertEquals(simpleTask.getTaskType(), "T");
        assertEquals(simpleTask.toString(), "[T][X] ip 7");
        assertEquals(simpleTask.writeTask(), "T 1 ip 7");
    }

    @Test
    void buildDescription_simpleTask() {
        String[] simpleTaskArrayCommand = {"todo", "ip", "7"};
        assertEquals(ToDo.buildDescription(simpleTaskArrayCommand, 1, 2), "ip 7");
    }

    @Test
    void markTask_simpleTask_statusIconX() {
        ToDo simpleTask = ToDo.create(commands);
        simpleTask.mark();
        assertEquals(simpleTask.getStatusIcon(), "X");
    }

    @Test
    void unmarkTask_simpleTask_statusIconEmpty() {
        ToDo simpleTask = ToDo.create(commands);
        simpleTask.unmark();
        assertEquals(simpleTask.getStatusIcon(), " ");
    }

    @Test
    void getTaskType_simpleTask_getT() {
        ToDo simpleTask = ToDo.create(commands);
        assertEquals(simpleTask.getTaskType(), "T");
    }

    @Test
    void testToString_simpleTask() {
        ToDo simpleTask = ToDo.create(commands);
        simpleTask.mark();
        assertEquals(simpleTask.toString(), "[T][X] ip 7");
        simpleTask.unmark();
        assertEquals(simpleTask.toString(), "[T][ ] ip 7");
    }

    @Test
    void writeTask() {
        ToDo simpleTask = ToDo.create(commands);
        simpleTask.unmark();
        assertEquals(simpleTask.writeTask(), "T 0 ip 7");
        simpleTask.mark();
        assertEquals(simpleTask.writeTask(), "T 1 ip 7");
    }
}
