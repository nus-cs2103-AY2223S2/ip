package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDefaultStringConversion(){
        assertEquals("[D][ ] read book (by: May 12 2022)",
                new Deadline("read book", false, "2022-05-12").toString());
        assertEquals("[D][X] READ BOOK (by: Oct 27 2019)",
                new Deadline("READ BOOK", true, "2019-10-27").toString());
        assertEquals("[D][ ] 56 + abc > y (by: Dec 06 1977)",
                new Deadline("56 + abc > y", false, "1977-12-06").toString());
        assertEquals("[D][X] 123 (by: Feb 01 2022)",
                new Deadline("123", true, "2022-02-01").toString());
    }

    @Test
    public void testTaskStorageStringConversion(){
        assertEquals("D|false|read book|2022-05-12",
                new Deadline("read book", false, "2022-05-12").toTaskStorageString());
        assertEquals("D|true|READ BOOK|2019-10-27",
                new Deadline("READ BOOK", true, "2019-10-27").toTaskStorageString());
        assertEquals("D|false|56 + abc > y|1977-12-06",
                new Deadline("56 + abc > y", false, "1977-12-06").toTaskStorageString());
        assertEquals("D|true|123|2022-02-01",
                new Deadline("123", true, "2022-02-01").toTaskStorageString());
    }

    @Test
    public void taskMarking() {
        Task task = new Deadline("read book", false, "2022-05-12");
        task.mark();

        assertEquals("[D][X] read book (by: May 12 2022)", task.toString());
        assertEquals("D|true|read book|2022-05-12", task.toTaskStorageString());
    }

    @Test
    public void taskUnmarking() {
        Task task = new Deadline("read book", true, "2022-05-12");
        task.unmark();

        assertEquals("[D][ ] read book (by: May 12 2022)", task.toString());
        assertEquals("D|false|read book|2022-05-12", task.toTaskStorageString());
    }
}
