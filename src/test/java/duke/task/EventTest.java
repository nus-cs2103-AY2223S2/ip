package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    private final String description;
    private final String startTime;
    private final String endTime;
    private final Event task;

    public EventTest() throws DukeException {
        description = "Hello World";
        startTime = "2024-01-01";
        endTime = "2024-02-01";
        task = new Event(description, startTime, endTime);
    }

    @Test
    public void getStartTime() {
        // A unit test for Event#getStartTime

        // automatically verify the response
        assertEquals(task.getStartTime(),
                startTime);
    }

    @Test
    public void getEndTime() {
        // A unit test for Event#getEndTime

        // automatically verify the response
        assertEquals(task.getEndTime(),
                endTime);
    }

    @Test
    public void toSaveData() {
        // A unit test for Event#toSaveData
        // test setup
        String delimiter = " | ";
        String saveData = "E"
                + delimiter + " "
                + delimiter + description
                + delimiter + startTime
                + delimiter + endTime;

        // automatically verify the response
        assertEquals(task.toSaveData(delimiter),
                saveData);
    }

    @Test
    public void generate() throws DukeException {
        // A unit test for Event#generate
        // test setup
        Event task = Event.generate("event " + description
                + " /from " + startTime
                + " /to " + endTime);

        // automatically verify the response
        assertEquals(task.getDescription(),
                this.task.getDescription());
        assertEquals(task.getStatusIcon(),
                this.task.getStatusIcon());
        assertEquals(task.getStartTime(),
                this.task.getStartTime());
        assertEquals(task.getEndTime(),
                this.task.getEndTime());
    }
}
