package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private Deadline deadline = new Deadline("return book", "29/01/2023", "2300");
    @Test
    public void addDeadline(){
        assertEquals("[D][ ] return book (by: 29 January 2023 23:00)", deadline.toString());
    }

    @Test
    public void markDeadline(){
        deadline.mark();
        assertEquals("[D][X] return book (by: 29 January 2023 23:00)", deadline.toString());
    }

    @Test
    public void unmarkDeadline(){
        deadline.unmark();
        assertEquals("[D][ ] return book (by: 29 January 2023 23:00)", deadline.toString());
    }

    @Test
    public void saveDeadline(){
        assertEquals("deadline false 29/01/2023 2300\nreturn book", deadline.getDetailsToSave());
    }
}