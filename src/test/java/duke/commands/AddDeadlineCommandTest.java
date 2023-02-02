package duke.commands;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddDeadlineCommandTest {
    @Test
    public void constructor_invalidInput_exceptionThrown() {
        try {
            new AddDeadlineCommand(new ArrayList<>(Arrays.asList("there", "is", "no", "by")));
        } catch (Exception e) {
            assertEquals(
                    "Invalid input received! \ntasks.Deadline commands are in the form of: " +
                            "deadline name /by date \n(remember to include '/by')",
                    e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDateFormat_exceptionThrown() {
        try {
            new AddDeadlineCommand(new ArrayList<>(Arrays.asList("deadline", "sampleDeadline", "/by", "today")));
        } catch (Exception e) {
            assertEquals(
                    "Invalid date format! \n date must be in the form of YYYY-MM-DD",
                    e.getMessage());
        }
    }

}
