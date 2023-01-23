package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void correctParseTest1() {
        Deadline a = new Deadline("Eat", "2022/12/11 18:50");
        assertEquals("Eat", a.getName());
        assertEquals("Dec 11 2022, 1850 hrs", a.getByTime());
    }

    @Test
    public void correctParseTest2() {
        Deadline a = new Deadline("Sleep", "tonight");
        assertEquals("Sleep", a.getName());
        assertEquals("tonight", a.getByTime());
    }

    @Test
    public void falseParseTest1() {
        Deadline a = new Deadline("Eat", "2022/22/11 18:50");
        assertEquals("Eat", a.getName());
        assertEquals("2022/22/11 18:50", a.getByTime());
    }

    @Test
    public void falseParseTest2() {
        Deadline a = new Deadline("Eat", "2022/12/11 18:60");
        assertEquals("Eat", a.getName());
        assertEquals("2022/12/11 18:60", a.getByTime());
    }

    @Test
    public void toStringTest1() {
        Deadline a = new Deadline("Eat", "2022/12/11 18:50");
        assertEquals("[D][ ] Eat (by: Dec 11 2022, 1850 hrs)", a.toString());
    }

    @Test
    public void toStringTest2() {
        Deadline a = new Deadline("Eat", "2022/12/11 18:50");
        assertEquals("D= =Eat=2022/12/11 18:50", a.toSaveString());
    }
}
