package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatCheckerTest {
    @Test
    public void isCorrectDateInputTest(){
        assertEquals(true, FormatChecker.isCorrectDateInput("01/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectDateInput("01/11/200112:00"));
        assertEquals(false, FormatChecker.isCorrectDateInput("01.11.2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectDateInput("01/11/2001"));
        assertEquals(false, FormatChecker.isCorrectDateInput("2001/11/01"));
    }

    @Test
    public void isCorrectDeadlineCmd_correct() {
        assertEquals(true, FormatChecker.isCorrectDeadlineCmd("task1 /by 01/11/2001 12:00"));
        assertEquals(true, FormatChecker.isCorrectDeadlineCmd("task 1 /by 01/11/2001 12:00"));
        assertEquals(true, FormatChecker.isCorrectDeadlineCmd("task   1 /by 01/11/2001 12:00"));
    }

    @Test
    public void isCorrectDeadlineCmd_wrongFormat(){
        assertEquals(false, FormatChecker.isCorrectDeadlineCmd("task1/by 01/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectDeadlineCmd("task1 /by01/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectDeadlineCmd("task1 /by 01/11/200112:00"));
    }

    @Test
    public void isCorrectDeadlineCmd_missingContent() {
        assertEquals(false, FormatChecker.isCorrectDeadlineCmd("/by 2001/11/01 12:00"));
    }

    @Test
    public void isCorrectEventCmd_correct() {
        assertEquals(true, FormatChecker.isCorrectEventCmd("task1 /from 01/11/2001 12:00 /to 02/11/2001 12:00"));
        assertEquals(true, FormatChecker.isCorrectEventCmd("task 1 /from 01/11/2001 12:00 /to 02/11/2001 12:00"));
        assertEquals(true, FormatChecker.isCorrectEventCmd("task   1 /from 01/11/2001 12:00 /to 02/11/2001 12:00"));
    }


    @Test
    public void isCorrectEventCmd_wrongFormat(){
        assertEquals(false, FormatChecker.isCorrectEventCmd("task1/from 01/11/2001 12:00 /to 02/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectEventCmd("task1 /from01/11/2001 12:00 /to 02/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectEventCmd("task1 /from 01/11/200112:00 /to 02/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectEventCmd("task1 /from 01/11/2001 12:00/to 02/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectEventCmd("task1 /from 01/11/2001 12:00 /to02/11/2001 12:00"));
        assertEquals(false, FormatChecker.isCorrectEventCmd("task1 /from 01/11/2001 12:00 /to 02/11/200112:00"));
    }

    @Test
    public void isCorrectEventCmd_missingContent() {
        assertEquals(false, FormatChecker.isCorrectEventCmd("/from 01/11/2001 12:00 /to 02/11/2001 12:00"));
    }
}
