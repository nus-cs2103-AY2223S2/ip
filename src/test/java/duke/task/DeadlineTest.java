package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidDateException;

public class DeadlineTest {
    @Test
    public void addDeadline() {
        Deadline homework = null;
        try {
            homework = new Deadline("CS3230 HW1", "2023-01-31");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        assertEquals("[D][] CS3230 HW1 | BY: Jan 31 2023", homework.getStatusIcon());
    }

    @Test
    public void mark() {
        Deadline homework = null;
        try {
            homework = new Deadline("CS3230 HW1", "2023-01-31");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        homework.markTask();
        assertEquals("[D][X] CS3230 HW1 | BY: Jan 31 2023", homework.getStatusIcon());
    }

    @Test
    public void unmark() {
        Deadline homework = null;
        try {
            homework = new Deadline("CS3230 HW1", "2023-01-31");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        homework.markTask();
        homework.unmarkTask();
        assertEquals("[D][] CS3230 HW1 | BY: Jan 31 2023", homework.getStatusIcon());
    }

    @Test
    public void encode() {
        Deadline homework = null;
        try {
            homework = new Deadline("CS3230 HW1", "2023-01-31");
        } catch (InvalidDateException e) {
            System.out.println(e);
        }
        assertEquals("deadline ### false ### CS3230 HW1 ### 2023-01-31", homework.encode());
    }
}
