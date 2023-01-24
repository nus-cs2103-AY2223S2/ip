package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testDefaultStringConversion(){
        assertEquals("[E][ ] read book (from: May 12 2022 to: Oct 27 2023)",
                new Event("read book", false, "2022-05-12", "2023-10-27").toString());
        assertEquals("[E][X] READ BOOK (from: Oct 27 2019 to: Dec 27 2019)",
                new Event("READ BOOK", true, "2019-10-27", "2019-12-27").toString());
        assertEquals("[E][ ] 56 + abc > y (from: Dec 06 1977 to: Oct 30 2019)",
                new Event("56 + abc > y", false, "1977-12-06", "2019-10-30").toString());
        assertEquals("[E][X] 123 (from: Feb 01 2022 to: May 16 2023)",
                new Event("123", true, "2022-02-01", "2023-05-16").toString());
    }

    @Test
    public void testTaskStorageStringConversion(){
        assertEquals("E|false|read book|2022-05-12|2023-10-27",
                new Event("read book", false, "2022-05-12", "2023-10-27").toTaskStorageString());
        assertEquals("E|true|READ BOOK|2019-10-27|2019-12-27",
                new Event("READ BOOK", true, "2019-10-27", "2019-12-27").toTaskStorageString());
        assertEquals("E|false|56 + abc > y|1977-12-06|2019-10-30",
                new Event("56 + abc > y", false, "1977-12-06", "2019-10-30").toTaskStorageString());
        assertEquals("E|true|123|2022-02-01|2023-05-16",
                new Event("123", true, "2022-02-01", "2023-05-16").toTaskStorageString());
    }

    @Test
    public void taskMarking() {
        Task task = new Event("read book", false, "2022-05-12", "2023-10-27");
        task.mark();

        assertEquals("[E][X] read book (from: May 12 2022 to: Oct 27 2023)", task.toString());
        assertEquals("E|true|read book|2022-05-12|2023-10-27", task.toTaskStorageString());
    }

    @Test
    public void taskUnmarking() {
        Task task = new Event("read book", true, "2022-05-12", "2023-10-27");
        task.unmark();

        assertEquals("[E][ ] read book (from: May 12 2022 to: Oct 27 2023)", task.toString());
        assertEquals("E|false|read book|2022-05-12|2023-10-27", task.toTaskStorageString());
    }
}
