package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void add() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.add();
        assertEquals("Got it. I've added this task:\n" +
                " [T][ ] borrow book", tdo.message_add);

    }

    @Test
    void display() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.display();
        assertEquals("[T][ ]borrow book", tdo.message_display);
    }

    @Test
    void delete() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.delete();
        assertEquals("Noted. I've removed this task:\n" +
                " [T][ ] borrow book", tdo.message_delete);
    }

    @Test
    void marked() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.marked();
        assertEquals("Nice! I've marked this task as done:\n" +
                "  [T][X] borrow book", tdo.message_marked);
    }

    @Test
    void unmarked() {
        ToDo tdo = new ToDo("borrow book", false);
        tdo.unmarked();
        assertEquals("OK, I've marked this task as not done yet:\n" +
                " [T][ ] borrow book", tdo.message_unmarked);
    }
}