package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private LocalDate eventOneStart = LocalDate.parse("2023-03-04", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private LocalDate eventOneEnd = LocalDate.parse("2023-03-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private LocalDate eventTwoStart = eventOneStart;
    private LocalDate eventTwoEnd = eventOneEnd;
    private LocalDate eventThreeStart = eventOneStart;
    private LocalDate eventThreeEnd = LocalDate.parse("2023-03-07", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    /**
     * Checks the implementation of Event.equals().
     */
    @Test
    public void equals() {
        Event eventOne = new Event("festival", eventOneStart, eventOneEnd);
        Event eventTwo = new Event("concert", eventTwoStart, eventTwoEnd);
        Event eventThree = new Event("festival", eventThreeStart, eventThreeEnd);
        ToDo toDo = new ToDo("festival");
        assertEquals(eventOne.equals(eventTwo), false);
        assertEquals(eventThree.equals(eventOne), false);
        assertEquals(eventTwo.equals(eventTwo), true);
        assertEquals(eventThree.equals(toDo), false);
    }

    /**
     * Checks the implementation of Event.toString().
     */
    @Test
    public void toString_checkMessage() {
        Event eventOne = new Event("festival", eventOneStart, eventOneEnd);
        Event eventTwo = new Event("concert", eventTwoStart, eventTwoEnd);
        Event eventThree = new Event("festival", eventThreeStart, eventThreeEnd);
        assertEquals(eventOne.toString(), "[E][ ] festival (from: Mar 4 2023 to: Mar 6 2023)");
        assertEquals(eventTwo.toString(), "[E][ ] concert (from: Mar 4 2023 to: Mar 6 2023)");
        assertEquals(eventThree.toString(), "[E][ ] festival (from: Mar 4 2023 to: Mar 7 2023)");
    }
}
