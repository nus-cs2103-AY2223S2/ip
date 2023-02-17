package parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import tasks.Task;

class TaskInfoParserTest {
    private String simpleCommand = "todo ip 7";
    private String[] simpleCommandArray = {"T", "0", "ip", "7"};
    @Test
    void parseTaskFromStdin_simpleTask_correctInfo() {
        TaskInfoParser parser = new TaskInfoParser();
        Task simpleTask = parser.parse(simpleCommand);
        assertNotNull(simpleTask);
        assertEquals("T", simpleTask.getTaskType());
        assertEquals("T 0 ip 7", simpleTask.writeTask());
        assertEquals("ip 7", simpleTask.getDescription());
        assertEquals(" ", simpleTask.getStatusIcon());
        simpleTask.mark();
        assertEquals("X", simpleTask.getStatusIcon());
    }

    @Test
    void parseTaskFromFile_simpleTask_correctInfo() {
        TaskInfoParser parser = new TaskInfoParser();
        Task simpleTask = parser.parse(simpleCommandArray);
        assertNotNull(simpleTask);
        assertEquals("T", simpleTask.getTaskType());
        assertEquals("T 0 ip 7", simpleTask.writeTask());
        assertEquals("ip 7", simpleTask.getDescription());
        assertEquals(" ", simpleTask.getStatusIcon());
        simpleTask.mark();
        assertEquals("X", simpleTask.getStatusIcon());
    }
}
