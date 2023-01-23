package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTest {
    @Test
    public void toStringTest1() {
        ToDo a = new ToDo("Eat a sandwich!");
        assertEquals("[T][ ] Eat a sandwich!", a.toString());
    }

    @Test
    public void toStringTest2() {
        ToDo a = new ToDo("Eat a sandwich!");
        assertEquals("T= =Eat a sandwich!", a.toSaveString());
    }

    @Test
    public void toStringTest3() {
        ToDo a = new ToDo(" ");
        assertEquals("[T][ ] ", a.toString());
    }

    @Test
    public void toStringTest4() {
        ToDo a = new ToDo(" ");
        assertEquals("T= =", a.toSaveString());
    }
}
