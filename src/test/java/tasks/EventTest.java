package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void correctParseTest1() {
        Event a = new Event("Eat", "2022/12/11 18:50", "2022/12/11 18:60");
        assertEquals("Eat", a.getName());
        assertEquals("Dec 11 2022, 1850 hrs", a.getFromTime());
        assertEquals("2022/12/11 18:60", a.getToTime());
    }

    @Test
    public void correctParseTest2() {
        Event a = new Event("Eat", "2022/12/11 18:60", "2022/12/11 18:50");
        assertEquals("Eat", a.getName());
        assertEquals("2022/12/11 18:60", a.getFromTime());
        assertEquals("Dec 11 2022, 1850 hrs", a.getToTime());
    }

    @Test
    public void correctParseTest3() {
        Event a = new Event("Eat", "2022/02/01 18:60", "2022/02/01 18:50");
        assertEquals("Eat", a.getName());
        assertEquals("2022/02/01 18:60", a.getFromTime());
        assertEquals("Feb 01 2022, 1850 hrs", a.getToTime());
    }

    @Test
    public void toStringTest1() {
        Event a = new Event("Eat", "2022/12/11 18:60", "2022/12/11 18:50");
        assertEquals("[E][ ] Eat (from: 2022/12/11 18:60 to: Dec 11 2022, 1850 hrs)", a.toString());
    }

    @Test
    public void toStringTest2() {
        Event a = new Event("Eat", "2022/12/11 18:60", "2022/12/11 18:50");
        assertEquals("E= =Eat=2022/12/11 18:60=Dec 11 2022, 1850 hrs", a.toSaveString());
    }
}
